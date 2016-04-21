package server.db.connection;
/////////////////////////////////////////////////////////////////////////////
// CLASS            : TransactionManager
// PURPOSE          : Class used for database transaction
// NOTES            : None
// LAST MODIFIED    :
//

/**
 *
 * @author Nisha
 */
/**
 * Importing standard java classes
 */

import common.ClientException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;

/**
 * Importing VahanClient/VahanCommon java classes
 */
import javax.sql.RowSet;
import nic.java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

public class TransactionManagerUserManagement {

    private String whereiam;
    private int transactionID;
    private static int transactionCounter = 0;
    private static int releasedTransactionCounter = 0;
    private Connection con;
    private Statement stmt;
    private PreparedStatement prstmt;
    private ArrayList sqlList = new ArrayList();
    private boolean transactionSuccessfull;
    private boolean commitHasBeenCalledAlready;
    private boolean released;
    private static final Logger LOGGER = LogManager.getLogger(TransactionManagerLocal.class.getName());

    public TransactionManagerUserManagement(String whereiam) throws SQLException {
        initialize(whereiam);
        // InstanceCount.add(this);
    }

    public void finalize() throws Throwable {

        // InstanceCount.remove(this);
        super.finalize();
        if (!released) {

            Debug.logtnx(formattedTransactionID() + " DEV_ERROR  :  TransactionManager.release()" + " called. Developer has not called tmgr.release() explicitly." + " Please call tmgr.release() explicitly : " + whereiam);
        }
    }

    private void initialize(String whereiam) throws SQLException {
        transactionID = ++transactionCounter;
        try {
            con = ConnectionPooling.getDBConnectionUserManagement();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        con.setAutoCommit(false);
        stmt = con.createStatement();
        sqlList.clear();
        this.whereiam = whereiam;
        transactionSuccessfull = false;
        // Initialize the already committed transaction flag
        commitHasBeenCalledAlready = false;
        // Initialize flag that the resoureces are assigned
        released = false;
        //Debug.logtnx(formattedTransactionID() + ":| ...Transaction Started... " + whereiam);
    }

    public int executeDML(String sql) throws SQLException {
        // Check input
        if (CommonUtils.isNullOrBlank(sql)) {
            throw new SQLException("DEV_ERROR : Null SQL found. : " + whereiam);
        }

        // Ensure that the con is still inside a transaction
        if (commitHasBeenCalledAlready) {
            throw new SQLException("DEV_ERROR : Already commited TransactionManager" + " object is used without calling reset() : " + whereiam);
        }

        // Trim sql
        sql = sql.trim();

        // Log the DML
        //Debug.logsql(formattedTransactionID() + sql);
        // Add to the list
        sqlList.add(sql);

        // Execute DML
        int numRowsAffected = stmt.executeUpdate(sql);

        // Return the number of rows inserted / updated / deleted
        return numRowsAffected;
    }

    public int executePreparedDML() throws SQLException {

        // This will work only with Postgres Database 
        String sql = prstmt.toString();
        // Check input
        if (CommonUtils.isNullOrBlank(sql)) {
            throw new SQLException("DEV_ERROR : Null SQL found. : " + whereiam);
        }

        // Ensure that the con is still inside a transaction
        if (commitHasBeenCalledAlready) {
            throw new SQLException("DEV_ERROR : Already commited TransactionManager" + " object is used without calling reset() : " + whereiam);
        }
        // Ensure the given query is a either INSERT or UPDATE or DELETE or DROP TABLE (DROP T)
        // Also ensures that the qury may be a DROP query
        String iudPart = sql.trim().substring(0, 6);
        if ((!iudPart.equalsIgnoreCase("INSERT")) && (!iudPart.equalsIgnoreCase("UPDATE")) && (!iudPart.equalsIgnoreCase("DELETE")) && (!iudPart.equalsIgnoreCase("DROP T"))) {

            throw new SQLException("Not an Insert/Update/Delete/Drop query : \"" + sql + "\" : " + whereiam);
        }
        // Trim sql
        sql = sql.trim();

        // Log the DML
        //Debug.logsql(formattedTransactionID() + sql);
        // Add to the list
        sqlList.add(sql);

        // Execute DML
        int numRowsAffected = prstmt.executeUpdate();

        // Return the number of rows inserted / updated / deleted
        return numRowsAffected;
    }

    /**
     * Method to execute a SQL-SELECT statement and return a DETACHED RowSet
     * (ResultSet).
     *
     * @param selectSQL - SELECT statement to be executed.
     *
     * @return RowSet - Detached ResultSet.
     */
    public javax.sql.RowSet fetchDetachedRowSetFromPreparedStatement()
            throws SQLException, ClientException {
        // Check input

        // this will work only with Postgres
        String selectSQL = prstmt.toString();
        if (selectSQL == null || selectSQL.trim().equals("")) {
            throw new SQLException("Empty sql passed : \"" + selectSQL + "\"");
        }
        // Ensure the given query is a SELECT QUERY
        String selectPart = selectSQL.trim().substring(0, 6);
        if (!selectPart.equalsIgnoreCase("SELECT")) {
            throw new SQLException("Not a select query : \"" + selectSQL + "\"");
        }

        ResultSet rs = null;

        // Execute query
        try {

            if (con == null) {
                System.out.println("Connection null");
            }

            rs = prstmt.executeQuery();
            // Make detatched rowset
            sun.jdbc.rowset.CachedRowSet rowSet = new sun.jdbc.rowset.CachedRowSet();
            rowSet.populate(rs);
            // Return
            return rowSet;
        } catch (Exception sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                release();
            } catch (ClientException ex) {
                System.out.println("Error in DB Connection release");
                throw ex;
            }

        }
        return null;
    }

    public void commit() throws SQLException {
        // Check that this method is called once only
        if (commitHasBeenCalledAlready) {
            String msg = "DEV_ERROR : Please call TransactionManager.commit()" + " only once. You already have called it on the" + " same object of TransactionManager. If you want to" + " call commit() again then create a fresh object" + " -OR- call reset() on this object first.";
            throw new SQLException(msg);
        }

        // If the commit has been called only when the transaction invoves
        // only one DML, then inform the developer by logging the message.
        if (sqlList.size() <= 1) {
            // Debug.logtnx(formattedTransactionID() + " 0.o.^.o.0   This transaction" + " has only one DML. Better use" + " TransactionManager.executeDMLIndividualTnx(sql)" + "   0.o.^.o.0 " + whereiam);
        }
        // Commit has been called, so set the commitHasBeenCalledAlready
        commitHasBeenCalledAlready = true;

        // Commit now
        con.commit();

        // Control reaches here means transaction is successfull
        transactionSuccessfull = true;
        // Debug.logtnx(formattedTransactionID() + ":) ...Transaction committed successfully... " + whereiam);
        con.setAutoCommit(true);
    }

    public void release() throws ClientException {
        // Increment the release counter
        releasedTransactionCounter++;

        // Set the flag that the release method has been called
        released = true;

        // If the PreparedStatement is created by the user then close it.
        try {
            if (prstmt != null) {
                prstmt.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.rollback();
                con.close();
            }
        } catch (SQLException ex) {
            // Just log the exception and proceed
            LOGGER.error(ex);
        } finally {
            prstmt = null;
            con = null;
        }
        // Debug.logtnx(formattedTransactionID() + ":| ...Transaction Released... " + whereiam);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        prstmt = null;
        if (con == null) {
            initialize(whereiam);
        }
        prstmt = con.prepareStatement(sql);
        return prstmt;
    }

//    public PreparedStatement prepareStatement(String sql, int scroll, int conqyirency) throws SQLException {
//
//        prstmt = null;
//        prstmt = con.prepareStatement(sql, scroll, conqyirency);
//        return prstmt;
//    }
    public void reset(String whereIAm) throws SQLException, ClientException {
        // Release the resources if it is not released when the reset is called
        if (!released) {
            release();
        }

        // Initialize the object
        //initialize(whereIAm);
    }

    /**
     * Return the transactionSuccessfull flag.
     *
     * @return True if the transaction was comitted successfully else false.
     */
    public boolean isTransactionSuccessfull() {
        return transactionSuccessfull;
    }

    public boolean isReleased() {
        return released;
    }

    public String getWhereiam() {
        return whereiam;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public static int getReleasedTransactionCounter() {
        return releasedTransactionCounter;
    }

    public static int getTransactionCounter() {
        return transactionCounter;
    }

    Connection getConnectionRef() {   // package method //
        return con;
    }

    public String formattedTransactionID() {
        return "[" + FormatUtils.getRightAligned(transactionID, 6) + "] ";
    }

    public String toString() {
        // Make sql list
        StringBuffer sqls = new StringBuffer();
        int sqlCount = sqlList.size();
        for (int i = 0; i < sqlCount; i++) {
            sqls.append((String) sqlList.get(i));
            if (i != sqlCount - 1) {
                sqls.append("\n");
            }
        }

        // Make toString
        String str = "whereiam=" + whereiam + " transactionSuccessfull=" + transactionSuccessfull + " commitHasBeenCalledAlready=" + commitHasBeenCalledAlready + " transactionID=" + transactionID + " transactionCounter=" + transactionCounter + " releasedTransactionCounter=" + releasedTransactionCounter + " sqlCount=" + sqlCount + "\n" + sqls.toString();
        return str;
    }

    public static int executeDMLIndividualTnx(String iudSQL, String whereIAm, String poolName)
            throws SQLException {

        // Check input
        if (iudSQL == null || iudSQL.trim().equals("")) {
            throw new SQLException("DEV_ERROR : Empty sql passed : \"" + iudSQL + "\" : " + whereIAm);
        }

        // Ensure the given query is a either INSERT or UPDATE or DELETE or DROP TABLE (DROP T)
        // Also ensures that the qury may be a DROP query
        String iudPart = iudSQL.trim().substring(0, 6);
        if ((!iudPart.equalsIgnoreCase("INSERT")) && (!iudPart.equalsIgnoreCase("UPDATE")) && (!iudPart.equalsIgnoreCase("DELETE")) && (!iudPart.equalsIgnoreCase("DROP T"))) {

            throw new SQLException("Not an Insert/Update/Delete/Drop query : \"" + iudSQL + "\" : " + whereIAm);
        }

        // Log the SQL
        //Debug.logsql("[   1   ] " + iudSQL);
        // Connection/Statement
        Connection con = null;
        Statement stmt = null;

        // Execute query
        int nRows = 0;

        try {
            try {
                con = ConnectionPooling.getDBConnectionUserManagement();
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            stmt = con.createStatement();
            nRows = stmt.executeUpdate(iudSQL);
            if (nRows < 1 && !iudPart.equalsIgnoreCase("DROP T")) {
                throw new SQLException("No records updated/added/deleted in the DB : " + nRows + " In : " + whereIAm);
            }
        } finally {
            try {
                //returns a connection to the pool

                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ve) {
                throw new SQLException(ve.getMessage() + " : " + whereIAm);
            }
        }

        // Return
        return nRows;
    }

    public RowSet executeSelect(String sql) throws SQLException {
        // Check input
        //RowSet rs=null;
        CachedRowSet rowSet = new sun.jdbc.rowset.CachedRowSet();
        ResultSet rset = null;
        if (CommonUtils.isNullOrBlank(sql)) {
            throw new SQLException("DEV_ERROR : Null SQL found. : " + whereiam);
        }

        // Ensure that the con is still inside a transaction
        if (commitHasBeenCalledAlready) {
            throw new SQLException("DEV_ERROR : Already commited TransactionManager" + " object is used without calling reset() : " + whereiam);
        }

        // Trim sql
        sql = sql.trim();

        // Log the DML
        //Debug.logsql(formattedTransactionID() + sql);
        // Add to the list
        sqlList.add(sql);

        // Execute DML
        rset = stmt.executeQuery(sql);
        rowSet.populate(rset);
        // Return the number of rows inserted / updated / deleted
        return rowSet;
    }
}

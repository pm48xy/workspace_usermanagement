package server.db.connection;
/////////////////////////////////////////////////////////////////////////////
// CLASS            : TransactionManager
// PURPOSE          : Class used for database transaction
// NOTES            : None
// LAST MODIFIED    :
//
//////////////////////////////////////////////////////////////////////////////
// Copyright 2016 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
/////////////////////////////////////////////////////////////////////////////

/**
 *
 * @author NIC
 */
/**
 * Importing standard java classes
 */
import java.sql.*;
import java.util.*;

/**
 * Importing Client/Common java classes
 */
import nic.java.util.*;
import common.ClientException;

/**
 * Handles a database transaction.
 * <PRE>
 *     1. If single sql DML needs to be executed then user must
 *        use the static method "TransactionManager.executeDMLIndividualTnx(sql);"
 *     2. Transaction : Create an object of this class and use
 *        the methods provided. Call commit() once all the DML
 *        sqls are executed. And 'finally' release the transaction manager.
 * </PRE>
 *
 * <BR>
 * This class must be used carefully. One should call .commit() method only
 * once. If you call it more than once without calling reset() first then a
 * SQLException will be thrown. If you want to re-utilize the TransactionManager
 * object then you must first done with it (ie call commit())and then call
 * reset() method. Once you call reset() method then you are ready for using it
 * for another transaction.
 *
 * <BR>
 * User of this class should not bother about the database resoureces. Also they
 * should not bother about rollback. All user should bother about are following
 * four points...
 * <PRE>
 *   1. Create TransactionManager object  [ new TransactionManager(whereiam) ]
 *   2. Execute DML statements on it      [ tmgr.executeDML(sql) ]
 *   3. commit()
 *   4. release() in 'finally' block
 *
 * <BR>
 * <B>Here is a template...</B>
 *
 * <FONT COLOR=blue>
 * <CODE>
 *  public void myMethod() {
 *      String whereiam = "MyClass.myMethod()"
 *      TransactionManager tmgr = null;
 *
 *      try {
 *          // Create transaction manager
 *          tmgr = new TransactionManager(whereiam);
 *
 *          sql = "INSERT ....";
 *          tmgr.executeDML(sql);
 *
 *          sql = "UPDATE ...";
 *          tmgr.executeDML(sql);
 *
 *          // Commit
 *          tmgr.commit();
 *      }
 *      catch (SQLException sqle) {
 *          Debug.logexc(sqle, whereiam);   // NOTE : No rollback need to be handled. release() does that.
 *      }
 *      finally {
 *          tmgr.release();
 *      }
 *  }
 * </CODE>
 * </FONT>
 * </PRE>
 */
public class TransactionManagerRemote {
    ///////////////////////////////////////////////////////////////////////
    // VARIABLES
    ///////////////////////////////////////////////////////////////////////

    /**
     * String to show where the transaction is happening. This is mainly for
     * debugging purpose. eg
     * "PDOFormPermitGoodsRestore.updatePermitRestoreGoodsPermit()"
     */
    private String whereiam;
    /**
     * Transaction ID. Every time a transaction manager object is created a ID
     * number is assigned to it. Also when reset() method is called a new id
     * number is assigned.
     */
    private int transactionID;
    /**
     * Counter used for generating transactionID.
     */
    private static int transactionCounter = 0;
    /**
     * Counter used for remembering released transaction manager objects. This
     * is primarily for Debugging purpose.
     */
    private static int releasedTransactionCounter = 0;
    /**
     * Database connection. Connection will be called from a database connection
     * pool at the time of creation of the this object and at the time of
     * reset() method call.
     */
    private Connection con;
    /**
     * Database statement. Statement object will be created the moment
     * connection object is called from a connection pool in the
     * constructor/reset().
     */
    private Statement stmt;
    /**
     * Database PreparedStatement statement. PreparedStatement object will be
     * created when the user calls tmgr.prepareStatement(sql). User should call
     * the prepareStatement(sql) that returns this ref only once. If user calls
     * more than once then SQLException will be thrown.
     */
    private PreparedStatement prstmt;
    /**
     * List of the DML sql statements that this transaction executed.
     */
    private ArrayList sqlList = new ArrayList();
    /**
     * Flag to check if transaction finished without any exception. This flag
     * will be 'false' when this object is created and till the user calls
     * commit(). If commit() goes fine than this flag is set as 'true'
     */
    private boolean transactionSuccessfull;
    /**
     * Flag to ensure that the commit() is called only once. This flag will be
     * 'false' when this object is created and till the user calls commit()
     * irrespective of commit succeed or fail. Once commit() is called this flag
     * is set as 'true'. This is to ensure that no one call the commit() method
     * more than once. If user has to re-use this object then he must call
     * reset() method that sets this flag to 'false' alongwith other flags etc
     */
    private boolean commitHasBeenCalledAlready;
    /**
     * Flag to indicate if the transaction is released or not
     */
    private boolean released;

    ///////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////
    /**
     * Constructor.
     *
     * @param whereiam String to show from where the transaction is happening.
     * This is mainly for debugging purpose. eg
     * "PDOFormPermitGoodsRestore.updatePermitRestoreGoodsPermit()"
     *
     * @throws SQLException
     */
    public TransactionManagerRemote(String whereiam) throws SQLException {
        initialize(whereiam);
        // InstanceCount.add(this);
    }

    /**
     * Override finalize()
     */
    public void finalize() {

        // InstanceCount.remove(this);
        // Release the resources if it is not released.
        // The following code will be executed only when the developer has not
        // called tmgr.release() explicitly in his transaction handling code.
        // This is to ensure that the resources are released when garbage collection
        // occurs for the transaction manager resources that are not released by the
        // developer.
        if (!released) {

            Debug.logtnx(formattedTransactionID() + " DEV_ERROR  :  TransactionManager.release()" + " called. Developer has not called tmgr.release() explicitly." + " Please call tmgr.release() explicitly : " + whereiam);
        }
    }

    ///////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////
    /**
     * Initialize this object. This private method must be called only by
     * constructor or reset() method that makes this object just like a new
     * TransactionManager object.
     *
     * @param whereiam String showing where the transaction is happening. eg
     * "PDOFormPermitGoodsRestore.updatePermitRestoreGoodsPermit()"
     */
    private void initialize(String whereiam) throws SQLException {
        transactionID = ++transactionCounter;

        // Log the start of transaction.
        Debug.logtnx(formattedTransactionID() + ":| ...Transaction Started... " + whereiam);

        try {
            con = ConnectionPooling.getDBConnectionRemoteServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Set the autoCommit flag 'false' (transaction)
        con.setAutoCommit(false);
        // Create a statement
        stmt = con.createStatement();
        // Initialize the sqlList
        sqlList.clear();
        // Initialize whereiam
        this.whereiam = whereiam;
        // Initialize the successful transacted transaction flag
        transactionSuccessfull = false;
        // Initialize the already committed transaction flag
        commitHasBeenCalledAlready = false;
        // Initialize flag that the resoureces are assigned
        released = false;
        //Debug.logtnx(formattedTransactionID() + ":| ...Transaction Started... " + whereiam);
    }

    /**
     * Execute DML sql.
     *
     * @param sql - DML sql
     *
     * @return Number of records affected
     */
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
        Debug.logsql(formattedTransactionID() + sql);

        // Add to the list
        sqlList.add(sql);

        // Execute DML
        int numRowsAffected = stmt.executeUpdate(sql);

        // Return the number of rows inserted / updated / deleted
        return numRowsAffected;
    }

    /**
     * Commit the transaction. If the commit() goes fine then this method sets
     * the flag 'transactionSuccessfull' as true.
     *
     * @throws SQLException when commit fails
     */
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

        // Log transaction commit successfull
        Debug.logtnx(formattedTransactionID() + ":) ...Transaction committed successfully... " + whereiam);

        // Once the commit is over, set the auto-commit as true. This way when the
        // connection returns to pool no pre-cautionary rollback will be called.
        // For the sake of 'double-check' and as a 'prevention' measure we rollback the
        // transaction (connection) in PoolableDBConnectionFactory.passivateObject(con)
        // method when autoCommit is set as 'false' just before the connection is
        // returned to the pool. So now that will not be fired un-nessasarily as
        // we set the autoCommit as 'true' here.
        con.setAutoCommit(true);
    }

    /**
     * Releases the resources like stmt, prstmt, and retuns the con to
     * Connection Pool. Also rollsback the uncommitted transaction (connection)
     * if by accident such a connection is getting returned.
     *
     * @throws ClientException - when not able to release transaction resurces
     */
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
            if (con != null) {
                con.close();
            }
        } catch (SQLException sqle) {
            // Just log the exception and proceed
            Debug.logexc(sqle, whereiam);
        } finally {
            prstmt = null;
            con = null;
        }
        Debug.logtnx(formattedTransactionID() + ":| ...Transaction Released... " + whereiam);
    }

    /**
     * Returns the PreparedStatement. User should call the prepareStatement(sql)
     * only once. If user calls more than once then SQLException will be thrown.
     *
     * @param sql given prepared sql.
     *
     * @return PreparedStatement object.
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        prstmt = null;
        prstmt = con.prepareStatement(sql);
        return prstmt;
    }

    public PreparedStatement prepareStatement(String sql, int scroll, int conqyirency) throws SQLException {

        prstmt = null;
        prstmt = con.prepareStatement(sql, scroll, conqyirency);
        return prstmt;
    }

    /**
     * Reset so that this object is available for re-use.
     *
     * @param whereIAm From where the reset() is called.
     */
    public void reset(String whereIAm) throws SQLException, ClientException {
        // Release the resources if it is not released when the reset is called
        if (!released) {
            release();
        }

        // Initialize the object
        initialize(whereIAm);
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

    public static int executeDMLIndividualTnx(String iudSQL, String whereIAm)
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
                con = ConnectionPooling.getDBConnectionRemoteServer();
            } catch (Exception e) {
                e.printStackTrace();
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
}

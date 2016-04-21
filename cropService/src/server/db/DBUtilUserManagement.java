/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.db;
/////////////////////////////////////////////////////////////////////////////
// CLASS            : DBUtils
// PURPOSE          : Class used for database specific operations
// NOTES            : None
// LAST MODIFIED    :
//
//////////////////////////////////////////////////////////////////////////////
// Copyright 2009 National Informatics Centre, NIC. http://www.nic.in
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
import java.io.*;
import javax.servlet.jsp.*;
import javax.sql.*;

/**
 * Importing Client/Common java classes
 */
import nic.java.util.*;
import common.ClientException;

import server.db.connection.ConnectionPooling;

public abstract class DBUtilUserManagement {

    /**
     * Get DB Connection warnings, if any.
     *
     * @param con JDBC Connection object.
     *
     * @return SQLWarning object.
     *
     * @throws SQLException
     */
    public static SQLWarning getWarnings(Connection con) throws SQLException {
        // But still there could be some warnings. Check for and display
        // warnings, if any, generated by the connection.
        return con.getWarnings();
    }

    /**
     * Displays the database connection warnings in console, if any.
     *
     * @param warn SQLWarning object.
     */
    public static void displayWarnings(SQLWarning warn) {
        // System.out.println("\n************ W A R N I N G S*************\n");
        //checks warn is not null
        while (warn != null) {
            //    System.out.println("SQLState   : " + warn.getSQLState());
            //   System.out.println("Message    : " + warn.getMessage());
            //   System.out.println("Error Code : " + warn.getErrorCode());
            //  System.out.println();
            //retrieves the warning chained to sql warning
            warn = warn.getNextWarning();
        }
    }

    /**
     * Get and Displays the database connection warnings, if any.
     *
     * @param con JDBC Connection object.
     *
     * @return SQLWarning object.
     *
     * @throws SQLException
     */
    public static SQLWarning getAndDisplayWarnings(Connection con) throws SQLException {
        //Retrieves the first warning reported by calls on the connection and stores in warn of type SQLWarning
        SQLWarning warn = con.getWarnings();
        //checks if warn is not null
        if (warn != null) {
            //Displays the database connection warnings in console
            displayWarnings(warn);
        }

        return warn;
    }

    /**
     * Displays the Data Base Information like DB name, driver, version etc.
     *
     * @param con Database connection.
     *
     * @throws SQLException
     */
    public static void displayDatabaseInfo(Connection con) throws SQLException {
        // Get the Data Base Information
        DatabaseMetaData dbmd = con.getMetaData();
        //prints the database connection details
        System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooo");
        System.out.println("Connected to  : " + dbmd.getURL());
        System.out.println("Driver        : " + dbmd.getDriverName());
        System.out.println("Driver Version: " + dbmd.getDriverVersion());
        System.out.println("Database      : " + dbmd.getDatabaseProductName());
        System.out.println("Version       : " + dbmd.getDatabaseProductVersion());
        System.out.println("User name     : " + dbmd.getUserName());
        System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooo");
    }

    /**
     * Return the Data Base Information like DB name, driver, version etc in the
     * form of a string.
     *
     * @param con Database connection.
     *
     * @throws SQLException
     */
    public static String displayDatabaseInfoHtml(Connection con)
            throws SQLException {

        // Get the Data Base Information
        DatabaseMetaData dbmd = con.getMetaData();
        //returns the database connection details
        return "<TABLE BORDER=0 CELLSPACING=0 CELLPADING=0>" + "<TR VALIGN=TOP><TD NOWRAP>Connected to</TD>   <TD><B>:</B></TD> <TD>" + dbmd.getURL() + "</TD></TR>" + "<TR VALIGN=TOP><TD NOWRAP>Driver</TD>         <TD><B>:</B></TD> <TD>" + dbmd.getDriverName() + "</TD></TR>" + "<TR VALIGN=TOP><TD NOWRAP>Driver Version</TD> <TD><B>:</B></TD> <TD>" + dbmd.getDriverVersion() + "</TD></TR>" + "<TR VALIGN=TOP><TD NOWRAP>Database</TD>       <TD><B>:</B></TD> <TD>" + dbmd.getDatabaseProductName() + "</TD></TR>" + "<TR VALIGN=TOP><TD NOWRAP>Version</TD>        <TD><B>:</B></TD> <TD>" + dbmd.getDatabaseProductVersion() + "</TD></TR>" + "<TR VALIGN=TOP><TD NOWRAP>User name</TD>      <TD><B>:</B></TD> <TD>" + dbmd.getUserName() + "</TD></TR>" + "</TABLE>";
    }

    /**
     * Check the given sql if it executes correctly
     *
     * @param sql Given SQL to check
     *
     * @return True if SQL executes correctly else false
     *
     * @throws SQLException
     * @throws ClientException
     */
    public static void checkSQL(String sql) throws SQLException, ClientException {
        // Check input
        if (sql == null || sql.trim().equals("")) {
            throw new SQLException("Empty sql passed : \"" + sql + "\"");
        }

        // Ensure the given query is a SELECT query
        String selectPart = sql.trim().substring(0, 6);
        if (!selectPart.equalsIgnoreCase("SELECT")) {
            throw new SQLException("Not a select query : \"" + sql + "\"");
        }

        // Connection/Statement/ResultSet
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            con = ConnectionPooling.getDBConnectionUserManagement();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            throw new SQLException("Please check your query. This is not" + " correct due to following reason : " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            stmt = null;
            rs = null;
            con = null;
        }
    }

    /**
     * Write the data onto the jsp stream if given. If given null then append
     * the data into the given string buffer.
     *
     * @param data Given string data
     * @param out JspWriter Stream object. Could be null. If it is null then the
     * data is populated in the given StringBuffer.
     * @param sbuf StringBuffer object
     *
     * @throws IOException
     */
    public static void print(String data, JspWriter out,
            StringBuffer sbuf)
            throws IOException {

        print(data, out, sbuf, null);
    }

    /**
     * Write the data onto the jsp stream if given. If given null then append
     * the data into the given string buffer. It also updates report length by
     * adding the char length of the given data string.
     *
     * @param data Given string data
     * @param out JspWriter Stream object. Could be null. If it is null then the
     * data is populated in the given StringBuffer.
     * @param sbuf StringBuffer object
     * @param reportSize Char count in the Report (reportSize[0])
     * @throws IOException
     */
    public static void print(String data, JspWriter out,
            StringBuffer sbuf, int[] reportSize)
            throws IOException {

        // If the JspWriter Stream is given then directly write
        // the data onto it otherwise append it to the StringBuffer
        if (out != null) {
            out.println(data);
        } else {
            sbuf.append(data);
        }

        // Add to the size
        if (reportSize != null && reportSize.length > 0) {
            reportSize[0] += data.length();
        }
    }

    /**
     * Get the record counts in the given Scrollable ResultSet. After get the
     * count set the location before the first.
     *
     * @param rs Given Scrollable ResultSet
     *
     * @return Record count
     *
     * @throws SQLException
     */
    public static int getRecordCount(ResultSet rs)
            throws SQLException {

        // Check inputs
        if (rs == null || rs.getType() != ResultSet.TYPE_SCROLL_INSENSITIVE) {
            return 0;
        }

        // Compute the count
        int count = 0;
        while (rs.next()) {
            count++;
        }

        // Move the cursor to the front of this ResultSet object,
        // just before the first row
        rs.beforeFirst();

        // Return
        return count;
    }

    /**
     * Get the record counts in the given Scrollable RowSet. After get the count
     * set the location before the first.
     *
     * @param rs Given Scrollable RowSet
     *
     * @return Record count
     *
     * @throws SQLException
     */
    public static int getRecordCount(RowSet rs) throws ClientException {
        int count = 0;
        try {
            // Check inputs
            if (rs == null || rs.getType() != ResultSet.TYPE_SCROLL_INSENSITIVE) {
                return 0;
            }

            // Compute the count
            while (rs.next()) {
                ++count;
            }

            // Move the cursor to the front of this ResultSet object,
            // just before the first row
            rs.beforeFirst();
        } catch (SQLException sqle) {
            throw new ClientException(sqle.getMessage());
        }
        // Return
        return count;
    }

    /**
     * Displays the results from a ResultSet cursor. This is used for debugging.
     *
     * @param rs ResultSet object whose data need to be displayed.
     *
     * @throws SQLException
     */
    public static void displayResults(ResultSet rs)
            throws SQLException {

        // Get the ResultSet metadata refrence
        ResultSetMetaData rmeta = rs.getMetaData();
        int numColumns = rmeta.getColumnCount();

        // Print the Column Names
        // NOTE: Column index starts with 1 and not 0
        for (int j = 1; j <= numColumns; j++) {
            System.out.print(rmeta.getColumnName(j) + " | ");
        }
        System.out.println();

        // Print the records from the RecordSet
        while (rs.next()) {
            for (int k = 1; k <= numColumns; k++) {
                System.out.print(rs.getString(k) + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Get the Clob data in the String object
     *
     * @param rs ResultSet (Cursor) object.
     * @param colName Column Name of type CLOB
     *
     * @return String data if found, else returns null.
     *
     * @throws SQLException
     */
    public static String getClobData(ResultSet rs, String colName)
            throws SQLException {

        // Return data variable
        String data = null;

        // Get the clob data
        Clob dataClob = rs.getClob(colName);
        if (dataClob != null) {
            data = dataClob.getSubString(1, (int) dataClob.length());

            //RCN_OBSR
            // We store String data in a clob field by first putting
            // empty_clob() and then get this CLOB locator to put
            // the string content using clob.putString(1, str).
            // If the str is null then the clob.getSubString()
            // returns "" instead of null. So we are putting
            // following code... ;)
            if (data.equals("")) {
                data = null;
            }
        }

        // Return
        return data;
    }

    /**
     * Returns count(*) from a given table using the given where clause.
     *
     * @param tableName Table Name
     * @param whereClause The SQL WHERE clause string. Provide null if no where
     * clause needed.
     *
     * @return Number of the records found based on inputs
     *
     * @throws SQLException
     *
     * @throws ClientException
     */
    public static int getCountStar(String tableName,
            String whereClause)
            throws SQLException, ClientException {

        String whereiam = "DBUtils.getCountStar()";
        // Return data
        int count = 0;

        // Query
        String query = "SELECT COUNT(*) FROM " + tableName;
        if (whereClause != null) {
            query += whereClause;
        }

        // Connection/Statement/ResultSet
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Connection/Statement/ResultSet
            // con = DBConnectionPool.getConnection();
            //gets the open connection based on specified rto
            con = ConnectionPooling.getDBConnectionUserManagement();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            // There got be only one record
            rs.next();
            count = rs.getInt(1);
        } catch (Exception sqle) {
            sqle.printStackTrace();
            Debug.logexc(sqle, whereiam);

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Debug.logexc(e, whereiam);
            }
            stmt = null;
            rs = null;
            con = null;

        }

        // Return
        return count;
    }

    /**
     * Returns the Nth column name for the given table name.
     *
     * @param tname Table Name
     * @param colNum Column Number (1 or above)
     *
     * @return First column name
     *
     * @throws SQLException
     * @throws ClientException
     */
    public static String getNthColumnName(String tname, int colNum)
            throws SQLException, ClientException {
        String whereiam = "DBUtils.getNthColumnName()";

        // SQL that selects nothing but provides the structure
        String sql = "SELECT * FROM " + tname + " WHERE 1=2";
        //Debug.logsql(sql);

        // Connection/Statement/ResultSet
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        // Get the column name
        String cname = null;

        try {

            con = ConnectionPooling.getDBConnectionUserManagement();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            ResultSetMetaData rmeta = rs.getMetaData();
            int columnCount = rmeta.getColumnCount();
            if (colNum < 1 || colNum > columnCount) {
                throw new ClientException("DEV_ERROR : Given column number " + colNum + " is outside of column number range.");
            }

            cname = rmeta.getColumnName(colNum);
        } catch (Exception e) {
            Debug.logexc(e, whereiam);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Debug.logexc(e, whereiam);
            }
            stmt = null;
            rs = null;
            con = null;

        }

        // Return
        return cname;
    }

    /**
     * Returns the column names and types for the given table name.
     *
     * @param tname Table Name
     *
     * @return 2D arrays having column names and their types
     *
     * @throws SQLException
     * @throws ClientException
     */
    public static String[][] getTableStructure(String tname)
            throws SQLException, ClientException {

        // SQL that selects nothing but provides the structure
        String sql = "SELECT * FROM " + tname + " WHERE 1=2";
        String whereiam = "DBUtils.getTableStructure";
        //Debug.logsql(sql);

        // Connection/Statement/ResultSet
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        // Get the data
        String[][] data = null;

        try {
            // Connection/Statement/ResultSet
            // con = DBConnectionPool.getConnection();
            //gets the open connection based on specified rto
            con = ConnectionPooling.getDBConnectionUserManagement();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            // Get the ResultSet metadata refrence
            ResultSetMetaData rmeta = rs.getMetaData();
            int numColumns = rmeta.getColumnCount();

            // Return variable
            data = new String[numColumns][2];

            // Add the column names in this array
            for (int j = 1; j <= numColumns; j++) {
                data[j - 1][0] = rmeta.getColumnName(j);
                data[j - 1][1] = rmeta.getColumnTypeName(j);
            }
        } catch (Exception e) {
            Debug.logexc(e, whereiam);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Debug.logexc(e, whereiam);
            }
            stmt = null;
            rs = null;
            con = null;

        }

        // Return
        return data;
    }

    /**
     * Returns the max number used so far for the PK (assuming that the first
     * column is PK and is of NUMBER type) for the given table.
     *
     * @param tname Table Name
     *
     * @return Max number used so far for the PK
     *
     * @throws SQLException
     * @throws ClientException
     */
    public static int getMaxPKNumUsedSoFar(String tname)
            throws SQLException, ClientException {

        String whereiam = "DBUtils.getMaxPKNumUsedSoFar()";
        // Return variable
        int maxPKNum = 0;

        // Get the first column name. We know that the first
        // column is Primary Key (NUMBER type) in our V_M_* tables
        String pk = getNthColumnName(tname, 1);

        // SQL that selects nothing but provides the structure
        String sql = "SELECT MAX(" + pk + ") FROM " + tname;
        //Debug.logsql(sql);

        // Connection/Statement/ResultSet
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        // Get the data
        try {
            // Connection/Statement/ResultSet
            //gets the open connection based on specified rto
            con = ConnectionPooling.getDBConnectionUserManagement();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            rs.next();
            maxPKNum = rs.getInt(1);
        } catch (SQLException sqle) {
            Debug.logexc(sqle, whereiam);
            throw sqle;
        } catch (Exception e) {
            Debug.logexc(e, whereiam);

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Debug.logexc(e, whereiam);
            }
            stmt = null;
            rs = null;
            con = null;

        }

        // Return
        return maxPKNum;
    }

    /**
     * Returns the max timestamp used so far in the given audit table.
     *
     * @param tname Table Name
     * @param id ID Name
     * @param idVal ID Value
     *
     * @return Max number used so far for the PK
     *
     * @throws SQLException
     * @throws ClientException
     */
    public static String getLastAuditTimestamp(String tname, String id, String idVal)
            throws ClientException, SQLException {

        String whereiam = "DBUtils.getLastAuditTimestamp()";
        // Return Variable
        String timestamp = null;

        // SQL that selects nothing but provides the structure
        // TECH_NOTE : the following sql will return one record
        //             having empty string "" in it when there
        //             are no records for the given id in the table.
        //             So it needs to be treated carefully.
        String sql = "SELECT max(TIMESTAMP) TIMESTAMP FROM " + tname + " WHERE " + id + "=" + idVal;
        //Debug.logsql(sql);

        // Connection/Statement/ResultSet
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        // Get the data
        try {
            // Connection/Statement/ResultSet
            //gets the open connection based on specified rto
            con = ConnectionPooling.getDBConnectionUserManagement();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                timestamp = DateUtils.getDateInDDMMYYYY_HHMMSS(rs.getTimestamp("TIMESTAMP"));
            }
        } catch (SQLException sqle) {
            Debug.logexc(sqle, whereiam);
            throw sqle;
        } catch (Exception e) {
            Debug.logexc(e, whereiam);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Debug.logexc(e, whereiam);
            }
            stmt = null;
            rs = null;
            con = null;

        }
        // TECH_NOTE : See above TECH_NOTE
        if (timestamp != null && timestamp.trim().equals("")) {
            timestamp = null;
        }

        // Return
        return timestamp;
    }

    /**
     * Method to execute a SQL-SELECT statement and return a DETACHED RowSet
     * (ResultSet).
     *
     * @param selectSQL - SELECT statement to be executed.
     *
     * @return RowSet - Detached ResultSet.
     */
    public static javax.sql.RowSet fetchDetachedRowSet(String selectSQL)
            throws SQLException {
        // Check input
        if (selectSQL == null || selectSQL.trim().equals("")) {
            throw new SQLException("Empty sql passed : \"" + selectSQL + "\"");
        }
        // Ensure the given query is a SELECT QUERY
        String selectPart = selectSQL.trim().substring(0, 6);
        if (!selectPart.equalsIgnoreCase("SELECT")) {
            throw new SQLException("Not a select query : \"" + selectSQL + "\"");
        }

        // Log sql
        //Debug.logsql(selectSQL);
        // Connection/Statement/ResultSet
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        // Execute query
        try {
            // Get DB connection and create a statement
            //gets the open connection based on specified rto
            con = ConnectionPooling. getDBConnectionUserManagement();
            if (con == null) {
                System.out.println("Connection null");
            }
            stmt = con.createStatement();

            // Execute SELECT query
            rs = stmt.executeQuery(selectSQL);
            // Make detatched rowset
            sun.jdbc.rowset.CachedRowSet rowSet = new sun.jdbc.rowset.CachedRowSet();
            rowSet.populate(rs);
            // Return
            return rowSet;
        } catch (Exception sqle) {
            sqle.printStackTrace();
        } finally {
            //returns a connection to the pool
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
            stmt = null;
            rs = null;
            con = null;

        }
        return null;
    }

    /**
     * Returns Date as String. Takes a reference of the RowSet and the name of
     * the field in it. This then returns a String by checking what type of
     * database is being currently used by .
     *
     * @param rs RowSet from which the date has to be extracted
     * @param fieldName Name of the date field in the RowSet
     *
     * @return date as String
     */
    public static String getDateFromRowSet_AsString(RowSet rs, String fieldName)
            throws ClientException, SQLException {

        return DateUtils.parseDate(getDateFromRowSet_AsDate(rs, fieldName));
    }

    /**
     * Returns a java.util.Date. Takes a reference of the RowSet and the name of
     * the field in it. This then returns a java.util.Date object by checking
     * what type of database is being currently used by .
     *
     * @param rs RowSet from which the date has to be extracted
     * @param fieldName Name of the date field in the RowSet
     *
     * @return java.util.Date
     */
    public static java.util.Date getDateFromRowSet_AsDate(RowSet rs, String fieldName)
            throws ClientException, SQLException {
        //return variable
        java.util.Date returnDate = null;
        //checks if rs is not null and fieldname is not null
        if (rs != null && fieldName != null) {
            //checks if dbsystem is equal to null
            if (FormatUtils.DB_SYSTEM == null) {
                throw new ClientException("BUG : Null database found : DBUtils.getDateFromRowSet_AsDate()");
            } //checks if dbsystem is equal to DB2
            else if (FormatUtils.DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_DB2)) {
                returnDate = rs.getTimestamp(fieldName);
            } //checks if dbsystem is equal to Oracle
            else if (FormatUtils.DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_ORACLE)) {
                returnDate = (Timestamp) rs.getObject(fieldName);
            } //checks if dbsystem is equal to MSSql
            else if (FormatUtils.DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_MSSQL)) {
                //TODO_GES : To be tested on MSSQL
                returnDate = rs.getTimestamp(fieldName);
            }//checks if dbsystem is equal to Postgres
            else if (FormatUtils.DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_POSTGRES)) {
                returnDate = (Timestamp) rs.getObject(fieldName);
            } else {
                throw new ClientException("BUG : Control should never come here :" + " Unkown Db found : " + FormatUtils.DB_SYSTEM
                        + ": DBUtils.getDateFromRowSet_AsDate()");
            }
        }

        return returnDate;
    }

    /**
     * Returns string with the DB server's current date syntax These may be:- If
     * Oracle - SYSDATE If MSSQL - getDate() If DB2 - CURRENT DATE
     *
     * @return Returns string which when appended to SQL will give current date
     */
    public static String getCurrentServerDateSyntax() throws ClientException {
        //return variable
        String formattedDate = null;
        //checks if dbsystem is equal to null
        if (FormatUtils.DB_SYSTEM == null) {
            throw new ClientException("BUG : Null database found");
        } //checks if dbsystem is equal to DB2
        else if (FormatUtils.DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_DB2)) {
            formattedDate = " CURRENT DATE";
        } //checks if dbsystem is equal to Oracle
        else if (FormatUtils.DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_ORACLE)) {
            formattedDate = " SYSDATE";
        } //checks if dbsystem is equal to MSSql
        else if (FormatUtils.DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_MSSQL)) {
            formattedDate = " getDate()";
        }//checks if dbsystem is equal to Postgres
        else if (FormatUtils.DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_POSTGRES)) {
            formattedDate = " current_timestamp";
        } else {
            throw new ClientException("BUG : Control should never come here :" + " Unkown Db found : " + FormatUtils.DB_SYSTEM);
        }

        return formattedDate;
    }

    /**
     * Returns a formatted string for SELECT sql which checks for null column
     * value. And replaces it by the maximum possible value of numbers.
     *
     * @param columnName Name of the column to be replaced
     *
     * @return Returns Formatted string for SELECT sql
     */
    public static String replaceNullWithMaxNumberValue(String columnName) {
        return replaceNullWith(columnName, "999999999");
    }

    /**
     * Returns a formatted string for SELECT sql which checks for null column
     * value. And replaces it by 0 (Zero).
     *
     * @param columnName Name of the column to be replaced
     *
     * @return Returns Formatted string for SELECT sql
     */
    public static String replaceNullWithZeroValue(String columnName) {
        return replaceNullWith(columnName, "0");
    }

    /**
     * Returns a formatted string for SELECT sql which checks for null column
     * value. And replaces it by the maximum possible value of that column.
     * Note: The maxValue parameter if used for any datatype other than number
     * (like date) must be formatted by using FormatUtils.fdwc()
     *
     * The formats for null checks are:- If Oracle - NVL If MSSQL - ISNULL If
     * DB2 - COALESCE
     *
     * @param columnName Name of the column to be replaced
     * @param maxValue Maximum value to be used
     *
     * @return Returns Formatted string for SELECT sql
     */
    private static String replaceNullWith(String columnName, String maxValue) {
        String formattedSQL = columnName;

        // If database is DB2 -
        if (FormatUtils.DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_DB2)) {
            formattedSQL = " COALESCE(" + columnName + "," + maxValue + ")";
        } // If database is Oracle
        else if (FormatUtils.DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_ORACLE)) {
            formattedSQL = " NVL(" + columnName + "," + maxValue + ")";
        } // If database is SQL Server
        // TODO_JIS - Testing is pending for SQL Server
        else if (FormatUtils.DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_MSSQL)) {
            formattedSQL = " ISNULL(" + columnName + "," + maxValue + ")";
        } else if (FormatUtils.DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_POSTGRES)) {
            formattedSQL = " ISNULL(" + columnName + "," + maxValue + ")";
        } else {
            // Do Nothing
        }

        // Return
        return formattedSQL;
    }
}

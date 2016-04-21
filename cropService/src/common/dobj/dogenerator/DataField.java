package common.dobj.dogenerator;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : Data Field 
// PURPOSE          : Represents a Data field in a form.
// NOTES            : THIS CLASS IS USED TWO PLACES. 
//                    --FIRST-- by the DOGenerator.java to generate the 
//                    DOxxxx.java files based on the given XML file.
//                    --SECOND-- by the DOxxxx.java files itself.
// LAST MODIFIED    :
//  20030919 GUM019 Catching FormatUtilsException and changed package structure
//  20030811 AKS013 Auditing - TCC : Possible Errors
//  20030805 AKS012 Auditing - TCC : Performance Category
//  20030804 SIM005 Added Copyright Documentation
//  20030621 CPO004 Changed number formatting in ff() "NULL" handled
//  20030528 GES001 ff_wc() added, trimTillLastComma() corrected
//  20030522 GUM005 Added setName(), made class Serializable
//  20030519 RCN007 extends ClientObject
//  20030513 GUM002 Added ff(), makeInsertSQL(), makeUpdateSQL(), trimTillLastComma()
//  20030506 RCN005 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.io.*;
//
// Importing Common packages/classes
//
import nic.java.util.*;
import common.*;

/**
 * Represents a Data field in a form.
 * <PRE>
 * --FIRST-- by the DOGenerator.java to generate the
 * DOxxxx.java files based on the given XML file.
 *
 * --SECOND-- by the DOxxxx.java files itself.
 * </PRE>
 *
 * @author RCN
 */
public class DataField extends ClientObject implements Serializable {

    ///////////////////////////////////////////////////////////////////////
    // CONSTANTS
    ///////////////////////////////////////////////////////////////////////
    /**
     * New line
     */
    public static final String NEW_LINE = "\n";

    /**
     * Followings are the data type as mentioned in the XML file.
     */
    public static final String DBTYPE_VARCHAR = "Varchar";
    public static final String DBTYPE_NUMERIC = "Numeric";
    public static final String DBTYPE_SMALLINT = "SmallInt";
    public static final String DBTYPE_INTEGER = "Integer";
    public static final String DBTYPE_DBTIMESTAMP = "DBTimeStamp";
    public static final String DBTYPE_UNSIGNEDTINYINT = "UnsignedTinyInt";
    public static final String DBTYPE_CHAR = "Char";
    public static final String DBTYPE_DOUBLE = "Double";
    public static final String DBTYPE_SINGLE = "Single";
    /**
     * Text Type as mentioned in the XML file
     */
    public static final String TTTYPE_NUMBER = "Number";
    public static final String TTTYPE_CHARACTER = "Character";
    public static final String TTTYPE_ALPHANUMERIC = "AlphaNumeric";
    public static final String TTTYPE_DATE = "Date";
    /**
     * Data Field DB type as in database
     */
    public static final int DF_DBTYPE_INT = 1;
    public static final int DF_DBTYPE_NUMERIC = 2;
    public static final int DF_DBTYPE_CHAR = 3;
    public static final int DF_DBTYPE_VARCHAR = 4;
    public static final int DF_DBTYPE_DATE = 5;
    /**
     * Data field Text Type
     */
    public static final int DF_TTTYPE_NUMBER = 1;
    public static final int DF_TTTYPE_CHARACTER = 2;
    public static final int DF_TTTYPE_ALPHANUMERIC = 3;
    public static final int DF_TTTYPE_DATE = 4;

    ///////////////////////////////////////////////////////////////////////
    // INSTANCE VARIABLES
    ///////////////////////////////////////////////////////////////////////
    /**
     * Name of the (table) field
     */
    private String name;

    /**
     * Data type of the (table) field
     */
    private int dbtype;

    /**
     * Default value of the field
     */
    private String dv;

    /**
     * Allowed Lower limit of the field (inclusive)
     */
    private String ll;

    /**
     * Allowed Upper limit of the field (inclusive)
     */
    private String ul;

    /**
     * Text type
     */
    private int tt;

    /**
     * Field description
     */
    private String desc;

    /**
     * Is the (form) field is allowed to be nullable
     */
    private boolean formNullable;

    /**
     * Table name where the field will go
     */
    private String tableName;

    /**
     * Field value
     */
    private String value;

    ///////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////
    /**
     * This constructor must only be used by the "DOGenerator.java" program.
     * This should not be used by the "DOxxxx.java" objects code, instead the
     * next constructor should be used.
     */
    public DataField(String name, String dbtype,
            String dv, String ll, String ul,
            String tt, String desc,
            String formNullable, String tableName)
            throws DataFieldException {

        //
        // Check the data
        //
        StringBuffer err = new StringBuffer();

        // name
        if (name == null || name.length() == 0) {
            err.append("null/empty name found." + NEW_LINE);
        }
        // dbtype
        if (dbtype == null || dbtype.length() == 0) {
            err.append("null/empty dbtype found." + NEW_LINE);
        }
        // tt
        if (tt == null || tt.length() == 0) {
            err.append("null/empty tt found." + NEW_LINE);
        }
        // desc
        if (desc == null || desc.length() == 0) {
            err.append("null/empty desc found." + NEW_LINE);
        }
        // tableName
        if (tableName == null || tableName.length() == 0) {
            err.append("null/empty tableName found." + NEW_LINE);
        }
        String errStr = err.toString();
        if (!errStr.equals("")) {
            throw new DataFieldException(errStr);
        }

        // ...............
        // Format the data
        // ...............
        // dv
        if (dv != null && dv.length() == 0) {
            dv = null;
        }
        // ll        
        if (ll != null && ll.length() == 0) {
            ll = null;
        }
        // ul
        if (ul != null && ul.length() == 0) {
            ul = null;
        }
        // dbtype
        int intDbtype = 0;
        if (dbtype.equals(DBTYPE_VARCHAR)) {
            intDbtype = DF_DBTYPE_VARCHAR;
        } else if (dbtype.equals(DBTYPE_CHAR)) {
            intDbtype = DF_DBTYPE_CHAR;
        } else if (dbtype.equals(DBTYPE_SMALLINT)
                || dbtype.equals(DBTYPE_INTEGER)
                || dbtype.equals(DBTYPE_UNSIGNEDTINYINT)) {
            intDbtype = DF_DBTYPE_INT;
        } else if (dbtype.equals(DBTYPE_NUMERIC)
                || dbtype.equals(DBTYPE_DOUBLE)
                || dbtype.equals(DBTYPE_SINGLE)) {
            intDbtype = DF_DBTYPE_NUMERIC;
        } else if (dbtype.equals(DBTYPE_DBTIMESTAMP)) {
            intDbtype = DF_DBTYPE_DATE;
        } else {
            throw new DataFieldException("Unknown dbtype found : " + dbtype);
        }
        //tt
        int intTt = 0;
        if (tt.equals(TTTYPE_NUMBER)) {
            intTt = DF_TTTYPE_NUMBER;
        } else if (tt.equals(TTTYPE_CHARACTER)) {
            intTt = DF_TTTYPE_CHARACTER;
        } else if (tt.equals(TTTYPE_ALPHANUMERIC)) {
            intTt = DF_TTTYPE_ALPHANUMERIC;
        } else if (tt.equals(TTTYPE_DATE)) {
            intTt = DF_TTTYPE_DATE;
        } else {
            throw new DataFieldException("Unknown tt found : " + tt);
        }
        //formNullable
        boolean booleanFormNullable = false;
        if (formNullable == null) {
            booleanFormNullable = true;
        } else if (formNullable.length() == 0) {
            booleanFormNullable = true;
        } else if (formNullable.equals("N")) {
            booleanFormNullable = false;
        } else {
            throw new DataFieldException("Unknown formNullable found : " + formNullable);
        }

        // Initialize the data
        this.name = name;
        this.dbtype = intDbtype;
        this.dv = dv;
        this.ll = ll;
        this.ul = ul;
        this.tt = intTt;
        this.desc = desc;
        this.formNullable = booleanFormNullable;
        this.tableName = tableName;
    }

    /**
     * This constructor must only be used by the "DOxxxx.java" program of the .
     */
    public DataField(String name, int dbtype,
            String dv, String ll, String ul,
            int tt, String desc,
            boolean formNullable, String tableName)
            throws DataFieldException {

        // Initialize the data
        this.name = name;
        this.dbtype = dbtype;
        this.dv = dv;
        this.ll = ll;
        this.ul = ul;
        this.tt = tt;
        this.desc = desc;
        this.formNullable = formNullable;
        this.tableName = tableName;
    }

    ///////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////
    /**
     * Checks the value based on the data field type etc.
     *
     * @return null if data value is valid, else the error message.
     */
    public String validate() {
        StringBuffer err = new StringBuffer();

        // Check if null value is allowed if the value is null
        if (value == null) {
            if (formNullable) {
                return null;
            } else {
                err.append("Field '" + name + "' is null\n");
            }
        }

        // Based on the dbtype check that the value is of correct format
        switch (dbtype) {
            case DF_DBTYPE_INT:
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException nfe) {
                    err.append("Field '" + name + "' is not Integer\n");
                }
                break;

            case DF_DBTYPE_NUMERIC:
                try {
                    Float.parseFloat(value);
                } catch (NumberFormatException nfe) {
                    err.append("Field '" + name + "' is not Numeric\n");
                }
                break;

            case DF_DBTYPE_CHAR:
                break;

            case DF_DBTYPE_VARCHAR:
                break;

            case DF_DBTYPE_DATE:
                //RCN_TODO Check the date format and date validity
                break;

            default:
                err.append("DEV_ERROR : Field '" + name + "' is of UNKNOWN data type\n");
                break;
        }

        // Check for the error, if any
        String retErr = err.toString();
        if (retErr.equals("")) {
            retErr = null;
        }

        return retErr;
    }

    /*
     * Method to format the field as required uses the FormatUtils class.
     *
     * @return Formatted String for the DataField
     */
    public String ff_wc() throws ClientException {
        return trimTillLastComma(ff());
    }

    /*
     * Method to format the field as required uses the FormatUtils class.
     *
     * @return Formatted String for the DataField
     */
    public String ff() throws ClientException {
        String formattedStr = null;

        // Based on DB_TYPE format the string
        switch (dbtype) {
            case DF_DBTYPE_CHAR:
            case DF_DBTYPE_VARCHAR:
                formattedStr = FormatUtils.fs(value);
                break;

            case DF_DBTYPE_DATE:
                try {
                    formattedStr = FormatUtils.fd(value);
                } catch (FormatUtilsException fue) {
                    throw new ClientException(fue.getMessage());
                }
                break;

            case DF_DBTYPE_INT:
            case DF_DBTYPE_NUMERIC:
                if (value != null && value.length() > 0) {
                    formattedStr = String.valueOf(value) + ", ";
                } else {
                    formattedStr = "NULL, ";
                }
                break;

            default:
                throw new ClientException("BUG: Unkown dbtype found in ff() : " + dbtype);
        }

        return formattedStr;
    }
    /*
     * Method to format the field as required uses the FormatUtils class.
     * created for smart card only...KML--15-10-2008.
     * @return Formatted String for the DataField
     */

    public String ff_smart() throws ClientException {
        String formattedStr = null;

        // Based on DB_TYPE format the string
        switch (dbtype) {
            case DF_DBTYPE_CHAR:
            case DF_DBTYPE_VARCHAR:
                formattedStr = FormatUtils.fs_smart(value);
                break;

            case DF_DBTYPE_DATE:
                try {
                    formattedStr = FormatUtils.fd(value);
                } catch (FormatUtilsException fue) {
                    throw new ClientException(fue.getMessage());
                }
                break;

            case DF_DBTYPE_INT:
            case DF_DBTYPE_NUMERIC:
                if (value != null && value.length() > 0) {
                    formattedStr = String.valueOf(value) + ", ";
                } else {
                    formattedStr = "NULL, ";
                }
                break;

            default:
                throw new ClientException("BUG: Unkown dbtype found in ff() : " + dbtype);
        }

        return formattedStr;
    }
    //KML--15-10-2008 end

    /**
     * Make a insert statement for given list of the datafields
     *
     * @param dataFields List of Datafields
     * @param tableName Table name for which the update statement need to be
     * generated
     *
     * @return Generated SQL or null if any error occurs
     */
    public static String makeInsertSQL(DataField[] dataFields,
            String tableName)
            throws ClientException {

        // Check input
        if (dataFields == null || dataFields.length == 0) {
            return null;
        }

        // Make the sql
        int length = dataFields.length;
        StringBuffer sql = new StringBuffer();

        // Add field names part
        sql.append("INSERT INTO " + tableName + " (");
        for (int i = 0; i < length; i++) {
            sql.append(dataFields[i].getName() + ", ");
        }

        // Remove last ,
        String sqlInMaking = trimTillLastComma(sql.toString());

        // New sql StringBuffer
        sql = new StringBuffer(sqlInMaking);

        // Add closing )
        sql.append(") VALUES (");

        // Add field value part
        for (int i = 0; i < length; i++) {
            sql.append(dataFields[i].ff());
        }

        // Remove last ,
        sqlInMaking = trimTillLastComma(sql.toString());

        // Add closing )
        sqlInMaking += ")";

        // Return
        return sqlInMaking;
    }

    /**
     * Make a update statement for given list of the datafields
     *
     * @param dataFields List of Datafields
     * @param tableName Table name for which the update statement need to be
     * generated
     *
     * @return Generated SQL or null if any error occurs
     */
    public static String makeUpdateSQL(DataField[] dataFields,
            String tableName)
            throws ClientException {

        return makeUpdateSQL(dataFields, tableName, null);
    }

    /**
     * Make a update statement for given list of the datafields
     *
     * @param dataFields List of Datafields
     * @param tableName Table name for which the update statement need to be
     * generated
     * @param whereClause Where clause for the sql statement
     *
     * @return Generated SQL or null if any error occurs
     */
    public static String makeUpdateSQL(DataField[] dataFields,
            String tableName,
            String whereClause)
            throws ClientException {

        // Check input
        if (dataFields == null || dataFields.length == 0) {
            return null;
        }

        // Make the sql
        int length = dataFields.length;
        StringBuffer sql = new StringBuffer();

        // Add field names part
        sql.append("UPDATE " + tableName + " SET ");
        for (int i = 0; i < length; i++) {
            sql.append(dataFields[i].getName() + " = " + dataFields[i].ff());
        }

        // Remove last ,
        String sqlInMaking = trimTillLastComma(sql.toString());

        // Add where clause
        if (whereClause != null) {
            sqlInMaking += " " + whereClause;
        }

        // Return
        return sqlInMaking;
    }

    /**
     * Trim till last ,
     *
     * @param str Given string to process
     *
     * @return New string trimmed till last comma (last comma not included)
     */
    public static String trimTillLastComma(String str) {
        // Check input
        if (str == null) {
            return null;
        }

        // Remove last comma
        int index = str.lastIndexOf(",");
        if (index == -1) {
            return str;
        }
        return str.substring(0, index);
    }

    ///////////////////////////////////////////////////////////////////////
    // GETTERS
    ///////////////////////////////////////////////////////////////////////
    public String getName() {
        return name;
    }

    public int getDbtype() {
        return dbtype;
    }

    public String getDv() {
        return dv;
    }

    public String getLl() {
        return ll;
    }

    public String getUl() {
        return ul;
    }

    public int getTt() {
        return tt;
    }

    public String getDesc() {
        return desc;
    }

    public boolean getFormNullable() {
        return formNullable;
    }

    public String getTableName() {
        return tableName;
    }

    public String getValue() {
        return value;
    }

    ///////////////////////////////////////////////////////////////////////
    // SETTERS
    // Only setting of value and name is allowed
    ///////////////////////////////////////////////////////////////////////
    public void setValue(String value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Dump the Structure of the object
     */
    public static String dumpStructure() {
        String structure = ""
                + "name : "
                + "value : "
                + "dbtype : "
                + "dv : "
                + "ll : "
                + "ul : "
                + "tt : "
                + "desc : "
                + "formNullable : "
                + "tableName";
        return structure;
    }

    /**
     * Dump Method
     */
    public String dump() {
        String data = ""
                + name + " : "
                + value + " : "
                + dbtype + " : "
                + dv + " : "
                + ll + " : "
                + ul + " : "
                + tt + " : "
                + desc + " : "
                + formNullable + " : "
                + tableName;
        return data;
    }
}

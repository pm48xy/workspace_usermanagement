package common.db;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : MasterTableDO
// PURPOSE          : Java object representing a Master Database Table (Structure+Data).
// NOTES            : None
// LAST MODIFIED    :
//  20030919 GUM019 Changed package structure
//  20030604 CPO003 Added getCode()
//  20030602 AKS003 QA modifications
//  20030513 GUM002 Added getRow()
//  20030415 AKS002 Modified getData_Disabled() to handle NullPointerException
//  20030410 AKS001 Added getData_Disabled() and documentation
//  20030310 RCN001 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.sql.*;
import java.io.*;
//
// Importing Common java packages/classes
//
import nic.java.util.*;
import common.*;

/**
 * Converts a given master table database into Java Object (Read-Only).
 *
 * ASSUMPTION: The master table is assumed to be having the first two columns of
 * prime interest. The first column gives 'CODE' and the second gives a 'DESCR'
 * data. However the master table DO represents the entire table and stores the
 * data in the String format irrespective of the actual data type in the table.
 *
 * @author RCN
 */
public class MasterTableDO extends TableDO {

    ////////////////////////////////////////////////////////////////////////
    // CONSTANTS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Index at which the Code data lies in the table
     */
    public static final int IDX_CODE = 0;
    /**
     * Index at which the Description data lies in the table
     */
    public static final int IDX_DESCR = 1;
    /**
     * CODE column name
     */
    public static final String COL_NAME_CODE = "CODE";
    /**
     * DESCR column name
     */
    public static final String COL_NAME_DESCR = "DESCR";

    ////////////////////////////////////////////////////////////////////////
    // INSTANCE VARIABLES
    ////////////////////////////////////////////////////////////////////////
    /**
     * Data that to be used by the client in *available ordered* format
     */
    private String[][] data_AO;

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR(S)
    ////////////////////////////////////////////////////////////////////////
    /**
     * Constructor.
     *
     * @param tableName Table name
     * @param metadata Gives the column name and column data type
     * @param data Data as it is in the master table
     * @param data_AO Data that is available and is ordered as per the user.
     * *Available Order* is done using the VSM_CHOICE_ORDER
     */
    public MasterTableDO(String tableName, String[][] metadata,
            String[][] data, String[][] data_AO) {
        super(tableName, metadata, data);
        this.data_AO = data_AO;
    }

    ////////////////////////////////////////////////////////////////////////
    // METHOD(S)
    ////////////////////////////////////////////////////////////////////////
    /**
     * Gets the *Available Ordered* data.
     *
     * @return *Available Ordered* data.
     */
    public String[][] getData_AO() {
        return data_AO;
    }

    /**
     * Gets the *Disabled* data.
     *
     * @return *Disabled* data or null if no record is disabled.
     */
    public String[][] getData_Disabled() {
        // Check for empty table
        if (data == null) {
            return null;
        }

        // Get how many are disabled records
        int size = data.length - data_AO.length;
        int ncols = data[0].length;
        String[][] data_Disabled = new String[size][ncols];

        // Check the number of disabled records
        if (size <= 0) {
            return null;
        }

        // Make the list of the disabled data records
        int k = 0;
        boolean isFound = false;
        for (int i = 0; i < data.length; i++) {
            // Find if this record is there in the data_AO or not
            isFound = false;
            for (int j = 0; j < data_AO.length; j++) {
                if (data[i][0].equals(data_AO[j][0])) {
                    isFound = true;
                    break;
                }
            }

            // If the record is not there in the data_AO, add it to
            // the data_Disabled
            if (!isFound) {
                data_Disabled[k] = data[i];
                k++;
            }
        }

        // Return
        return data_Disabled;
    }

    /**
     * Gets the entire codes.
     *
     * @return Entire codes.
     *
     * @throws ClientException
     */
    public String[] getCodes() throws ClientException {
        return getDataForColumn(IDX_CODE, data);
    }

    /**
     * Gets the entire descriptions.
     *
     * @return entire descriptions.
     *
     * @throws ClientException
     */
    public String[] getDescrs() throws ClientException {
        return getDataForColumn(IDX_DESCR, data);
    }

    /**
     * Gets the *Available Ordered* codes.
     *
     * @return *Available Ordered* codes.
     *
     * @throws ClientException
     */
    public String[] getCodes_AO() throws ClientException {
        return getDataForColumn(IDX_CODE, data_AO);
    }

    /**
     * Gets the *Available Ordered* descriptions.
     *
     * @return *Available Ordered* descriptions.
     *
     * @throws ClientException
     */
    public String[] getDescrs_AO() throws ClientException {
        return getDataForColumn(IDX_DESCR, data_AO);
    }

    /**
     * Returns description for a given code.
     *
     * @param code Given code in String.
     *
     * @return Associated description.
     *
     * @throws ClientException
     */
    public String getDesc(String code) throws ClientException {
        // If the code given is null, then return empty string.
        if (code == null) {
            return "ERROR : Null code passed";
        }

        // Get the description
        for (int i = 0; i < data.length; i++) {
            if (code.equals(data[i][0])) {
                return data[i][1];
            }
        }

        // As the control reaches here, something is wrong.
        // Throw an exception.
        String msg = Debug.BUG + "DEV_ERROR : Ouch! Unknown code = " + code
                + " passed to MasterTablesDO.getDesc()";
        Debug.log(msg);
        throw new ClientException(msg);
    }

    /**
     * Returns code for a given description.
     *
     * @param desc Given description in String.
     *
     * @return Associated code.
     *
     * @throws ClientException In case of unknown description
     */
    public String getCode(String desc) throws ClientException {
        // If the desc given is null, then return empty string.
        if (desc == null) {
            return "ERROR : Null description passed";
        }

        // Get the description
        String code = null;
        for (int i = 0; i < data.length; i++) {
            if (desc.equals(data[i][1])) {
                if (code != null) {
                    throw new ClientException("ERROR : Duplicate Description found : " + desc);
                }
                code = data[i][0];
                // Iterate entire loop...
            }
        }

        // Check if EXACTLY ONE Code is found. If yes return the code
        if (code != null) {
            return code;
        }

        // As the control reaches here, something is wrong.
        // Throw an exception.
        String msg = Debug.BUG + "DEV_ERROR : Ouch! Unknown description = " + desc
                + " passed to MasterTablesDO.getCode()";
        Debug.log(msg);
        throw new ClientException(msg);
    }

    /**
     * Method to return specific row.
     *
     * @return one row from the table.
     */
    public String[] getRow(String code) {
        for (int i = 0; i < data.length; i++) {
            if (code.equals(data[i][0])) {
                return data[i];
            }
        }

        return null;
    }

    /**
     * Get the form data in the form CODE:DESCR
     *
     * @return Form data in the form CODE:DESCR in an array
     */
    public String[][] getFormData() {
        // Return null if the data_AO is null
        if (data_AO == null) {
            return null;
        }

        // Return the first two columns interface CODE and DESCR
        String[][] fd = new String[data_AO.length][2];
        for (int i = 0; i < data_AO.length; i++) {
            fd[i][0] = data_AO[i][0];
            fd[i][1] = data_AO[i][1];
        }
        return fd;
    }

    /**
     * Dump. Debug method to display the data.
     */
    public void dump() {
        // Dump TableDO data        
        super.dump();

        // Dump data as per this object (MasterTableDO)
        System.out.println("\n------------<<< " + tableName + " (Available Ordered Choice List) >>>------------");
        for (int i = 0; i < metadata.length; i++) {
            for (int j = 0; j < metadata[0].length; j++) {
                System.out.print(metadata[i][j]);
                if (j != metadata[0].length - 1) {
                    System.out.print(" : ");
                }
            }
            System.out.println();
        }

        System.out.println();
        for (int i = 0; data_AO != null && i < data_AO.length; i++) {
            for (int j = 0; j < data_AO[0].length; j++) {
                System.out.print(data_AO[i][j]);
                if (j != data_AO[0].length - 1) {
                    System.out.print(" : ");
                }
            }
            System.out.println();
        }
    }
}

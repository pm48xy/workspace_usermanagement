package common.db;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : TableDO
// PURPOSE          : Java object representing a Database Table (Structure+Data).
// NOTES            : None
// LAST MODIFIED    :
//  20160112 AMR Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2016 National Informatics Centre, NIC. http://www.nic.in
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
import common.*;

/**
 * Converts a given table database into Java Object (Read-Only).
 *
 * @author RCN
 */
public class TableDO extends ClientObject implements Serializable {

    ////////////////////////////////////////////////////////////////////////
    // INSTANCE VARIABLES
    ////////////////////////////////////////////////////////////////////////
    /**
     * DB table name
     */
    protected String tableName;

    /**
     * MetaData to give column names and column types
     */
    protected String[][] metadata;

    /**
     * Data exactly as it is in the table
     */
    protected String[][] data;

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR(S)
    ////////////////////////////////////////////////////////////////////////
    /**
     * Constructor.
     *
     * @param tableName Table name
     * @param metadata Gives the column name and column data type
     * @param data Data as it is in the table
     */
    public TableDO(String tableName, String[][] metadata, String[][] data) {
        this.tableName = tableName;
        this.metadata = metadata;
        this.data = data;
    }

    ////////////////////////////////////////////////////////////////////////
    // METHOD(S)
    ////////////////////////////////////////////////////////////////////////
    /**
     * Gets the name of the table.
     *
     * @return Table name.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Gets the metadata.
     *
     * <PRE>
     *  String[0][0..n] = Designated column's name
     *  String[1][0..n] = Designated column's database-specific type name
     *  String[2][0..n] = Designated column's SQL type (See java.sql.Types)
     *  String[3][0..n] = Designated column's normal maximum display width in characters
     *
     *  String[2][0..n] contains int in the String form (java.sql.Types)
     * </PRE>
     *
     * @return Metadata array. First row (String[0][i]) contain ColumnNames,
     * Second row (String[1][i]) contain ColumnTypeNames and Third row
     * (String[2][i] contain ColumnTypes
     *
     * @see <A HREF="java.sql.Types">java.sql.Types</A>
     */
    public String[][] getMetadata() {
        return metadata;
    }

    /**
     * Gets the entire data.
     *
     * @return Entire data as it is in the Table
     */
    public String[][] getData() {
        return data;
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
     * Returns data for the given column index from the given data.
     *
     * @param colIdx Column index for which the data need to be returned.
     *
     * @return the data
     */
    protected static String[] getDataForColumn(int colIdx, String[][] tableData)
            throws ClientException {

        // Check the index
        if (colIdx > tableData.length - 1 || colIdx < 0) {
            throw new ClientException("Out of range Column Index : getDataForColumn()");
        }

        // Get the data
        String[] coldata = new String[tableData.length];
        for (int i = 0; i < coldata.length; i++) {
            coldata[i] = tableData[i][colIdx];
        }

        // Return the data
        return coldata;
    }

    /**
     * Get the number of columns in the table
     *
     * @return Number of column count in the table
     */
    public int getColumnCount() {
        if (metadata == null || metadata.length == 0) {
            return 0;
        }

        return metadata[0].length;
    }

    /**
     * Dump. Debug method to display the data.
     */
    public void dump() {
        System.out.println("\n------------<<< " + tableName + " >>>------------");
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
        for (int i = 0; data != null && i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(data[i][j]);
                if (j != data[0].length - 1) {
                    System.out.print(" : ");
                }
            }
            System.out.println();
        }
    }
}

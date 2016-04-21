package common;
///////////////////////////////////////////////////////////////////////////////
// CLASS            : TableConstants
// PURPOSE          : Defines constants specific to database table structure.
// NOTES            : None
// LAST MODIFIED    :
//  20160111 Ambrish Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2016 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//
// NONE
//
// Importing Common java packages/classes
//
// NONE

/**
 * Defines constants specific to database table structure. SYNC_POINT : The
 * values here must be in sync with the Database/ Table Structures.
 *
 */
public interface TableConstants {

    // COLUMN INDEX
    // <IDX>_<TABLE_NAME>_<COLUMN_NAME> : Tells at wthat index the column
    // is in the database table
    // e.g. IDX_VM_VEH_CATG_FEE = 2 : Index of column 'FEE' in table
    //      'VM_VEH_CATG' is 2
    //......................................................................
    /**
     * Column index for CODE in VM_xxxxx tables
     */
    int IDX_VM_XXXXX_CODE = 0;          //<---- Common to all VM_xxxxx tables

    /**
     * Column index for DESCR in VM_xxxxx tables
     */
    int IDX_VM_XXXXX_DESCR = 1;          //<---- Common to all VM_xxxxx tables

    /**
     * Table to store state code and state name.
     */
    String STATE_MAST = "state_mast";
    
    /**
     * Table to store state code, dist_cd and dist name.
     */
    String DIST_MAST = "dist_mast";
    

}

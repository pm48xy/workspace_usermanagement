package common;
///////////////////////////////////////////////////////////////////////////////
// CLASS            : OperationIds
// PURPOSE          : Defines static constants which map operation Ids 
//                    for ServerHandler.handleProcess(), ServerHandler.makeDO()
// NOTES            : None
//  20160113 AMR Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
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
 * Defines static constants which map operation Ids for
 * ServerHandler.handleProcess(), ServerHandler.makeDO()
 *
 * @author GUM
 */
public interface OperationIds {
    //================================================================
    // TECH_NOTE:    
    // All interface attributes are implicitly "public static final"
    // All interface methods    are implicitly "public abstract"
    //================================================================

    //////////////////////////////////////////////////////////////////////////
    // handleProcess() operation Ids
    //////////////////////////////////////////////////////////////////////////
    //........................
    //Data entry module
    //........................
    // form holding data entry
    int P_HOLDING_DATA = 1;
    int P_CROP_DATA = 2;

    // master data related operation ids
    int M_STATE_MASTER_DATA = 1;
    int M_DISTRICT_MASTER_DATA = 2;
    int M_TEHSIL_MASTER_DATA = 3;
    int M_VILLAGE_MASTER_DATA = 4;
    int M_CROP_MASTER_DATA = 5;

}

package common;
///////////////////////////////////////////////////////////////////////////////
// CLASS            : ServiceIds
// PURPOSE          : Defines static constants which map services 
//                    to the menu options / managed bean constructor
// NOTES            : None
// LAST MODIFIED    :

//  20160113 AMR Created
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
 * Provides the constants which map menu options to the services. These
 * constants are used to find out which service is called.
 *
 * SYNC_POINT : The values here must be in sync with the Database table
 * VSM_SERVICES.
 *
 * @author JIS
 */
public interface ServiceIds {
    //================================================================
    // TECH_NOTE:    
    // All interface attributes are implicitly "public static final"
    // All interface methods    are implicitly "public abstract"
    //================================================================

    //----------------------------------------------------------------
    // 1-50   : System Services (eg Login, Logout, Exit, L&F, Options..
    // 51-900 : Form Services (Fee, Fitness, Registration, Tax, Permit forms)
    //----------------------------------------------------------------
    // Start Module
    //........................
    int STR_EDIT_PROPERTIES = 51;  // STR_ means 'Start' menu

    //Admin Module
    //........................     
    int ADM_CONFIGURE_ROLES = 101;
    int ADM_CONFIGURE_USERS = 102;
    int ADM_CHANGE_PASSWORD = 103;
    int ADM_VIEW_SESSIONS = 104;
    int ADM_MASTER_TABLE_CHOICE_EDITOR = 105;

    // Data Entry Module
    int DATA_ENTRY = 201;

    int MASTER_DATA = 301;

}

package common.context;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : UserServicesException
// PURPOSE          : Exception for invalid choice
// NOTES            : None
// LAST MODIFIED    :
//  20030804 SIM005 Added Copyright Documentation
//  20030424 JIS002 Created
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

import common.*;

/**
 * Exception class to check invalid choice.
 *
 * @author JIS
 */
public class UserServicesException extends ClientException {

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR(S)
    ////////////////////////////////////////////////////////////////////////    
    /**
     * Creates a new instance of UserServicesException.
     */
    public UserServicesException() {
        super();
    }

    /**
     * Creates a new instance of UserServicesException.
     *
     * @param msg Message for the exception object created.
     */
    public UserServicesException(String msg) {
        super(msg);
    }
}

package common.context;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : UserAuthenticationException
// PURPOSE          : Exception for user authentication
// NOTES            : None
// LAST MODIFIED    :
//  20030519 Added "Copyright 2003 National Informatics...."
//  20030429 JIS002 Created
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
 * Used for user authentication failure. Exception is created when there is
 * invalid userid or password. Also created when the user is disabled and tries
 * to log on.
 *
 * @author JIS
 */
public class UserAuthenticationException extends ClientException {

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR(S)
    ////////////////////////////////////////////////////////////////////////    
    /**
     * Creates a new instance of UserAuthenticationException.
     */
    public UserAuthenticationException() {
        super();
    }

    /**
     * Creates a new instance of UserAuthenticationException.
     *
     * @param msg Message for the exception object created.
     */
    public UserAuthenticationException(String msg) {
        super(msg);
    }
}

package common;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : ClientMessageException
// PURPOSE          : Top parent expception class for Client application
// NOTES            : None
// LAST MODIFIED    :
//  20160112 AMR Created
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
 * Exception class for handling client messages
 */
public class ClientMessageException extends ClientException {

    /**
     * Constructor
     */
    public ClientMessageException() {
        super();
    }

    /**
     * Constructor
     */
    public ClientMessageException(String msg) {
        super(msg);
    }
}

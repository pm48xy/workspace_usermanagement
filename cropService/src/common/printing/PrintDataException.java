package common.printing;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : PrintDataException
// PURPOSE          : Exception class for printing process
// NOTES            : None
// LAST MODIFIED    :
//  20030409 RCN002 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//
// NONE
//
// Importing Client/Common java packages/classes
//

import common.*;

/**
 * Exception class for printing process
 *
 * @author RCN
 */
public class PrintDataException extends ClientException {

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////
    public PrintDataException() {
        super();
    }

    public PrintDataException(String msg) {
        super(msg);
    }
}

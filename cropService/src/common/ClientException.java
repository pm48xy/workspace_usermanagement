package common;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : ClientException
// PURPOSE          : Top parent expception class for Client
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

import nic.java.util.InstanceCount;

/**
 * Top parent expception class for Application Server
 *
 * @author RCN
 */
public class ClientException extends Exception {

    /**
     * Constructor
     */
    public ClientException() {
        super();
        InstanceCount.add(this);
    }

    /**
     * Constructor
     */
    public ClientException(String msg) {
        super(msg);
        InstanceCount.add(this);
    }

    /**
     * Override finalize()
     */
    public void finalize() {
        InstanceCount.remove(this);
    }
}

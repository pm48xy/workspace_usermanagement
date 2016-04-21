package common;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : ClientRuntimeException
// PURPOSE          : Top parent runtime expception class for 
// NOTES            : None
// LAST MODIFIED    :
// 20160112 AMR Created
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
 * Top parent runtime expception class for Client
 *
 * @author RCN
 */
public class ClientRuntimeException extends RuntimeException {

    /**
     * Constructor
     */
    public ClientRuntimeException() {
        super();
        InstanceCount.add(this);
    }

    /**
     * Constructor
     */
    public ClientRuntimeException(String msg) {
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

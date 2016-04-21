package common;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : ClientObject
// PURPOSE          : Parent of all the classes specific to 
//                    (not really, wherever possible)
// NOTES            : None
// LAST MODIFIED    :
//  20160112  AMR Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2016 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//
// NONE
//
// Importing Server/Common java packages/classes
//

import nic.java.util.InstanceCount;

/**
 * Parent of all the classes specific to Client (not really, wherever possible)
 *
 * @author RCN
 */
public abstract class ClientObject {

    /**
     * Constructor
     */
    public ClientObject() {
        InstanceCount.add(this);
    }

    /**
     * Override finalize()
     */
    public void finalize() {
        InstanceCount.remove(this);
    }
}

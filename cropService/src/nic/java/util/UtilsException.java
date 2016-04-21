package nic.java.util;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : UtilsException
// PURPOSE          : Date Utils Exception
// NOTES            : None
// LAST MODIFIED    :
//  20030919 GUM019 Package re-structuring
//  20030519 RCN007 InstanceCount added
//  20030306 RCN001 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//
// NONE 

/**
 * Top level Exception for utility methods in Utils classes
 *
 * @author RCN
 */
public class UtilsException extends Exception {

    /**
     * Constructor
     */
    public UtilsException() {
        super();
        InstanceCount.add(this);
    }

    /**
     * Constructor
     */
    public UtilsException(String msg) {
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

package nic.java.util;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : FormatUtilsException
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
 * Exception for Format utility methods.
 *
 * @author RCN
 */
public class FormatUtilsException extends UtilsException {

    /**
     * Constructor
     */
    public FormatUtilsException() {
        super();
        InstanceCount.add(this);
    }

    /**
     * Constructor
     */
    public FormatUtilsException(String msg) {
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

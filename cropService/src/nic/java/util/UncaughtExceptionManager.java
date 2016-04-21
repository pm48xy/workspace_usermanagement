package nic.java.util;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : UncaughtExceptionManager
// PURPOSE          : Handles uncaught exceptions
// NOTES            : None
// LAST MODIFIED    :
//  20030919 GUM019 Package re-structuring
//  20030310 RCN001 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.io.*;

/**
 * Handles uncaught exceptions.
 *
 * @author RCN
 */
public class UncaughtExceptionManager extends ThreadGroup {

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Constructor
     */
    public UncaughtExceptionManager(String name) {
        super(name);
    }

    /**
     * Constructor
     */
    public UncaughtExceptionManager(ThreadGroup parent, String name) {
        super(parent, name);
    }

    ////////////////////////////////////////////////////////////////////////
    // METHODS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Override the uncaughtException to handle the uncaught exceptions in the
     * code.
     */
    public void uncaughtException(Thread t, Throwable e) {
        Debug.logexc(e);

        if (e instanceof java.lang.ThreadDeath) {
            Debug.logexc(e, "Returning....");
            return;
        }

        String strace = CommonUtils.getStackTrace(e);
        Debug.logexc(e, CommonUtils.NEW_LINE + strace);
    }
}

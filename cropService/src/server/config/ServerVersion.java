/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.config;
/////////////////////////////////////////////////////////////////////////////
// CLASS            : ServerVersion

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// PURPOSE          : Class used for server details
// NOTES            : None
// LAST MODIFIED    :
//
//////////////////////////////////////////////////////////////////////////////
// Copyright 2009 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
/////////////////////////////////////////////////////////////////////////////
/**
 * This class is used to server details
 *
 * @author NIC
 */
public abstract class ServerVersion {
    /*
     * VERSION HISTORY
     * 2.0 : 15 Aug 2003
     */

    /**
     * Name of the server application
     */
    public static final String SERVER_NAME = "UP CHECKPOST";
    /**
     * Version of the server application
     */
    public static final String SERVER_VERSION = "1.0";
    /**
     * Version flag (if any) of the server application. It should have empty
     * string in case of release build.
     */
    public static final String SERVER_VERSION_FLAG = "Beta 1";
    static Logger LOGGER = LogManager.getLogger(ServerVersion.class);
    /**
     * Build number for the server application. The format for version is
     * N.N.N.MM eg. 2.0.0 . For Bug fix releases the third digit will increase
     * eg. 2.0.1 . For Small features and when the number of bug fixed is high
     * the second digit increases eg. 2.1.0 . When something major happens in
     * the software in terms of workflow, new major features the first digit
     * increases eg. 3.0.0 .
     *
     * For development builds the last 2 digits will be used for internal
     * purpose only. Every build will have a number eg. For first build the
     * number is 01 and so forth. Whenever any of the version digit changes out
     * of N.N.N the NN number is reset to 01. Therefore for the first build of
     * 2.0.0 version is 20001. NNNMM
     */
    public static final int SERVER_BUILD_NUMBER = 1; //2.0.0.04
    /**
     * Build date of the server application
     */
    public static final String SERVER_BUILD_DATE = "(11 Jan 2016)";

    /**
     * Properties Dump. Prints the values of all the properties Used for
     * Debugging.
     */
    public static void dump() {
        //System.out.println(dataDump());
        LOGGER.info(dataDump());
    }

    /**
     * Properties Dump. Prints the values of all the properties Used for
     * Debugging.
     */
    public static String dataDump() {
        return ""
                + "SERVER_NAME                        = " + SERVER_NAME + "\n"
                + "SERVER_VERSION                     = " + SERVER_VERSION + "\n"
                + "SERVER_VERSION_FLAG                = " + SERVER_VERSION_FLAG + "\n"
                + "SERVER_BUILD_NUMBER                = " + SERVER_BUILD_NUMBER + "\n"
                + "SERVER_BUILD_DATE                  = " + SERVER_BUILD_DATE;
    }
}

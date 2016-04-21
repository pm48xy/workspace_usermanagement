package server.util;
/////////////////////////////////////////////////////////////////////////////
// CLASS            : ServerUtils
// PURPOSE          : Class used for Server Utilities
// NOTES            : None
// LAST MODIFIED    :
//
//////////////////////////////////////////////////////////////////////////////
// Copyright 2009 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
/////////////////////////////////////////////////////////////////////////////

/**
 * Importing Client/Common java classes
 */
import java.rmi.RemoteException;
import nic.java.util.Debug;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.config.ApplicationConfig;

/**
 * This class is used to Server Utilities
 *
 * @author tapas
 */
public abstract class ServerUtils {
    /*
     * VERSION HISTORY
     * 2.0 : 15 Aug 2003
     */

    /**
     * Server Name
     */
    static Logger LOGGER = LogManager.getLogger(ServerUtils.class);
    public static final String SERVER_NAME = "UP CHECKPOST";
    /**
     * Server version
     */
    public static final String SERVER_VERSION = "1.0";
    /**
     * Server version date used for version identifier
     */
    public static final String SERVER_VERSION_IDENTIFIER = "(15 FEB 2012)";

    /**
     * Helper method for printing stack trace
     */
    public static void pst(Throwable t) {
        if (ApplicationConfig.DEBUG_PRINT_STACK_TRACE) {
            //Debug.log(Debug.EXC +"------------pst : server-------------");
            LOGGER.info(Debug.EXC + "------------pst : server-------------");
            t.printStackTrace();
            //LOGGER.info(t.printStackTrace());
        }
    }

    /**
     * Get the current server date
     *
     * @return Current server date
     *
     * @throws RemoteException
     */
    public static java.util.Date getCurrentServerDate() {
        return new java.util.Date();
    }
}

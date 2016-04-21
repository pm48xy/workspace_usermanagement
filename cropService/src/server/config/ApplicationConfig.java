package server.config;
/////////////////////////////////////////////////////////////////////////////
// CLASS            : ApplicationConfig
// PURPOSE          : Class used for  configuring application properties like state code, rto code,  version,connection url,username,password,maximum connection etc
// NOTES            : None
// LAST MODIFIED    :
//
//////////////////////////////////////////////////////////////////////////////
// Copyright 2009 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
/////////////////////////////////////////////////////////////////////////////

/**
 * Importing standard java classes
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.MissingResourceException;

/**
 * Importing Client/Common java classes
 */
import nic.java.util.Debug;
import nic.java.util.FormatUtils;
import nic.java.util.InstanceCount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is used for configuring application properties like state code,
 * rto code, version,connection url,username,password,maximum connection etc
 *
 * @author NIC
 */
public class ApplicationConfig {

    ////////////////////////////////////////////////////////////////////////
    // INSTANCE VARIABLES
    ////////////////////////////////////////////////////////////////////////
    public static final String MERCHANT_CODE = "104";
    public static final int MERCHANT_CODE_NEW = 104;
    public static final String ENC_DEC_KEY = "o@6^IND&";
    //for testing public static final String MERCHANT_CODE = "nat_permit";
    private static final String PROPERTY_FILE = "db";
    public static String RTO_PREFIX;
    public static String STATE_CD;
    public static String RTO_CD;
    public static String _VERSION;
    public static String DATABASE_DRIVER;
    public static String CONNECTION_URL;
    public static String USER_NAME;
    public static String USER_PASSWORD;
    public static int MAX_CON;
    public static String SERVER_IP;
    public static String RDBMS_NAME;
    public static int REGISTRY_PORT;
    public static int USER_SESSION_TIMEOUT_SEC;
    public static int EDIT_SESSION_TIMEOUT_SEC;
    public static int DB_TYPE;
    public static String TABLE_VT;
    public static String TABLE_VM;
    public static String TABLE_VH;
    public static String TABLE_VST;
    public static boolean DEBUG = false;
    public static boolean DEBUG_PRINT_STACK_TRACE = false;
    public static String LOG_FILE;
    static Logger LOGGER = LogManager.getLogger(ApplicationConfig.class);

    /**
     * constructor
     *
     * @param stateCode
     * @param rtoCode
     * @param Version
     * @param databaseDriver
     * @param connectionURL
     * @param userName
     * @param userPassword
     * @param maxCon
     * @param serverIP
     * @param rdbmasName
     * @param registryPort
     * @param userSessionTimeOut
     * @param editSessionTimeOut
     * @param dbType
     * @param logFile
     * @param rtoPrefix
     */
    public ApplicationConfig(String stateCode,
            String rtoCode,
            String Version,
            String databaseDriver,
            String connectionURL,
            String userName,
            String userPassword,
            int maxCon,
            String serverIP,
            String rdbmasName,
            int registryPort,
            int userSessionTimeOut,
            int editSessionTimeOut,
            int dbType,
            String logFile,
            String rtoPrefix) {

        init(stateCode, rtoCode, Version, databaseDriver, connectionURL, userName,
                userPassword, maxCon, serverIP, rdbmasName, registryPort, userSessionTimeOut,
                editSessionTimeOut, dbType, logFile, rtoPrefix);

    }

    /**
     * Initializes the fields
     *
     * @param stateCode
     * @param rtoCode
     * @param Version
     * @param databaseDriver
     * @param connectionURL
     * @param userName
     * @param userPassword
     * @param maxCon
     * @param serverIP
     * @param rdbmasName
     * @param registryPort
     * @param userSessionTimeOut
     * @param editSessionTimeOut
     * @param dbType
     * @param logFile
     * @param rtoPrefix
     */
    private void init(String stateCode, String rtoCode, String Version,
            String databaseDriver, String connectionURL, String userName,
            String userPassword, int maxCon, String serverIP,
            String rdbmasName, int registryPort, int userSessionTimeOut,
            int editSessionTimeOut, int dbType, String logFile, String rtoPrefix) {

        STATE_CD = stateCode;
        RTO_CD = rtoCode;
        _VERSION = Version;
        DATABASE_DRIVER = databaseDriver;
        CONNECTION_URL = connectionURL;
        USER_NAME = userName;
        USER_PASSWORD = userPassword;
        MAX_CON = maxCon;
        SERVER_IP = serverIP;
        RDBMS_NAME = rdbmasName;
        REGISTRY_PORT = registryPort;
        USER_SESSION_TIMEOUT_SEC = userSessionTimeOut;
        EDIT_SESSION_TIMEOUT_SEC = editSessionTimeOut;
        DB_TYPE = dbType;
        LOG_FILE = logFile;
        RTO_PREFIX = rtoPrefix;

        if (Version.equalsIgnoreCase("2.0")) {
            TABLE_VT = "VT_";
            TABLE_VM = "VM_";
            TABLE_VH = "VH_";
            TABLE_VST = "VST_";
        } else if (Version.equalsIgnoreCase("1.0")) {
            TABLE_VT = "";
            TABLE_VM = "";
            TABLE_VH = "";
            TABLE_VST = "";
        }

        try {
            String server_DEBUG = "true";
            String server_DEBUG_PRINT_STACK_TRACE = "true";
            if (server_DEBUG.equalsIgnoreCase("true")) {
                DEBUG = true;
            }
            if (server_DEBUG_PRINT_STACK_TRACE.equalsIgnoreCase("true")) {
                DEBUG_PRINT_STACK_TRACE = true;
            }
            ServerVersion.dump();
            //calls the dump method which Prints the values of all the properties
            dump();
        } catch (MissingResourceException e) {
            String msg = "Please provide \"" + e.getKey() + "\" value in the " + PROPERTY_FILE + ".properties file";
            String className = e.getClassName();
            String key = e.getKey();
            throw new MissingResourceException(msg, className, key);
        }

        FormatUtils.DB_SYSTEM = RDBMS_NAME;
        InstanceCount.DO_COUNTING = DEBUG;
        setLogFileStream();
    }

    public static void dump() {
        //System.out.println(dataDump());
        LOGGER.info(dataDump());
    }

    public static String dataDump() {
        return "" + "SERVER_HOST                        = " + SERVER_IP + "\n" + "REGISTRY_PORT                      = " + REGISTRY_PORT + "\n" + "DB_SYSTEM                          = " + RDBMS_NAME + "\n" + "DB_DRIVER                          = " + DATABASE_DRIVER + "\n" + "DB_URL                             = " + CONNECTION_URL + "\n" + "DB_LOGIN                           = " + USER_NAME + "\n" + "DB_PASSWORD                        = " + "******" /*DB_PASSWORD*/ + "\n" + "LOG_FILE                           = " + LOG_FILE + "\n" + "USER_SESSION_TIMEOUT_SEC           = " + USER_SESSION_TIMEOUT_SEC + "\n" + "EDIT_SESSION_TIMEOUT_SEC           = " + EDIT_SESSION_TIMEOUT_SEC + "\n" + "DEBUG                              = " + DEBUG + "\n" + "DEBUG_PRINT_STACK_TRACE            = " + DEBUG_PRINT_STACK_TRACE + "\n";
    }

    public static void setLogFileStream() {
        // If the LOG_FILE is null or empty (ie no log file is given), just return
        if (LOG_FILE == null || LOG_FILE.trim().equals("")) {
            return;
        }

        // If the log file is given and there already exists the same
        // file, then take a backup of the file
        try {
            File logFile = new File(LOG_FILE);
            if (logFile.exists() && logFile.isFile()) {
                // Rename
                int indexOfDot = LOG_FILE.lastIndexOf('.');
                String extension = LOG_FILE.substring(indexOfDot, LOG_FILE.length());
                String fileName = LOG_FILE.substring(0, indexOfDot);
                String newFileName = fileName + "_" + System.currentTimeMillis() + extension;
                logFile.renameTo(new File(newFileName));
                System.out.println("\nLog file back-up taken : " + newFileName);
            }
        } catch (Exception e) {
            // Simply ignore if the above renaming fails.
            // At the most the log file backup will not be taken.
            System.out.println("Failed to take backup of log file : " + LOG_FILE);
        }

        // Redirect the System.out to a log file instead of Standard Output
        try {
            FileOutputStream fos = new FileOutputStream(LOG_FILE);
            PrintStream ps = new PrintStream(fos);
            System.out.println("\nCheck " + LOG_FILE + " for Server Specific log/error messages");
            System.setOut(ps);   // Standard Output
            System.setErr(ps);   // Standard Error
        } catch (Exception e) {
            //*** Using Console as Standard Output ***"
            Debug.log(Debug.INF + "Using Console as Standard Output. " + e.getMessage());
        }
    }
}

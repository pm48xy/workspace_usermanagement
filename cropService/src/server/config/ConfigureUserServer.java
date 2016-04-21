package server.config;
/////////////////////////////////////////////////////////////////////////////
// CLASS            : ConfigureUserServer
// PURPOSE          : Class used for configuring db.properties file
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
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is used for configuring db.properties file
 *
 * @author NIC
 */
public class ConfigureUserServer {

    /**
     * constructor
     *
     * @param prefix
     */
    public ConfigureUserServer(String prefix) {
        Properties dbProps = new Properties();
        Logger LOGGER = LogManager.getLogger(ConfigureUserServer.class);

        try {

            FacesContext fcontext = FacesContext.getCurrentInstance();
            ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();
            //gets the path of db.properties file and stores in propPath of String type
            String propPath = scontext.getRealPath("/config/db.properties");
            //loads db.properties file
            dbProps.load(new FileInputStream(propPath));
//            dbProps.load(new FileInputStream("/home/tapas/Projects/nationalpermit/web/config/db.properties"));
            //get the drivers and stores in driver of type String
            String driver = dbProps.getProperty("drivers");
            //get the specified logfile and stores in logFile of type String
            String logFile = dbProps.getProperty("logfile", "DBConnectionManager.log");
            //returns an enumeration of all keys in property list and stores in propNames of type Enumeration
            Enumeration propNames = dbProps.propertyNames();
            //checks if enumeration contains more elements
            while (propNames.hasMoreElements()) {
                String name = (String) propNames.nextElement();
                if (name.endsWith(".url")) {
                    String poolName = name.substring(0, name.lastIndexOf("."));
                    if (poolName.equalsIgnoreCase(prefix)) {
                        String dbUrl = dbProps.getProperty(poolName + ".url");
                        if (dbUrl == null) {
                            continue;
                        }
                        //gets the statecode from db.properties file
                        String stateCode = dbProps.getProperty(poolName + ".statecode");
                        //gets the statename from db.properties file
                        String stateName = dbProps.getProperty(poolName + ".statename");
                        //gets the rtocode from db.properties file
                        String rtoCode = dbProps.getProperty(poolName + ".rtocode");
                        //gets the rtoname from db.properties file
                        String rtoName = dbProps.getProperty(poolName + ".rtoname");
                        //gets the version from db.properties file
                        String Version = dbProps.getProperty(poolName + ".version");
                        //gets the dbdriver from db.properties file
                        String databaseDriver = dbProps.getProperty(poolName + ".dbdriver");
                        //gets the url from db.properties file
                        String connectionURL = dbProps.getProperty(poolName + ".url");
                        //gets the username from db.properties file
                        String userName = dbProps.getProperty(poolName + ".username");
                        //gets the password from db.properties file
                        String userPassword = dbProps.getProperty(poolName + ".password");
                        int maxCon = dbProps.getProperty(poolName + ".maxconn") == null ? Integer.parseInt(dbProps.getProperty(poolName + ".statename")) : 10;
                        String serverIP = dbProps.getProperty(poolName + ".serverip");
                        String rdbmasName = dbProps.getProperty(poolName + ".rdbmsname");
                        int registryPort = dbProps.getProperty(poolName + ".registryport") == null ? Integer.parseInt(dbProps.getProperty(poolName + ".registryport")) : 2222;
                        int userSessionTimeOut = dbProps.getProperty(poolName + ".usersessiontimeout") == null ? Integer.parseInt(dbProps.getProperty(poolName + ".usersessiontimeout")) : 3600;
                        int editSessionTimeOut = dbProps.getProperty(poolName + ".editsessiontimeout") == null ? Integer.parseInt(dbProps.getProperty(poolName + ".editsessiontimeout")) : 3600;
                        int dbType = dbProps.getProperty(poolName + ".statename") == null ? Integer.parseInt(dbProps.getProperty(poolName + ".statename")) : 4;
                        new ApplicationConfig(stateCode, rtoCode, Version, databaseDriver,
                                connectionURL, userName, userPassword, maxCon,
                                serverIP, rdbmasName, registryPort, userSessionTimeOut,
                                editSessionTimeOut, dbType, logFile, prefix);
                        //concatenates the pool name, connection url and username and stores in printData of type String
                        String printData = "Pool Name:" + poolName + "\nDB Url:" + connectionURL + "\n User Name:" + userName;
                        // System.out.println(printData);
                        String print = "Data:-\n" + "RTO_PREFIX:" + ApplicationConfig.RTO_PREFIX + "\nSTATE_CD:" + ApplicationConfig.STATE_CD + "\nRTO_CD:" + ApplicationConfig.RTO_CD + "\n_VERSION:" + ApplicationConfig._VERSION + "\nDATABASE_DRIVER:" + ApplicationConfig.CONNECTION_URL + "\nUSER_NAME:" + ApplicationConfig.USER_NAME + "\nUSER_PASSWORD:" + ApplicationConfig.USER_PASSWORD + "\nMAX_CON:" + ApplicationConfig.MAX_CON + "\nSERVER_IP:" + ApplicationConfig.SERVER_IP + "\nRDBMS_NAME:" + ApplicationConfig.RDBMS_NAME + "\nREGISTRY_PORT:" + ApplicationConfig.REGISTRY_PORT + "\nUSER_SESSION_TIMEOUT_SEC:" + ApplicationConfig.USER_SESSION_TIMEOUT_SEC + "\nEDIT_SESSION_TIMEOUT_SEC:" + ApplicationConfig.EDIT_SESSION_TIMEOUT_SEC + "\nDB_TYPE:" + ApplicationConfig.DB_TYPE + " \nTABLE_VT:" + ApplicationConfig.TABLE_VT + "\nTABLE_VM:" + ApplicationConfig.TABLE_VM + "\nTABLE_VH:" + ApplicationConfig.TABLE_VH + "\nTABLE_VST:" + ApplicationConfig.TABLE_VST;
                        //System.out.println(print);
                        LOGGER.info(printData);
                        LOGGER.info(print);
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println("EXECP:" + ex.getMessage());
        }

    }
}

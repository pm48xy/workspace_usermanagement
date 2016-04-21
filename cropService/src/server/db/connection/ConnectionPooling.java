package server.db.connection;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import nic.java.util.Debug;
import org.apache.tomcat.jdbc.pool.DataSource;

public class ConnectionPooling {

    private static DataSource dsLocal;
    private static DataSource dsRemote;
    private static DataSource dsUserMangement;

    public static Connection getDBConnectionLocal() throws NamingException, Exception {

        Connection con = null;
        try {
            if (dsLocal != null) {
                con = dsLocal.getConnection();
            } else {
                dsLocal = new DataSource();
                dsLocal.setDriverClassName("org.postgresql.Driver");
                dsLocal.setUrl("jdbc:postgresql://localhost:5432/crop");
                dsLocal.setUsername("postgres");
                dsLocal.setPassword("root$123");
                dsLocal.setInitialSize(10);
                dsLocal.setMaxActive(20);
                dsLocal.setMaxIdle(5);
                dsLocal.setMinIdle(2);
                con = dsLocal.getConnection();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Debug.logexc(e, "ConnectionPooling.getDBConnection()");
        }
        return con;
    }

     public static Connection getDBConnectionUserManagement() throws NamingException, Exception {

        Connection con = null;
        try {
            if (dsUserMangement != null) {
                con = dsUserMangement.getConnection();
            } else {
                dsUserMangement = new DataSource();
                dsUserMangement.setDriverClassName("org.postgresql.Driver");
                dsUserMangement.setUrl("jdbc:postgresql://localhost:5432/UserData");
                dsUserMangement.setUsername("postgres");
                dsUserMangement.setPassword("root$123");
                dsUserMangement.setInitialSize(10);
                dsUserMangement.setMaxActive(20);
                dsUserMangement.setMaxIdle(5);
                dsUserMangement.setMinIdle(2);
                con = dsUserMangement.getConnection();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Debug.logexc(e, "ConnectionPooling.getDBConnection()");
        }
        return con;
    }
    
    public static Connection getDBConnectionRemoteServer() throws NamingException, Exception {

        Connection con = null;
        try {
            if (dsRemote != null) {
                con = dsRemote.getConnection();
            } else {
                dsRemote = new DataSource();
                dsRemote.setDriverClassName("org.postgresql.Driver");
                // change as per remote DB Server.
                dsRemote.setUrl("jdbc:postgresql://localhost:5432/crop");
                dsRemote.setUsername("postgres");
                dsRemote.setPassword("postgres");
                dsRemote.setInitialSize(10);
                dsRemote.setMaxActive(20);
                dsRemote.setMaxIdle(5);
                dsRemote.setMinIdle(2);
                con = dsRemote.getConnection();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Debug.logexc(e, "ConnectionPooling.getDBConnection()");
        }
        return con;
    }
}

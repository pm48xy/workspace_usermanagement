package common.remote;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : AdminServer
// PURPOSE          : Admin role specific services
// NOTES            : None
// LAST MODIFIED    :
//  20040223 AKT025 Auditing - Removed 'Superfluous Content'
//  20030929 CPO012 Added  getUsersHavingCashierRole() method
//  20030807 JIS014 Added saveUpdatedPassword(), modified saveUpdatedUser()
//  20030723 GUM011 Added getCurrentSessions()
//  20030715 AKS008 Auditing - Removed 'Superfluous Content'
//  20030502 RCN004 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.rmi.*;
import java.util.*;
//
// Importing Common java packages/classes
//
import common.*;
import common.db.*;
import common.context.*;

/**
 * Admin role specific methods.
 */
public interface AdminServer extends Remote {

    /**
     * Returns a list of all the roles. The role 'admin' is not included in the
     * list of roles.
     *
     * @return list of roles in key/value format
     *
     * @throws RemoteException
     * @throws ClientException
     */
    String[][] getAllRoles() throws RemoteException, ClientException;

    /**
     * Returns a list of all the users with the role 'cashier'
     *
     * @return list of users in key/value format
     *
     * @throws RemoteException
     * @throws ClientException
     */
    String[][] getUsersHavingCashierRole() throws RemoteException, ClientException;

    /**
     * Returns a list of all the Services.
     *
     * @return list of Services in key/value format
     *
     * @throws RemoteException
     * @throws ClientException
     */
    String[][] getAllServices() throws RemoteException, ClientException;

    /**
     * Returns a list of all the users. The user 'admin' is not included in the
     * list of users.
     *
     * @return list of users in key/value format
     *
     * @throws RemoteException
     * @throws ClientException
     */
    String[][] getAllUsers() throws RemoteException, ClientException;

    /**
     * Saves the new role defined in the configure role form.
     *
     * @param role name of the role
     * @param descr description for the role
     * @param accessList the list which contains the user access
     *
     * @throws RemoteException
     * @throws ClientException
     */
    void saveNewRole(String role, String descr, String[][] accessList) throws RemoteException, ClientException;

    /**
     * Returns the content of the table in which access to services is modified
     * and list of services.
     *
     * @param role role for which data is required
     *
     * @return Hashtable returns the content of the table in which access to
     * service is modified and list of services
     *
     * @throws RemoteException
     * @throws ClientException
     */
    Hashtable getRoleData(String role) throws RemoteException, ClientException;

    /**
     * Saves an updated role modified in the configure role form.
     *
     * @param role name of the role
     * @param descr description for the role
     * @param accessList the list which contains the user access
     *
     * @throws RemoteException
     * @throws ClientException
     */
    void saveUpdatedRole(String role, String descr, String[][] accessList) throws RemoteException, ClientException;

    /**
     * Saves a new user created in the configure user form.
     *
     * @param userid User ID
     * @param md5password User password
     * @param comments description of the user
     * @param disabled flag if true means user is disabled, if false then not
     * @param roles list of roles for which user has rights
     *
     * @throws RemoteException
     * @throws ClientException
     */
    void saveNewUser(String userid, String md5password, String comments, boolean disabled, Object[] roles) throws RemoteException, ClientException;

    /**
     * Saves an updated user modified in the configure user form.
     *
     * @param userid User ID
     * @param md5password password of the user - Encrypted using
     * Encryption.md5()
     * @param comments description of the user
     * @param disabled flag if true means user is disabled, if false then not
     * @param roles list of roles for which user has rights
     *
     * @throws RemoteException
     * @throws ClientException
     */
    void saveUpdatedUser(String userid, String md5password, String comments, boolean disabled, Object[] roles) throws RemoteException, ClientException;

    /**
     * Fetches details of a user.
     *
     * @param userid User ID
     *
     * @return String[] password, comments and disabled flag are returned in an
     * array
     *
     * @throws RemoteException
     * @throws ClientException
     */
    String[] getUserDetails(String userid) throws RemoteException, ClientException;

    /**
     * Fetches details of all the roles for a user.
     *
     * @param userid User ID
     *
     * @return Hashtable list of all the roles corresponding to the user
     *
     * @throws RemoteException
     * @throws ClientException
     */
    Hashtable getUserRoles(String userid) throws RemoteException, ClientException;

    /**
     * Verify existing password and Saves the updated user password.
     *
     * @param userid User ID
     * @param existingmd5Password Existing password
     * @param newmd5Password New password
     *
     * @return boolean true is returned when user password is successfully saved
     * otherwise returns false
     *
     * @throws RemoteException
     * @throws ClientException
     */
    boolean saveUpdatedPassword(String userid, String existingmd5Password, String newmd5Password) throws RemoteException, ClientException;

    /**
     * Method to get the currently logged in user sessions in a 2-D array
     *
     * @return 2-D array with the session data record indexes: [i][0] - UserId
     * [i][1] - Login time [i][2] - Last Accessed [i][3] - Auto Logout time left
     * [i][4] - User session ID
     *
     * @throws RemoteException when remote exception occurs
     */
    String[][] getCurrentSessions() throws RemoteException;

    /**
     * To save the user logging in into table VST_LOGIN_INFO with State Code and
     * RTO Code. DIV21062008
     */
    public void insertLoggedUserInfo(String userId, String stateName, String rtoName) throws RemoteException, ClientException;

    /**
     * To delete the user logging in into table VST_LOGIN_INFO With Login Id.
     *
     */
    public void insertLoggedOutUserInfo(String loginId) throws RemoteException, ClientException;

    /**
     * Method to get state name and rto name.
     */
    String[] getStateRtoName() throws RemoteException, ClientException;

}

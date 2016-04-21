package common.remote;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : AuthServer
// PURPOSE          : User authentication specific services
// NOTES            : None
// LAST MODIFIED    :
//  20040223 AKT025 Auditing - Removed 'Superfluous Content'
//  20030919 GUM019 Corrected javadoc
//  20030723 GUM011 Added invalidateSession()
//  20030715 AKS008 Auditing - Removed 'Superfluous Content'
//  20030509 GUM002 Changed authenticateUser() to return ClientContext
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
import common.context.*;

/**
 * User authentication specific methods.
 */
public interface AuthServer extends Remote {

    /**
     * Validates a user's id and password. throws exception when userid or
     * password is not matching or when the user is disabled
     *
     * @param userid User ID
     * @param password password of the user
     *
     * @return ClientContext object
     *
     * @throws RemoteException
     * @throws ClientException
     * @throws UserAuthenticationException
     */
    ClientContext authenticateUser(String userid, String saltedPassword, String saltKey, String poolName) throws RemoteException, ClientException, UserAuthenticationException;
    public String[] vaidateUser_UserManagemtnt(String userid, String saltedPassword, String saltKey); 
}

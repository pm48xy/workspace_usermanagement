package common.remote;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : TableServer
// PURPOSE          :  Server Interface
// NOTES            : None
// LAST MODIFIED    :
//  20031224 GES015 Changed getMasterTableList()
//  20030715 AKS008 Auditing - Removed 'Superfluous Content'
//  20030502 RCN004 Renamed DBServer to TableServer. Moved Role/Service/User
//                  specific methods to AdminServer.
//  20030424 JIS002 added methods getAllRoles(), getAllServices(), getAllUsers(),
//                                saveNewRole(), getRoleData(), saveUpdatedRole(),
//                                saveNewUser(), saveUpdatedUser(), getUserDetails(),
//                                getUserRoles(), authenticateUser(), getUserServices()
//  20030408 AKS001 Created
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
import common.dobj.crop.CropMasterDO;
import common.dobj.crop.DistrictMasterDO;
import common.dobj.crop.StateMasterDO;
import common.dobj.crop.TehsilMasterDO;
import common.dobj.crop.VillageMasterDO;

/**
 * Table data Server Remote Interface. Provides Database specific methods for
 * System on client side.
 */
public interface TableServer extends Remote {

    /**
     * Returns serialized (local) master table Data Objects (DO). This is object
     * that the client will be using to fill all the forms for most of the
     * comboboxes. The MasterTables contains the list of the master table DO
     * objects that contains the data from the master tables at the time of
     * Server Startup. That means any changes in the Master Tables thereafter
     * will not be reflected in the Client/Server till the Server is restarted.
     *
     * @return MasterTables object having the list of the individual MasterTable
     * Java DO.
     *
     * @throws RemoteException
     */
    MasterTables getStoredMasterTables() throws RemoteException;

    /**
     * Get the data from Database and not from the stored tables data in
     * MasterTables
     *
     * @param tableName Master Table Name
     *
     * @return Master Table Java Data Object (DO)
     *
     * @throws RemoteException
     * @throws ClientException
     */
    MasterTableDO getDynamicMasterTableDO(String tableName) throws RemoteException, ClientException;

    /**
     * Get the data from Database and not from the stored tables data in Tables
     *
     * @param tableName Table Name
     *
     * @return Table Java Data Object (DO)
     *
     * @throws RemoteException
     * @throws ClientException
     */
    TableDO getDynamicTableDO(String tableName) throws RemoteException, ClientException;

    /**
     * Return the list of the master table names. This list is generated only
     * once on the server.
     *
     * @return List of the Master Tables (picked from VSM_CHOICE_ORDER)
     *
     * @throws RemoteException
     * @throws ClientException
     */
    String[][] getMasterTableList() throws RemoteException, ClientException;

    /**
     * Insert/Update/Delete
     *
     * @param sql Given Insert/Update/Delete SQL command
     *
     * @return Number of rows affected
     *
     * @throws RemoteException
     * @throws ClientException
     */
    int execIUDSQL(String sql) throws RemoteException, ClientException;

    ArrayList<StateMasterDO> loadStateMasterList() throws ClientException;

    ArrayList<DistrictMasterDO> loadDistrictMasterList() throws ClientException;

    ArrayList<TehsilMasterDO> loadTehsilMasterList() throws ClientException;

    ArrayList<VillageMasterDO> loadVillageMasterList() throws ClientException;

    ArrayList<CropMasterDO> loadCropMasterList() throws ClientException;
}

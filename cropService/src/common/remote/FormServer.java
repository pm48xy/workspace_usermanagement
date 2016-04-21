package common.remote;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : FormServer
// PURPOSE          : Entry object for handling a form/service
// NOTES            : None
// LAST MODIFIED    :
//  20031001 GUM021 Implemented firewall() in makeDO() and makeDOArray()
//  20030715 AKS008 Auditing - Removed 'Superfluous Content'
//  20030614 AKS004 Added makeDOArray method
//  20030513 GUM002 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.rmi.*;
import java.util.*;
import java.sql.*;

//
// Importing Common java packages/classes
//
import common.*;
import common.dobj.*;

/**
 * Entry object for handling a form/service
 */
public interface FormServer extends Remote {

    /**
     * Process the given form data to make changes in the database.
     *
     * @param data object containing:- 1. DO 2. Service ID (see:
     * ServiceIds.java) 3. Operation ID (see: OperationIds.java)
     *
     * @return Return data represented as java.lang.Object. The process method
     * may return any kind of return data for example String, String[], int
     * (wrapped into java.lang.Integer), etc represented as java.lang.Object. It
     * is responsibility of the caller of this method to handle the retrurn data
     * appropriately.
     */
    Object process(FormData data) throws RemoteException, ClientException;

    /**
     * Constructs a new DO object.
     *
     * @param data object containing:- 1. String[] args - array of arguments. 2.
     * Service ID (see: ServiceIds.java) 3. Operation ID (see:
     * OperationIds.java)
     *
     * @return Returns the DO object of the service as represented by the
     * "serviceID" in the InputData argument. It is the responsibility of the
     * caller of this method to appropriately cast the return DO before using
     * it.
     */
    DO makeDO(InputData data) throws RemoteException, ClientException;

    /**
     * Constructs a array of DO objects.
     *
     * @param data object containing:- 1. String[] args - array of arguments. 2.
     * Service ID (see: ServiceIds.java) 3. Operation ID (see:
     * OperationIds.java)
     *
     * @return Returns the DO[] array for the service as represented by the
     * "serviceID" in the InputData argument. It is the responsibility of the
     * caller of this method to appropriately cast the return DO before using
     * it.
     */
    DO[] makeDOArray(InputData data) throws RemoteException, ClientException;

    /**
     * Function for returning the Registration Number... KML 14-10-2008
     */
    String ReturnRegnNo(InputData data) throws RemoteException, ClientException;

}

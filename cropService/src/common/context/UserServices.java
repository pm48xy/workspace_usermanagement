package common.context;
///////////////////////////////////////////////////////////////////////////////
// CLASS            : UserServices implements Serializable
// PURPOSE          : User Services class contains privileges for all 
//                    the services for a user
// NOTES            : None
// LAST MODIFIED    :
//  20030519 RCN007 extends ClientObject
//  20030513 GUM002 Changed serviceIds to serviceIDs
//  20030424 JIS002 Created
///////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.io.*;
import java.util.*;
//
// Importing Common java packages/classes
//
import common.*;

/**
 * User Services class contains privileges for all the services for a user. Six
 * operations are available per service. Contains union of all the roles and
 * services allowed to a user. The object of this class is populated at server
 * side and serialized for the client handy access.
 *
 * @author JIS
 */
public class UserServices extends ClientObject implements Serializable {
    ///////////////////////////////////////////////////////////////////////
    // CONSTANTS
    ///////////////////////////////////////////////////////////////////////

    /*
     * IMPORTANT
     *
     * Following IDX_* are used as IDs as well as INDEX of the 
     * 'int[][] services' list.
     */
    /**
     * services[][j] index - Service ID
     */
    public static final int IDX_SERVICE_ID = 0;

    /**
     * services[][j] index - permission to enter a form
     */
    public static final int IDX_ENTER = 1;

    /**
     * services[][j] index - permission to edit a form
     */
    public static final int IDX_EDIT = 2;

    /**
     * services[][j] index - permission to verify a form
     */
    public static final int IDX_VERIFY = 3;

    /**
     * services[][j] index - permission to approve a form
     */
    public static final int IDX_APPROVE = 4;

    /**
     * services[][j] index - permission to verify own form
     */
    public static final int IDX_VERIFY_OWN = 5;

    /**
     * services[][j] index - permission to approve own form
     */
    public static final int IDX_APPROVE_OWN = 6;

    ///////////////////////////////////////////////////////////////////////
    // VARIABLES
    ///////////////////////////////////////////////////////////////////////
    /**
     * List of all the services for the user. This list contains the cummulative
     * privileges for each service for all the roles the user have. Use above
     * constants IDX_* to access the data from this array.
     *
     * <PRE>
     * In this array each row represents a service as per following...
     * services[i][IDX_SERVICE_ID]   : Service ID
     * services[i][IDX_ENTER]        : Enter permission
     * services[i][IDX_EDIT]         : Edit permission
     * services[i][IDX_VERIFY]       : Verify permission
     * services[i][IDX_APPROVE]      : Approve permission
     * services[i][IDX_VERIFY_OWN]   : Verfiy own entered form permission
     * services[i][IDX_APPROVE_OWN]  : Approve own entered form permission
     *
     * Value of the above position has following meaning
     * 1 : means Allowed
     * 0 : means Not-Allowed
     * </PRE>
     */
    private int[][] services = null;

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR(S)
    ////////////////////////////////////////////////////////////////////////
    /**
     * Creates a new instance of UserServices.
     *
     * @param services list of all the services and access flags.
     *
     * @throws Exception Exception thrown if services are null or empty.
     */
    public UserServices(int[][] services) throws UserServicesException {
        if ((services == null) || (services.length <= 0)) {
            throw new UserServicesException("Services in UserServices cannot be empty");
        }

        this.services = services;
    }

    /**
     * Checks if the service is valid.
     *
     * @param serviceid service which is checked.
     *
     * @return boolean returns true if the serviceid is valid, false if not.
     */
    public boolean isServiceValid(int serviceid) {
        boolean result = false;

        for (int i = 0; i < services.length; i++) {
            if (services[i][IDX_SERVICE_ID] == serviceid) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Gets the index of the service in the list.
     *
     * @param serviceid service which is checked.
     *
     * @return returns the index of the service in the list. If the serviceid
     * does not exists then return -1
     */
    private int getServiceIndex(int serviceid) {
        int index = -1;

        for (int i = 0; i < services.length; i++) {
            if (services[i][IDX_SERVICE_ID] == serviceid) {
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * Checks if the user has access permission to a service.
     *
     * @param serviceid service id
     * @param actionid type of access (action) - Corresponds to the index number
     * in the services[][] array
     *
     * @return boolean returns true if the serviceid is valid and action is
     * allowed.
     *
     * @throws UserServicesException.
     */
    public boolean isAllowed(int serviceid, int actionid)
            throws UserServicesException {

        boolean result = false;

        // Check the serviceid validity
        int idx_serviceid = getServiceIndex(serviceid);
        if (idx_serviceid == -1) {
            throw new UserServicesException("Invalid service id found : " + serviceid);
        }

        // Check if the action is allowed
        switch (actionid) {
            case IDX_ENTER:
            case IDX_EDIT:
            case IDX_VERIFY:
            case IDX_APPROVE:
            case IDX_VERIFY_OWN:
            case IDX_APPROVE_OWN:
                if (services[idx_serviceid][actionid] == 1) {
                    result = true;
                }
                break;
            default:
                throw new UserServicesException("Unknown action id (index)"
                        + " found : " + actionid);
        }

        // Return
        return result;
    }

    /**
     * Returns all allowed services (even if only one action is allowed).
     *
     * @return List of allowed services, if no service is allowed then returns
     * null
     *
     * @throws UserServicesException
     */
    public int[] getAllowedServices() throws UserServicesException {
        ArrayList serviceList = new ArrayList();

        // Check if the user has access to a service
        int ncols = services[0].length;
        for (int i = 0; i < services.length; i++) {
            for (int actionid = 1; actionid < ncols; actionid++) {
                if (isAllowed(services[i][IDX_SERVICE_ID], actionid)) {
                    serviceList.add(new Integer(services[i][IDX_SERVICE_ID]));
                    break;
                }
            }
        }

        // Create int array from list for return
        int[] serviceIDs = null;
        int size = serviceList.size();
        if (size > 0) {
            serviceIDs = new int[size];
            for (int i = 0; i < size; i++) {
                serviceIDs[i] = ((Integer) serviceList.get(i)).intValue();
            }
        }

        // Return
        return serviceIDs;
    }

    /**
     * Returns all the services which user is not allowed to access.
     *
     * @return List of forbidden services, if no service is forbidden then
     * returns null
     *
     *
     * @throws UserServicesException.
     */
    public int[] getForbiddenServices() throws UserServicesException {
        int[] serviceIDs = null;

        // Get all allowed services
        int[] allowedServices = getAllowedServices();

        // Return all services which are not available in allowed services
        int size = services.length - allowedServices.length;
        if (size > 0) {
            serviceIDs = new int[size];
            int counter = 0;
            boolean containsValue = false;

            for (int i = 0; i < services.length; i++) {
                containsValue = false;

                for (int j = 0; j < allowedServices.length; j++) {
                    if (services[i][IDX_SERVICE_ID] == allowedServices[j]) {
                        containsValue = true;
                        break;
                    }
                }

                if (!containsValue) {
                    serviceIDs[counter++] = services[i][IDX_SERVICE_ID];
                }
            }
        }

        return serviceIDs;
    }
}

package common;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : FormData
// PURPOSE          : Form Data
// NOTES            : None
// LAST MODIFIED    :
//  20160112 AMR Created
//////////////////////////////////////////////////////////////////////////////
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
import common.context.*;
import common.dobj.*;

/**
 * Form Data
 *
 * @author RCN
 */
public class FormData extends ClientObject implements Serializable {

    /**
     * ClientContext object has all data related to a user and his session
     */
    private ClientContext clientContext;

    /**
     * List of DataObjects that represents the data on the form (panels/tabs).
     * In most of the cases this array contains single DO that represents the
     * entire data on a given Form.
     */
    private DO[] dataObjects;

    /**
     * Service identifier
     */
    private int serviceID;

    /**
     * Operation identifier
     */
    private int operationID;

    /////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    /////////////////////////////////////////////////////////////////////
    /**
     * Constructor.
     */
    public FormData() {
    }

    /**
     * Constructor initializing the Formdata object.
     *
     * @param clientContext ClientContext object has all data related to a user
     * and his session
     * @param dataObject DataObject has the data on the form
     * @param serviceID Service identifier
     * @param operationID Operation identifier
     */
    public FormData(ClientContext clientContext, DO dataObject,
            int serviceID, int operationID) {
        init(clientContext, new DO[]{dataObject}, serviceID, operationID);
    }

    /**
     * Constructor initializing the Formdata object.
     *
     * @param clientContext ClientContext object has all data related to a user
     * and his session
     * @param dataObjects List of DataObjects that represents the data on the
     * form (panels/tabs). In most of the cases this array contains single DO
     * that represents the entire data on a given Form.
     * @param serviceID Service identifier
     * @param operationID Operation identifier
     */
    public FormData(ClientContext clientContext, DO[] dataObjects,
            int serviceID, int operationID) {
        init(clientContext, dataObjects, serviceID, operationID);
    }

    /**
     * Method to initialize values of FormData.
     *
     * @param clientContext ClientContext object has all data related to a user
     * and his session
     * @param dataObjects List of DataObjects that represents the data on the
     * form (panels/tabs). In most of the cases this array contains single DO
     * that represents the entire data on a given Form.
     * @param serviceID Service identifier
     * @param operationID Operation identifier
     */
    public void init(ClientContext clientContext, DO[] dataObjects,
            int serviceID, int operationID) {
        this.clientContext = clientContext;
        this.dataObjects = dataObjects;
        this.serviceID = serviceID;
        this.operationID = operationID;
    }

    ////////////////////////////////////////////////////////////////////
    // GETTER Methods
    ////////////////////////////////////////////////////////////////////
    /**
     * Get the client context.
     *
     * @return ClientContext
     */
    public ClientContext getClientContext() {
        return clientContext;
    }

    /**
     * Get the data object.
     *
     * @return DO
     */
    public DO getDataObject() {
        return dataObjects[0];
    }

    /**
     * Get the data objects array.
     *
     * @return DO[] array of DOs
     */
    public DO[] getDataObjects() {
        return dataObjects;
    }

    /**
     * Get the operation ID.
     *
     * @return operation ID
     */
    public int getOperationID() {
        return operationID;
    }

    /**
     * Get the service ID.
     *
     * @return service ID
     */
    public int getServiceID() {
        return serviceID;
    }

    ////////////////////////////////////////////////////////////////////
    // SETTER Methods
    ////////////////////////////////////////////////////////////////////
    /**
     * Set the clientContext.
     *
     * @param clientContext Client Context to set
     */
    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    /**
     * Set the Data Object.
     *
     * @param dataObject data object to set
     */
    public void setDataObject(DO dataObject) {
        this.dataObjects[0] = dataObject;
    }

    /**
     * Set the Data Objects array.
     *
     * @param dataObjects data object array to set
     */
    public void setDataObjects(DO[] dataObjects) {
        this.dataObjects = dataObjects;
    }

    /**
     * Set the operation ID.
     *
     * @param operationID to set
     */
    public void setOperationID(int operationID) {
        this.operationID = operationID;
    }

    /**
     * Set the service ID.
     *
     * @param serviceID to set
     */
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }
}

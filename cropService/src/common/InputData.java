package common;
/////////////////////////////////////////////////////////////////////////////
// CLASS            : InputData
// PURPOSE          : Input Data used to load a DO object
// NOTES            : None
// LAST MODIFIED    :
//  20031001 GUM021 Implemented firewall() in makeDO() and makeDOArray()
//  20030519 RCN007 extends ClientObject
//  20030513 GUM002 Created
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

/**
 * Input Data used to load a DO object
 *
 * @author GUM
 */
public class InputData extends ClientObject implements Serializable {

    /**
     * ClientContext object has all data related to a user and his session
     */
    private ClientContext clientContext;

    /**
     * List of the arguments that need to be passed to load a DO object. These
     * arguments must be processed individually by the concerned makeDO method.
     * The list can contain any kind of arguments and it is the responsibility
     * of the makeDO method called to process them appropriately.
     */
    private Object[] args;

    /**
     * Service ID (as per ServiceIds.java)
     *
     * @see ServiceIds
     */
    private int serviceID;

    /**
     * Operation ID (as per OperationIds.java)
     *
     * @see OperationIds
     */
    private int operationID;

    /////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    /////////////////////////////////////////////////////////////////////
    /**
     * Empty constructor, creates blank FormData object.
     */
    public InputData() {
    }

    /**
     * Constructor initializing the InputData object with the passed values.
     *
     * @param clientContext ClientContext object has all data related to a user
     * and his session
     * @param args List of the arguments that need to be passed to load a DO
     * object. These arguments must be processed individually by the concerned
     * makeDO method. The list can contain any kind of arguments and it is the
     * responsibility of the makeDO method called to process them appropriately.
     * @param serviceID Service ID (as per ServiceIds.java)
     * @param operationID Operation ID (as per OperationIds.java)
     */
    public InputData(ClientContext clientContext, Object[] args,
            int serviceID, int operationID) {

        init(clientContext, args, serviceID, operationID);
    }

    /**
     * Initializes all the instance data of this object
     *
     * @param clientContext ClientContext object has all data related to a user
     * and his session
     * @param args List of the arguments that need to be passed to load a DO
     * object. These arguments must be processed individually by the concerned
     * makeDO method. The list can contain any kind of arguments and it is the
     * responsibility of the makeDO method called to process them appropriately.
     * @param serviceID Service ID (as per ServiceIds.java)
     * @param operationID Operation ID (as per OperationIds.java)
     */
    public void init(ClientContext clientContext, Object[] args,
            int serviceID, int operationID) {

        this.clientContext = clientContext;
        this.args = args;
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
     * Get the data arguments array.
     *
     * @return Object[] array of arguments
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * Get the service ID.
     *
     * @return service ID
     */
    public int getServiceID() {
        return serviceID;
    }

    /**
     * Get the operation ID.
     *
     * @return operation ID
     */
    public int getOperationID() {
        return operationID;
    }

    ////////////////////////////////////////////////////////////////////
    //SETTER Methods
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
     * Set the argument array.
     *
     * @param args array of arguments to set
     */
    public void setArgs(Object[] args) {
        this.args = args;
    }

    /**
     * Set the service ID.
     *
     * @param serviceID service ID to set
     */
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    /**
     * Set the operation ID.
     *
     * @param operationID to set
     */
    public void setOperationID(int operationID) {
        this.operationID = operationID;
    }
}

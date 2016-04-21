package common.context;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : ClientContext
// PURPOSE          : Stores client identification data
// NOTES            : None
// LAST MODIFIED    :
//  20030519 RCN007 extends ClientObject
//  20030515 JIS009 Serialized
//  20030511 GUM002 Added userID
//  20030306 RCN001 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.util.*;
import java.io.*;
//
// Importing Common java packages/classes
//
import common.*;

/**
 * Stores client identification data
 *
 * @author RCN
 */
public class ClientContext extends ClientObject implements Serializable {

    //////////////////////////////////////////////////////////////////
    // INSTANCE VARIABLES
    //////////////////////////////////////////////////////////////////
    /**
     * Session identifier
     */
    private String sessionID;

    /**
     * Logged in user name
     */
    private String userID;
    private String userName;
    private String mobile;
    private Boolean activeInd;
    private String remarks;

    //////////////////////////////////////////////////////////////////
    // CONSTRUCTOR(S)
    //////////////////////////////////////////////////////////////////
    /**
     * Constructor
     */
    //public ClientContext(long sessionID, String userID) {
    public ClientContext(String userID, String userName, String mobile, boolean activeInd, String remarks) {

        this.userID = userID;
        this.userName = userName;
        this.mobile = mobile;
        this.activeInd = activeInd;
        this.remarks = remarks;

    }

    //////////////////////////////////////////////////////////////////
    // GETTERS / SETTERS
    //////////////////////////////////////////////////////////////////
    public String getSessionID() {
        return sessionID;
    }

    public String getUserID() {
        return userID;
    }

    /**
     * @param sessionID the sessionID to set
     */
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the activeInd
     */
    public Boolean getActiveInd() {
        return activeInd;
    }

    /**
     * @param activeInd the activeInd to set
     */
    public void setActiveInd(Boolean activeInd) {
        this.activeInd = activeInd;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "ClientContext{" + "sessionID=" + sessionID + ", userID=" + userID + ", userName=" + userName + ", mobile=" + mobile + ", activeInd=" + activeInd + ", remarks=" + remarks + '}';
    }

}

package common.dobj;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : DO
// PURPOSE          : Parent of all the Data Object classes
// NOTES            : None
// LAST MODIFIED    :
//  20030804 SIM005 Added Copyright Documentation
//  20030602 AKS003 Added 'DO[] subDOs'
//  20030519 RCN007 extends ClientObject
//  20030419 RCN003 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.io.*;
//
// Importing Server/Common java packages/classes
//
import common.*;

/**
 * Parent of all the Data Object classes.
 *
 * @author RCN
 */
public abstract class DO extends ClientObject implements Serializable {

    /**
     * List of sub DOs to represents a complete Form data
     */
    protected DO[] subDOs;

    /**
     * Reset all the instance variables (only the value part of the DataField to
     * null).
     */
    public abstract void reset();

    /**
     * Dump all the instance variables
     */
    public abstract String dump();

    /**
     * Get the Sub DOs
     */
    public DO[] getSubDOs() {
        return subDOs;
    }

    /**
     * Set the sub DOs list
     */
    public void setSubDOs(DO[] subDOs) {
        this.subDOs = subDOs;
    }

    //Akshey 08-12-2009
    /**
     * Return NULL if value is 0 or null or 'null' or '' or ""
     *
     */
    public static String getNull(String value) {
        if (value != null) {
            if (value.equals("0") || value.equals("'NULL'") || value.equals("'null'") || value.equals("''") || value.equals("") || value.equals("null") || value.equals("NULL")) {
                return null;
            } else {
                return value;
            }
        }

        return value;
    }
    //end Akshey

}

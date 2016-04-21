package common.dobj.dogenerator;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : DataFieldException
// PURPOSE          : DataField expception class for 
// NOTES            : None
// LAST MODIFIED    :
//  20030513 GUM002 Renamed from DataFieldException to DataFieldException
//  20030506 RCN005 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//
// NONE 

/**
 * DataField expception class
 *
 * @author RCN
 */
public class DataFieldException extends Exception {

    /**
     * Constructor
     */
    public DataFieldException() {
        super();
    }

    /**
     * Constructor
     */
    public DataFieldException(String msg) {
        super(msg);
    }
}

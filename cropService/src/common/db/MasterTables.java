package common.db;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : MasterTables
// PURPOSE          : Loads all the master table database as Java Objects.
// NOTES            : None
// LAST MODIFIED    :
//  20090209 DIV052 Added VM_FRC_REASON   
//  20040422 RJB013 Delhi Changes I
//  20040113 SIM015 Added VM_CHOICE_FEE_MAST
//  20031223 NID007 Added VM_AUDIT_TYPE
//  20031013 NID001 Added VM_TRADE_CERT_FOR
//  20030716 AKT006 Added VM_MODULE_MASTER
//  20030602 AKS003 QA modifications
//  20030503 CPO002 Added VM_VEH_CATG.
//  20030410 CPO001 Documentaion. VM_ACTION, VM_OFFENCES as TableDO objects.
//                  Added VM_PAYMENT_MODE, VM_TAX_MODE, VM_VEH_TYPE.
//  20030423 AKS002 Removed VM_ACTION, VM_OFFENCES, VM_PMT_SUBTYPE
//  20030410 AKS001 Documentaion
//  20030310 RCN001 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.sql.*;
import java.util.*;
import java.io.*;
//
// Importing Common java packages/classes
//
import common.*;

/**
 * Loads all the master table database as Java Objects.
 *
 * @author RCN
 */
public class MasterTables extends ClientObject implements Serializable {
    /*
     * Master Table Read-Only Objects
     */

    //.........................................
    // CODE/DESCR based master tables
    //.........................................
    public MasterTableDO VM_AREA_MAST;
    public MasterTableDO VM_BANK;
    public MasterTableDO VM_BD_TYPE;
    public MasterTableDO VM_CHALLAN_OFFICER;
    public MasterTableDO VM_COUNTRY;
    public MasterTableDO VM_COURT_MASTER;
    public MasterTableDO VM_DEALER;
    public MasterTableDO VM_DOC_CD;
    public MasterTableDO VM_DOMAIN;
    public MasterTableDO VM_FIT_OFFICER;
    public MasterTableDO VM_FUEL;
    public MasterTableDO VM_GD_PMT_REGION;
    public MasterTableDO VM_ICCODE;
    public MasterTableDO VM_INSTYP;
    public MasterTableDO VM_MAKER;
    public MasterTableDO VM_MODELS;
    public MasterTableDO VM_OWCATG;
    public MasterTableDO VM_OWCODE;
    public MasterTableDO VM_PAYMENT_MODE;
    public MasterTableDO VM_PACTION;
    public MasterTableDO VM_PERMIT_AREA_CENTRE;
    public MasterTableDO VM_PINCODE_MASTER;
    public MasterTableDO VM_PMTSUR_PURPOS;
    public MasterTableDO VM_PMT_CATEGORY;
    public MasterTableDO VM_PMT_MAST;
    public MasterTableDO VM_QUAL_MAST;
    public MasterTableDO VM_REGN_TYPE;
    public MasterTableDO VM_RESULT_DESC;
    public MasterTableDO VM_ROLE_MASTER;
    public MasterTableDO VM_SERVICE_TYPE;
    public MasterTableDO VM_STATE_CD;
    public MasterTableDO VM_TAX_MODE;
    public MasterTableDO VM_TRANSACTION_MAST;
    public MasterTableDO VM_VEH_TYPE;
    public MasterTableDO VM_VEH_CATG;
    public MasterTableDO VM_VHCLASS_CD;
    public MasterTableDO VM_MODULE_MASTER;
    public MasterTableDO VM_TRADE_CERT_FOR;
    public MasterTableDO VM_AUDIT_TYPE;
    //public MasterTableDO VM_CHOICE_FEE_MAST;    //MohnishKr----28thAug2008
    public MasterTableDO VM_EUROTYPES;
    public MasterTableDO VM_DISTRICT;
    public MasterTableDO VM_RTO_CODE; // DIV21July2008
    //DIV 09-02-2009
    public MasterTableDO VM_FRC_REASON;
    //...........................................................
    // Non CODE/DESCR based master tables
    //...........................................................
    public MasterTableDO VM_ROUTE_MASTER;
    public MasterTableDO VM_VIA_MASTER;
    public MasterTableDO VM_USERS;

    //kml 02-02-2009
    public MasterTableDO VM_KIT_MFG;
    public MasterTableDO VM_KIT_TYPE;
    public MasterTableDO VM_KIT_WORKSHOP;

    //...........................................................
    // Master Tables for which only TableDO to be made.
    // These are the tables which does not contain the PK column.
    // Essentially in these tables the PK is a COMPOUND PK based
    // on more than one column.
    //...........................................................
    public TableDO VM_ACTION;
    public TableDO VM_OFFENCES;

    //.............................................................
    // For internal use to store the individual references so that
    // common methods can be called in a loop
    //.............................................................
    private Vector masterTableDOs;

    /**
     * Once the master table DO are created, call this method to fill the
     * masterTableDOs.
     *
     */
    public void fillMasterTableVector() {
        masterTableDOs = new Vector();
        //..........................................................
        // Add the master table references in the masterTableDOs
        //..........................................................
        masterTableDOs.add(VM_AREA_MAST);
        masterTableDOs.add(VM_BANK);
        masterTableDOs.add(VM_BD_TYPE);
        masterTableDOs.add(VM_CHALLAN_OFFICER);
        masterTableDOs.add(VM_COUNTRY);
        masterTableDOs.add(VM_COURT_MASTER);
        masterTableDOs.add(VM_DEALER);
        masterTableDOs.add(VM_DOC_CD);
        masterTableDOs.add(VM_DOMAIN);
        masterTableDOs.add(VM_FIT_OFFICER);
        masterTableDOs.add(VM_FUEL);
        masterTableDOs.add(VM_GD_PMT_REGION);
        masterTableDOs.add(VM_ICCODE);
        masterTableDOs.add(VM_INSTYP);
        masterTableDOs.add(VM_MAKER);
        masterTableDOs.add(VM_MODELS);
        masterTableDOs.add(VM_OWCATG);
        masterTableDOs.add(VM_OWCODE);
        masterTableDOs.add(VM_PAYMENT_MODE);
        masterTableDOs.add(VM_PACTION);
        masterTableDOs.add(VM_PERMIT_AREA_CENTRE);
        masterTableDOs.add(VM_PINCODE_MASTER);
        masterTableDOs.add(VM_PMTSUR_PURPOS);
        masterTableDOs.add(VM_PMT_CATEGORY);
        masterTableDOs.add(VM_PMT_MAST);
        masterTableDOs.add(VM_QUAL_MAST);
        masterTableDOs.add(VM_REGN_TYPE);
        masterTableDOs.add(VM_RESULT_DESC);
        masterTableDOs.add(VM_ROLE_MASTER);
        masterTableDOs.add(VM_SERVICE_TYPE);
        masterTableDOs.add(VM_STATE_CD);
        masterTableDOs.add(VM_TAX_MODE);
        masterTableDOs.add(VM_TRANSACTION_MAST);
        masterTableDOs.add(VM_VEH_TYPE);
        masterTableDOs.add(VM_VEH_CATG);
        masterTableDOs.add(VM_VHCLASS_CD);
        masterTableDOs.add(VM_MODULE_MASTER);
        masterTableDOs.add(VM_TRADE_CERT_FOR);
        masterTableDOs.add(VM_AUDIT_TYPE);
        masterTableDOs.add(VM_EUROTYPES);
        masterTableDOs.add(VM_DISTRICT);
        masterTableDOs.add(VM_RTO_CODE); //DIV21062008
        masterTableDOs.add(VM_FRC_REASON); //DIV 09-02-2009
        //.........................................................
        // Non CODE/DESCR based master tables
        //.........................................................
        masterTableDOs.add(VM_ROUTE_MASTER);
        masterTableDOs.add(VM_VIA_MASTER);
        masterTableDOs.add(VM_USERS);
        //masterTableDOs.add(VM_CHOICE_FEE_MAST);

        //kml 02-02-2009
        masterTableDOs.add(VM_KIT_MFG);
        masterTableDOs.add(VM_KIT_TYPE);
        masterTableDOs.add(VM_KIT_WORKSHOP);

        //...........................................................
        // Master Tables for which only TableDO to be made.
        // These are the tables which does not contain the PK column.
        // Essentially in these tables the PK is a COMPOUND PK based
        // on more than one column.
        //...........................................................
        masterTableDOs.add(VM_ACTION);
        masterTableDOs.add(VM_OFFENCES);

    }

    /**
     * Returns the master table list
     *
     * @return Vector containing master table DOs.
     */
    public Vector getMasterTables() {
        return masterTableDOs;
    }

    /**
     * Dump. Prints the contents of the Master Table Objects.
     */
    public void dump() {
        int iDOSize = masterTableDOs.size();
        for (int i = 0; i < iDOSize; i++) {
            ((MasterTableDO) masterTableDOs.get(i)).dump();
        }
    }
}

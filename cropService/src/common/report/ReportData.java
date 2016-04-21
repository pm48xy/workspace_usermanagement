package common.report;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : ReportData
// PURPOSE          : Contains the list of the report data
// NOTES            : None
// LAST MODIFIED    :
//  20040423 AJS013 Added REP_AUDIT_REPORT
//  20040227 ASF011 Added REPORT_ACC_STAT
//  20040225 AJS012 Added few constants
//  20040207 PAN014 Implemented RC format for Orissa
//  20040130 AJS011 Added few constants
//  20040113 AKT021 Added REPORT_VEH_BACKLOG
//  20040101 JIS025 Corrected Javadoc
//  20031229 AKT019 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.io.*;
//
// Importing Client/Common java packages/classes
//
// NONE
import common.*;
import nic.java.util.*;

/**
 * specific Report data.
 *
 * @author AKT
 */
public class ReportData implements Serializable {

    ////////////////////////////////////////////////////////////////////////
    // CONSTANTS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Vehicle Report
     */
    public static final int REPORT_VEH_GENERAL = 1;

    /**
     * Vehicle Fitness Report
     */
    public static final int REPORT_VEH_FITNESS = 2;

    /**
     * Vehicle Search Report
     */
    public static final int REPORT_VEH_SEARCH = 3;

    /**
     * Vehicle Permit Report
     */
    public static final int REPORT_VEH_PERMIT = 4;

    /**
     * Backlog User Report
     */
    public static final int REPORT_VEH_BACKLOG = 5;

    /**
     * Tax Clearance Certificate
     */
    public static final int REPORT_VEH_TCC = 6;

    /**
     * Demand Notice
     */
    public static final int REPORT_VEH_DEMAND_NOTICE = 7;

    /**
     * Tax Statement
     */
    public static final int REPORT_VEH_TAX_STATEMENT = 8;

    /**
     * Registration Certificate Report
     */
    public static final int REPORT_RC = 9;

    /**
     * Audit Recovery Report
     */
    public static final int REPORT_AUDIT_RECOVERY = 10;

    /**
     * Balance Tax Report
     */
    public static final int REPORT_BAL_TAX = 11;

    /**
     * Tax Exempted Report
     */
    public static final int REPORT_TAX_EXEMPTED = 12;

    /**
     * Day End Cash Satement Report
     */
    public static final int REPORT_ACC_STAT = 13;

    /**
     * Audit Report
     */
    public static final int REPORT_AUDIT_REPORT = 14;

    public static final int REPORT_VEH_MISC = 15;

    public static final int REPORT_THEFT_REPORT = 18;

    public static final int REPORT_TEMP_VEH = 19;

    public static final int REPORT_VERIFY_TRANS = 20;

    public static final int REPORT_APROV_TRANS = 21;

    public static final int REPORT_NOC_VEH = 22;

    public static final int REP_COUNT_REGISTER_VEHICLE = 23;

    public static final int REP_ONLINE_TAX_TOKEN_PRINT = 24;

    public static final int REP_ONLINE_TAX_CLEAR = 25;

    public static final int REP_ONLINE_TAX_POSTAL_CHARGE = 26;

    public static final int REPORT_SCREEN = 27;

    ////////////////////////////////////////////////////////////////////////
    // VARIABLE
    ////////////////////////////////////////////////////////////////////////
    /**
     * Report Type
     */
    private int type;

    /**
     * Report Data
     */
    private String[][] data;

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Constructor
     *
     * @param data Report Data
     * @param type Report Type
     */
    public ReportData(String[][] data, int type) {
        this.data = data;
        this.type = type;
    }

    ////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////
    /**
     * Get the report type
     *
     * @return report type
     */
    public int getType() {
        return type;
    }

    /**
     * Get the data
     *
     * @return 2-D Array containing Report data
     */
    public String[][] getData() {
        return data;
    }
}

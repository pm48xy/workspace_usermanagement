package common.db;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : TableList
// PURPOSE          : List of the tables used in . This is provided
//                    so that Table Names are not used directly in
//                    the code but through a constant variable.
// NOTES            : None
// LAST MODIFIED    :
//  20090209 DIV052 Added VT_FINANCER_RC and VM_FRC_REASON
//  20050705 DIV011 Added VT_TAX_REFUND
//  20040715 SIM022 Added VM_TAX_LADEN_MINUS_UNLADEN_WT and VM_VEH_TYPE_NEW
//  20040428 SIM020 Added VT_RETROFITTER_DATA
//  20040428 NID021 Added VT_VEHICLE_SWAP
//  20040422 SIM018 Added VST_VEHNO_MAST
//  20030422 NID020 Added VSM_NOC_NUM
//  20040422 RJB013 Delhi Changes I
//  20040329 NID014 Added VT_RECP_NO_CORRECTION
//  20040309 ASF012 Added VH_ACTION, VH_VEH_CATG and VSM_PMTFEE_MAST
//  20040227 ASF011 Added VH_DSA_PENDING_CANCEL and VH_ENFORCEMENT_COLL_CANCEL
//  20040115 AMT007 Added VH_REMOVED_OFFENCE
//  20040113 SIM015 Added VM_CHOICE_FEE_MAST
//  20040109 GES017 Added VSM_PURPOSE_CODE
//  20040108 AJS009 Added VH_SIDE_TRAILER
//  20031224 JIS023 Added VT_TAX_INSTALLMENT, VT_TAX_INSTALLMENT_BRKUP
//  20031223 AJS007 Modify VT_RC_SURR,VT_RC_CANCEL,VH_RC_SURR_HISTtable and added VH_VERIFY_RC_SURR_HIST, VH_VERIFY_RC_SURR
//  20031223 NID007 Added VM_AUDIT_TYPE
//  20031218 SIM014 Added VT_TAX_BALANCE
//  20031209 ASF009 Added VH_RD_TAX_CANCEL,  VT_RD_TAX_CLEAR and VH_OWNER_CANCEL_RCPT
//  20031117 JIS022 Added VM_TAX_SERVICE_TYPE
//  20031017 PAN006 Added VT_VEHICLE_CHECK
//  20031013 NID001 Added VM_TRADE_CERT_FOR
//  20030828 GES011 Renamed VM_TAX_EXEMPTION to VT_TAX_EXEMPTION
//  20030804 PAN003 Added VT_RC_SUSPEND AND VT_RC_RESTORE
//  20030731 ASF004 Added VT_FC_CANCEL, VT_FC_SUSPEND AND VT_FC_RESTORE
//  20030729 SIM004 Added VT_TEMP_RENEWAL
//  20030723 GUM011 Added VST_BACKLOG
//  20030719 AKT007 Added VT_OTH_OFF_PAYMENT
//  20030716 AKT006 Added VM_MODULE_MASTER 
//  20030714 RJB006 Added VSM_TAX_PARAM_MAP
//  20030702 JIS013 Added Tax Tables
//  20030708 RJB005 Added VH_VERIFY_HYPTH_CONT
//  20030630 AKS005 Added VT_TMP_PERMIT, VH_VERIFY_TEMP_PERMIT
//  20030626 RJB004 Added VT_TAX_CLEAR and VT_RD_TAX
//  20030624 AJS003 Added VH_VERIFY_TEMP_OWNER
//  20030620 AKT005 Added VT_DSA_PENDING
//  20030617 AJS002 Added VT_OWNER_CANCEL, VT_SPL_PERMIT, VH_VERIFY_SPL_PERMIT
//  20030603 AJS001 Added VT_ENFORCEMENT_COLL
//  20030603 AKT004 Changed VH_VEH_CONVERSION to VT_VEH_CONVERSION
//  20030520 JIS010 Added VT_AUDIT_RECOVERY, VT_NOC_CANCEL, VT_TEMP_OWNER_FEE
//  20030513 GUM002 Removed VSM_FITFEE_MAST
//  20030503 CPO002 Added VM_VEH_CATG.
//  20030501 CPO001 Added VM_PAYMENT_MODE, VM_TAX_MODE, VM_VEH_TYPE,
//                  VM_ACTION, VM_OFFENCES
//  20030423 AKS002 Added new VM_, VSM_, VST_ table entries and Special tables.
//  20030310 RCN001 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.sql.*;
import java.io.*;
//
// Importing Common java packages/classes
//
import common.*;

/**
 * List of the tables used in . This is provided so that Table Names are not
 * used directly in the code but through a constant variable listed here. Idea
 * is to have the flexibility at the development time and to avoid mis-spelled
 * table name in the SQL strings.
 *
 * @author AKS
 */
abstract public class TableList {

    ////////////////////////////////////////////////////////////////////////
    // CONSTANTS
    ////////////////////////////////////////////////////////////////////////
    //......................................................................
    // MASTER TABLES : FIRST COLUMN IS "CODE" AND SECOND COLUMN IS "DESCR".
    //                 "CODE" IS PK.
    //......................................................................
    public static final String VM_AREA_MAST = "VM_AREA_MAST";
    public static final String VM_BANK = "VM_BANK";
    public static final String VM_BD_TYPE = "VM_BD_TYPE";
    public static final String VM_CHALLAN_OFFICER = "VM_CHALLAN_OFFICER";
    public static final String VM_COUNTRY = "VM_COUNTRY";
    public static final String VM_COURT_MASTER = "VM_COURT_MASTER";
    public static final String VM_DEALER = "VM_DEALER";
    public static final String VM_DOC_CD = "VM_DOC_CD";
    public static final String VM_DOMAIN = "VM_DOMAIN";
    public static final String VM_FIT_OFFICER = "VM_FIT_OFFICER";
    public static final String VM_FUEL = "VM_FUEL";
    public static final String VM_GD_PMT_REGION = "VM_GD_PMT_REGION";
    public static final String VM_ICCODE = "VM_ICCODE";
    public static final String VM_INSTYP = "VM_INSTYP";
    public static final String VM_MAKER = "VM_MAKER";
    public static final String VM_MODELS = "VM_MODELS";
    public static final String VM_OWCATG = "VM_OWCATG";
    public static final String VM_OWCODE = "VM_OWCODE";
    public static final String VM_PAYMENT_MODE = "VM_PAYMENT_MODE";
    public static final String VM_PACTION = "VM_PACTION";
    public static final String VM_PERMIT_AREA_CENTRE = "VM_PERMIT_AREA_CENTRE";
    public static final String VM_PINCODE_MASTER = "VM_PINCODE_MASTER";
    public static final String VM_PMT_CATEGORY = "VM_PMT_CATEGORY";
    public static final String VM_PMT_MAST = "VM_PMT_MAST";
    public static final String VM_PMTSUR_PURPOS = "VM_PMTSUR_PURPOS";
    public static final String VM_QUAL_MAST = "VM_QUAL_MAST";
    public static final String VM_REGN_TYPE = "VM_REGN_TYPE";
    public static final String VM_RESULT_DESC = "VM_RESULT_DESC";
    public static final String VM_ROLE_MASTER = "VM_ROLE_MASTER";
    public static final String VM_ROUTE_MASTER = "VM_ROUTE_MASTER";
    public static final String VM_SERVICE_TYPE = "VM_SERVICE_TYPE";
    public static final String VM_STATE_CD = "VM_STATE_CD";
    public static final String VM_TAX_MODE = "VM_TAX_MODE";
    public static final String VM_TRANSACTION_MAST = "VM_TRANSACTION_MAST";
    public static final String VM_VHCLASS_CD = "VM_VHCLASS_CD";
    public static final String VM_VEH_TYPE = "VM_VEH_TYPE";
    public static final String VM_VEH_CATG = "VM_VEH_CATG";
    public static final String VM_VIA_MASTER = "VM_VIA_MASTER";
    public static final String VM_MODULE_MASTER = "VM_MODULE_MASTER";
    public static final String VM_TRADE_CERT_FOR = "VM_TRADE_CERT_FOR";
    public static final String VM_AUDIT_TYPE = "VM_AUDIT_TYPE";
    public static final String VM_CHOICE_FEE_MAST = "VM_CHOICE_FEE_MAST";
    public static final String VM_VEH_TYPE_NEW = "VM_VEH_TYPE_NEW";
    public static final String VM_DISTRICT = "VM_DISTRICT";
    public static final String VM_RTO_CODE = "VM_RTO_CODE";// DIV21July2008
    public static final String VM_FRC_REASON = "VM_FRC_REASON";//DIV 09-02-2009

    public static final String VM_TAX_PENALTY_CRITERIA = "VM_TAX_PENALTY_CRITERIA";
    public static final String VM_PENALTY_HOLIDAYS = "VM_PENALTY_HOLIDAYS";
    public static final String VM_TAX_PENALTY_SLAB = "VM_TAX_PENALTY_SLAB";
    public static final String VM_TAX_PENALTY_VEHICLES = "VM_TAX_PENALTY_VEHICLES";
    public static final String VM_TAX_BODY_CHASSIS = "VM_TAX_BODY_CHASSIS";
    public static final String VM_TAX_CC = "VM_TAX_CC";
    public static final String VM_TAX_DISTANCE_PER_DAY = "VM_TAX_DISTANCE_PER_DAY";
    public static final String VM_TAX_DOMAIN = "VM_TAX_DOMAIN";
    public static final String VT_TAX_EXEMPTION = "VT_TAX_EXEMPTION";
    public static final String VM_TAX_FLOOR_AREA = "VM_TAX_FLOOR_AREA";
    public static final String VM_TAX_FUEL = "VM_TAX_FUEL";
    public static final String VM_TAX_HP = "VM_TAX_HP";
    public static final String VM_TAX_LADEN_WT = "VM_TAX_LADEN_WT";
    public static final String VM_TAX_MISC = "VM_TAX_MISC";
    public static final String VM_TAX_OWNER_CATEGORY = "VM_TAX_OWNER_CATEGORY";
    public static final String VM_TAX_OWNERSHIP = "VM_TAX_OWNERSHIP";
    public static final String VM_TAX_PARAMETERS = "VM_TAX_PARAMETERS";
    public static final String VM_TAX_PERMIT = "VM_TAX_PERMIT";
    public static final String VM_TAX_ROUTE_CLASS = "VM_TAX_ROUTE_CLASS";
    public static final String VM_TAX_ROUTE_ONE_WAY = "VM_TAX_ROUTE_ONE_WAY";
    public static final String VM_TAX_SALE_AMOUNT = "VM_TAX_SALE_AMOUNT";
    public static final String VM_TAX_SEATING_CAPACITY = "VM_TAX_SEATING_CAPACITY";
    public static final String VM_TAX_SLAB = "VM_TAX_SLAB";
    public static final String VM_TAX_STANDING_CAPACITY = "VM_TAX_STANDING_CAPACITY";
    public static final String VM_TAX_SURCHARGE_REBATE_SLAB = "VM_TAX_SURCHARGE_REBATE_SLAB";
    public static final String VM_TAX_UNLADEN_WT = "VM_TAX_UNLADEN_WT";
    public static final String VM_TAX_VEHICLE_AGE = "VM_TAX_VEHICLE_AGE";
    public static final String VM_TAX_VEHICLES = "VM_TAX_VEHICLES";
    public static final String VM_TAX_VEHICLES_OTT = "VM_TAX_VEHICLES_OTT";
    public static final String VM_TAX_SERVICE_TYPE = "VM_TAX_SERVICE_TYPE";
    public static final String VM_EUROTYPES = "VM_EUROTYPES";
    public static final String VM_TAX_LADEN_MINUS_UNLADEN_WT = "VM_TAX_LADEN_MINUS_UNLADEN_WT";
    public static final String HSRP_AUTH_NO_MAST = "HSRP_AUTH_NO_MAST";
    //......................................................................
    // MASTER TABLES : Master Tables for which only TableDO to be made.
    // These are the tables which does not contain the PK column.
    // Essentially in these tables the PK is a COMPOUND PK based
    // on more than one column.
    //......................................................................
    public static final String VM_ACTION = "VM_ACTION";
    public static final String VM_OFFENCES = "VM_OFFENCES";

    //kml 02-02-2009
    public static final String VM_KIT_MFG = "VM_KIT_MFG";
    public static final String VM_KIT_TYPE = "VM_KIT_TYPE";
    public static final String VM_KIT_WORKSHOP = "VM_KIT_WORKSHOP";

    //......................................................................
    // TRANSACTION TABLES
    //......................................................................    
    public static final String VT_ACCOUNT = "VT_ACCOUNT";
    public static final String VT_AUTH = "VT_AUTH";
    public static final String VT_AXLE = "VT_AXLE";
    public static final String VT_BLOCK_VEH = "VT_BLOCK_VEH";
    public static final String VT_CHALLAN = "VT_CHALLAN";
    public static final String VT_COMVEH = "VT_COMVEH";
    public static final String VT_DOC_IMPND = "VT_DOC_IMPND";
    public static final String VT_EX_ARMY_VEH = "VT_EX_ARMY_VEH";
    public static final String VT_ENFORCEMENT_COLL = "VT_ENFORCEMENT_COLL";
    public static final String VT_FANCY_REGISTER = "VT_FANCY_REGISTER";
    public static final String VT_FIT_CANCEL = "VT_FIT_CANCEL";
    public static final String VT_FITNESS_FEE = "VT_FITNESS_FEE";
    public static final String VT_FITNESS_INFO = "VT_FITNESS_INFO";
    public static final String VT_GOODS_PERMIT = "VT_GOODS_PERMIT";
    public static final String VT_HYPTH = "VT_HYPTH";
    public static final String VT_HYPTH_CONT = "VT_HYPTH_CONT";
    public static final String VT_IMPORT_VEH = "VT_IMPORT_VEH";
    public static final String VT_MULTIPLE_OWNERS = "VT_MULTIPLE_OWNERS";
    public static final String VT_NOC = "VT_NOC";
    public static final String VT_OTHER_STAT_VEH = "VT_OTHER_STAT_VEH";
    public static final String VT_OWNER = "VT_OWNER";
    public static final String VT_PERMIT = "VT_PERMIT";
    public static final String VT_PMT_SURND_DETAIL = "VT_PMT_SURND_DETAIL";
    public static final String VT_PMTFEE = "VT_PMTFEE";
    public static final String VT_PUCC_VEH = "VT_PUCC_VEH";
    public static final String VT_PVT_RE_REGN = "VT_PVT_RE_REGN";
    public static final String VT_RC_CANCEL = "VT_RC_CANCEL";
    public static final String VT_RC_SURR = "VT_RC_SURR";
    public static final String VT_SIDE_TRAILER = "VT_SIDE_TRAILER";
    public static final String VT_TEMP_OWNER = "VT_TEMP_OWNER";
    public static final String VT_THEFT_REPORT = "VT_THEFT_REPORT";
    public static final String VT_TMP_REGN_DTL = "VT_TMP_REGN_DTL";
    public static final String VT_TRADE_CERT = "VT_TRADE_CERT";
    public static final String VT_TRADE_CERT_FEE = "VT_TRADE_CERT_FEE";
    public static final String VT_TRAILER = "VT_TRAILER";
    public static final String VT_VEH_CMPND_FEE = "VT_VEH_CMPND_FEE";
    public static final String VT_VEH_IMPND = "VT_VEH_IMPND";
    public static final String VT_VEH_OFFENCE = "VT_VEH_OFFENCE";
    public static final String VT_VEHINS = "VT_VEHINS";
    public static final String VT_VERIFY_DTLS = "VT_VERIFY_DTLS";
    public static final String VT_AUDIT_RECOVERY = "VT_AUDIT_RECOVERY";
    public static final String VT_NOC_CANCEL = "VT_NOC_CANCEL";
    public static final String VT_TEMP_OWNER_FEE = "VT_TEMP_OWNER_FEE";
    public static final String VT_CAMP_COLL = "VT_CAMP_COLL";
    public static final String VT_SPL_PERMIT = "VT_SPL_PERMIT";
    public static final String VT_OWNER_CANCEL = "VT_OWNER_CANCEL";
    public static final String VH_VERIFY_SPL_PERMIT = "VH_VERIFY_SPL_PERMIT";
    public static final String VT_DSA_PENDING = "VT_DSA_PENDING";
    public static final String VT_RD_TAX = "VT_RD_TAX";
    public static final String VT_RD_TAXPAID = "VT_RD_TAXPAID";
    public static final String VT_TAX_CLEAR = "VT_TAX_CLEAR";
    public static final String VH_RD_TAX_CANCEL = "VH_RD_TAX_CANCEL";
    public static final String VT_RD_TAX_CLEAR = "VT_RD_TAX_CLEAR";
    public static final String VT_TAX_DRAFT = "VT_TAX_DRAFT";
    public static final String VT_RD_TAXDRAFT = "VT_RD_TAXDRAFT";
    public static final String VT_TMP_PERMIT = "VT_TMP_PERMIT";
    public static final String VT_OTH_OFF_PAYMENT = "VT_OTH_OFF_PAYMENT";
    public static final String VT_TEMP_RENEWAL = "VT_TEMP_RENEWAL";
    public static final String VT_FC_CANCEL = "VT_FC_CANCEL";
    public static final String VT_FC_SUSPEND = "VT_FC_SUSPEND";
    public static final String VT_FC_RESTORE = "VT_FC_RESTORE";
    public static final String VT_RC_SUSPEND = "VT_RC_SUSPEND";
    public static final String VT_RC_RESTORE = "VT_RC_RESTORE";
    public static final String VT_VEHICLE_CHECK = "VT_VEHICLE_CHECK";
    public static final String VT_TAX_BALANCE = "VT_TAX_BALANCE";
    public static final String VT_TAX_INSTALLMENT = "VT_TAX_INSTALLMENT";
    public static final String VT_TAX_INSTALLMENT_BRKUP = "VT_TAX_INSTALLMENT_BRKUP";
    public static final String VT_RECP_NO_CORRECTION = "VT_RECP_NO_CORRECTION";
    public static final String VT_EURO_VEHICLE = "VT_EURO_VEHICLE";
    public static final String VT_VEHICLE_SWAP = "VT_VEHICLE_SWAP";
    public static final String VT_RETROFITTER_DATA = "VT_RETROFITTER_DATA";
    public static final String VT_TAX_REFUND = "VT_TAX_REFUND";
    public static final String VT_TAX_ADJUSTMENT = "VT_TAX_ADJUSTMENT";  //MohnishKr---1stOct2008--//
    public static final String VT_TAX_EXEM_CERT = "VT_TAX_EXEM_CERT";//kml 30.09.2008
    public static final String VT_FINANCER_RC = "VT_FINANCER_RC ";//DIV 09-02-2009
    public static final String VT_DUP_FIT_CERTIFICATE = "VT_DUP_FIT_CERTIFICATE";
    public static final String VT_DUP_RC_CERTIFICATE = "VT_DUP_RC_CERTIFICATE";
    public static final String VT_DUPLICATE_PMT_PRINT = "VT_DUPLICATE_PMT_PRINT";

    //kml 02-02-2009
    public static final String VT_CYL_DTLS = "VT_CYL_DTLS";
    public static final String VT_KIT_DTLS = "VT_KIT_DTLS";
    //venkat 220409
    public static final String VT_TEMP_APPROVE = "VT_TEMP_APPROVE";

    public static final String VT_TEMP_APPROVE_DEALER = "VT_TEMP_APPROVE_DEALER";

    //venkat for dealer tax details
    public static final String VT_OTH_OFF_TAX_PAYMENT = "VT_OTH_OFF_TAX_PAYMENT";
    public static final String VT_OTH_OFF_PAYMENT_DEALER = "VT_OTH_OFF_PAYMENT_DEALER";
    //Akshey For Owner Acceptance 14-10-2009
    public static final String VT_OWNER_DECLARE = "VT_OWNER_DECLARE";
    public static final String VT_TAX_SC = "VT_TAX_SC";
    public static final String VT_GREEN_TAX = "VT_GREEN_TAX";
    //Subhasis--20Sep2010---------------
    public static final String VT_P_AUTH = "VT_P_AUTH";
    public static final String VT_ALREADY_TAX_PAID = "VT_ALREADY_TAX_PAID";//vnkt 07Mar2011

    public static final String VH_P_AUTH_HIST = "VH_P_AUTH_HIST";

    public static final String VT_OWNER_HSRP = "VT_OWNER_HSRP";
    public static final String VH_OWNER_HSRP = "VH_OWNER_HSRP";
    public static final String HSRP_AUTH_PRINT = "HSRP_AUTH_PRINT";

    //......................................................................
    // SYSTEM MASTER TABLES
    //......................................................................    
    public static final String VSM_PMTFEE_MAST = "VSM_PMTFEE_MAST";
    public static final String VSM_PMTNO_MAST = "VSM_PMTNO_MAST";
    public static final String VSM_PRINT_HEAD = "VSM_PRINT_HEAD";
    public static final String VSM_RCPTNO_MAST = "VSM_RCPTNO_MAST";
    public static final String VSM_RUNNING_NO_MAST = "VSM_RUNNING_NO_MAST";
    public static final String VSM_TEMP_MAST = "VSM_TEMP_MAST";
    public static final String VSM_TRADE_CERT_MAST = "VSM_TRADE_CERT_MAST";
    public static final String VSM_UP_MAST = "VSM_UP_MAST";
    public static final String VSM_VEHMAP_TAB = "VSM_VEHMAP_TAB";
    public static final String VSM_CHOICE_ORDER = "VSM_CHOICE_ORDER";
    public static final String VSM_SERVICES = "VSM_SERVICES";
    public static final String VSM_ACTION = "VSM_ACTION";
    public static final String VSM_OFFENCES = "VSM_OFFENCES";
    public static final String VSM_PMT_SUBTYPE = "VSM_PMT_SUBTYPE";
    public static final String VSM_TAX_PARAM_MAP = "VSM_TAX_PARAM_MAP";
    public static final String VSM_PURPOSE_CODE = "VSM_PURPOSE_CODE";
    public static final String VSM_NOC_NUM = "VSM_NOC_NUM";
    public static final String VSM_FANCY_NUM = "VSM_FANCY_NUM";  // MohnishKr----28thAug2008
    public static final String VSM_RCPTNO_MAST_BACKLOG = "VSM_RCPTNO_MAST_BACKLOG"; //venkat for backlog V/A
    //......................................................................
    // SYSTEM TRANSACTION TABLES
    //......................................................................    
    public static final String VST_ROLES = "VST_ROLES";
    public static final String VST_ROLES_SERVICES = "VST_ROLES_SERVICES";
    public static final String VST_USERS = "VST_USERS";
    public static final String VST_USERS_ROLES = "VST_USERS_ROLES";
    public static final String VST_BACKLOG = "VST_BACKLOG";
    public static final String VST_VEHNO_MAST = "VST_VEHNO_MAST";
    public static final String VST_LOGIN_INFO = "VST_LOGIN_INFO"; //DIV21july2008
    //......................................................................
    // HISTORY TABLES
    //......................................................................    
    public static final String VH_ACCOUNT_CANCEL = "VH_ACCOUNT_CANCEL";
    public static final String VH_ADD_HIST = "VH_ADD_HIST";
    public static final String VH_AUTH_HIST = "VH_AUTH_HIST";
    public static final String VH_CHALLAN_HIST = "VH_CHALLAN_HIST";
    public static final String VH_DOC_IMPND_HIST = "VH_DOC_IMPND_HIST";
    public static final String VH_FC_PRINT = "VH_FC_PRINT";
    public static final String VH_FITNESS_FEE_CANCEL = "VH_FITNESS_FEE_CANCEL";
    public static final String VH_FITNESS_PAST = "VH_FITNESS_PAST";
    public static final String VH_GOODS_PERMIT_HIST = "VH_GOODS_PERMIT_HIST";
    public static final String VH_HYPTH_PAST = "VH_HYPTH_PAST";
    public static final String VH_OWNER_ADMIN = "VH_OWNER_ADMIN";
    public static final String VH_P_OWNER = "VH_P_OWNER";
    public static final String VH_PERMIT_HIST = "VH_PERMIT_HIST";
    public static final String VH_PMT_PRINT = "VH_PMT_PRINT";
    public static final String VH_PMTFEE_CANCEL = "VH_PMTFEE_CANCEL";
    public static final String VH_RC_PRINT = "VH_RC_PRINT";
    public static final String VH_RC_SURR_HIST = "VH_RC_SURR_HIST";
    public static final String VH_RE_ASSIGN = "VH_RE_ASSIGN";
    public static final String VH_TRADE_CERT_HIST = "VH_TRADE_CERT_HIST";
    public static final String VH_VEH_ALTER_HIST = "VH_VEH_ALTER_HIST";
    public static final String VH_VEH_CONV = "VH_VEH_CONV";
    public static final String VT_VEH_CONVERSION = "VT_VEH_CONVERSION";
    public static final String VH_VEH_IMPND_HIST = "VH_VEH_IMPND_HIST";
    public static final String VH_VEH_OFFENCE_HIST = "VH_VEH_OFFENCE_HIST";
    public static final String VH_VEHINS_HIST = "VH_VEHINS_HIST";
    public static final String VH_VERIFY_FITNESS_INFO = "VH_VERIFY_FITNESS_INFO";
    public static final String VH_VERIFY_HYPTH = "VH_VERIFY_HYPTH";
    public static final String VH_VERIFY_HYPTH_PAST = "VH_VERIFY_HYPTH_PAST";
    public static final String VH_VERIFY_NOC = "VH_VERIFY_NOC";
    public static final String VH_VERIFY_OWNER = "VH_VERIFY_OWNER";
    public static final String VH_VERIFY_PVTRE_REGN = "VH_VERIFY_PVTRE_REGN";
    public static final String VH_VERIFY_TEMP_OWNER = "VH_VERIFY_TEMP_OWNER";
    public static final String VH_VERIFY_HYPTH_CONT = "VH_VERIFY_HYPTH_CONT";
    public static final String VH_VERIFY_TEMP_PERMIT = "VH_VERIFY_TEMP_PERMIT";
    public static final String VH_VERIFY_RC_SURR = "VH_VERIFY_RC_SURR";
    public static final String VH_VERIFY_RC_SURR_HIST = "VH_VERIFY_RC_SURR_HIST";
    public static final String VH_OWNER_CANCEL_RCPT = "VH_OWNER_CANCEL_RCPT";
    public static final String VH_SIDE_TRAILER = "VH_SIDE_TRAILER";
    public static final String VH_REMOVED_OFFENCE = "VH_REMOVED_OFFENCE";
    public static final String VH_DSA_PENDING_CANCEL = "VH_DSA_PENDING_CANCEL";
    public static final String VH_ENFORCEMENT_COLL_CANCEL = "VH_ENFORCEMENT_COLL_CANCEL";
    public static final String VH_ACTION = "VH_ACTION";
    public static final String VH_VEH_CATG = "VH_VEH_CATG";
    public static final String VH_PMTFEE_MAST = "VH_PMTFEE_MAST";

    //kml 02-02-2009
    public static final String VH_CYL_DTLS = "VH_CYL_DTLS";
    public static final String VH_KIT_DTLS = "VH_KIT_DTLS";
    public static final String VH_NOC_HIST = "VH_NOC_HIST";
    public static final String VH_FANCY_REGISTER = "VH_FANCY_REGISTER";//venkat for fancy history

    /**
     * Smart card related tables.
     */
    public static final String SMART_CARD = "SMART_CARD";
    public static final String RC_BE_TO_BO = "RC_BE_TO_BO";
    public static final String SMARTCARD_UPDATE_STATUS = "SMARTCARD_UPDATE_STATUS";
    public static final String SMARTCARD_DATA = "SMARTCARD_DATA";
    public static final String VM_MAKER_NEW = "VM_MAKER_NEW";
    public static final String VM_VHCLASS_CD_NEW = "VM_VHCLASS_CD_NEW";
    public static final String VM_BD_TYPE_NEW = "VM_BD_TYPE_NEW";
    public static final String SMART_CARD_UPDATE = "SMART_CARD_UPDATE";
    public static final String VH_OWNER_HIST = "VH_OWNER_HIST";
    public static final String TAXTOKEN_PRINT = "TAXTOKEN_PRINT";//vnkt for online 27Nov2011
    public static final String HSRP_VENDOR = "HSRP_VENDOR";

    public static final String VT_OWNER_INFO = "VT_OWNER_INFO";
    public static final String VH_OWNER_INFO_HIST = "VH_OWNER_INFO_HIST";
    public static final String VT_SMS_ALERT = "VT_SMS_ALERT";
    public static final String VEH_NUM_MAST = "VEH_NUM_MAST";
    public static final String VT_USED_SERIES = "VT_USED_SERIES";

    //......................................................................
    // Different Master Tables where the first column is CODE but there is
    // no second column named DESCR. All other master tables VM_*
    // contains first two columns as CODE and DESCR.
    //......................................................................
    public static final String[] DIFFERENT_MASTER_TABLES = {
        VM_ROUTE_MASTER,
        VM_VIA_MASTER,
        VM_MODELS,};

    //......................................................................
    // Tells if the given master table need to be handled separately.
    // Most of the master tables contains first two columns as CODE and DESCR.
    // But in some master tables (VM_*) first column (CODE) is there but 
    // the second column is not DESCR. This method tells if the given
    // master table is different in this regards.
    //......................................................................
    /**
     * @param tableName Master table name
     *
     * @return True if the master table is different
     *
     */
    public static boolean isDifferentMasterTable(String tableName) {
        for (int i = 0; i < DIFFERENT_MASTER_TABLES.length; i++) {
            if (DIFFERENT_MASTER_TABLES[i].equals(tableName)) {
                return true;
            }
        }

        return false;
    }
}

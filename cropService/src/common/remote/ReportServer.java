package common.remote;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : ReportServer
// PURPOSE          :  Report Interface
// NOTES            : None
// LAST MODIFIED    :
//  20040423 AJS013 Added getAuditReport()
//  20040227 ASF011 Added getDayEndStatement ()
//  20040225 AJS012 Added getAuditRecovery(),getBalanceTax(),getTaxExempted()
//  20040223 AKT025 Auditing - Removed 'Superfluous Content'
//  20040207 PAN014 Implemented RC format for Orissa
//  20040130 AJS011 Added getTaxUptoDate(),getDemandNotice(),getTaxStatementDetail()
//  20040113 AKT021 Added getBacklogUsers()
//  20040101 JIS025 Corrected Javadoc
//  20031229 AKT019 Created 
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.rmi.*;
//
// Importing Common java packages/classes
//
import common.*;
import common.report.*;

/**
 * Report service methods.
 *
 * @author AKT
 */
public interface ReportServer extends Remote {

    /**
     * Get Vehicle summary report from given parameter.
     *
     * @param fromDate Date Registration date Lower Limit.
     * @param toDate Date Registration date Upper Limit.
     * @param vhClass Vehicle class code
     * @param regnType Registration type (Private, Passanger, Goods)
     * @param bodyType Body type
     * @param fuel Fuel type
     * @param ownerCD Owner code
     * @param manufacture ManuFacturer code
     * @param model Model code
     *
     * @return ReportData object
     *
     * @throws RemoteException
     * @throws ClientException
     */
    ReportData getRegVehicleSummary(String fromDate, String toDate, String vhClass,
            String regnType, String bodyType, String fuel,
            String ownerCD, String manufacture,
            String model)
            throws RemoteException, ClientException;

    /**
     * Get Vehicle summary report from given parameter.
     *
     * @param fromDate Date Registration date Lower Limit.
     * @param toDate Date Registration date Upper Limit.
     * @param regNo Registration Number of vehicle
     * @param chassisNo Chassis Number of vehicle
     * @param engineNo Engine Number of vehicle
     * @param oName Owner's Name of the vehicle
     * @param fName Father's Name
     * @param manuYear Manufacturer year
     * @param purchaseDt Purchase Date of vehicle
     * @param add1 Address1 of the Owner of the vehicle
     * @param city City
     * @param pincode Pin Code
     *
     * @return ReportData object
     * @throws RemoteException
     * @throws ClientException
     */
    ReportData getRegVehicleSummary(String fromDate, String toDate, String regNo,
            String chassisNo, String engineNo, String oName,
            String fName, String manuYear, String purchaseDt,
            String add1, String city, String pincode)
            throws RemoteException, ClientException;

    /**
     * Get Fitness Pending summary report from given parameter.
     *
     * @param vhClass Vehicle class code
     * @param regnType Registration type (Private, Passanger, Goods)
     *
     * @return ReportData object
     *
     * @throws RemoteException
     * @throws ClientException
     */
    ReportData getFitVehicleSummary(String vhClass, String regnType)
            throws RemoteException, ClientException;

    /**
     * Get Vehicle summary report from given parameter.
     *
     * @param fromDate Date Registration date Lower Limit.
     * @param toDate Date Registration date Upper Limit.
     * @param pmtType Permit Type
     * @param pmtCatg Permit Category
     * @param option types of Permit (i.e. Passanger or Goods)
     * @param regnType Registration type
     * @param showExpired true if Show Expired check box is selected otherwise
     * false
     *
     * @return ReportData object
     *
     * @throws RemoteException
     * @throws ClientException
     */
    ReportData getPermitVehicleSummary(String fromDate, String toDate, String pmtType,
            String pmtCatg, String option, String regnType,
            boolean showExpired)
            throws RemoteException, ClientException;

    /**
     * Get the Services entered by Backlog Users
     *
     * @param userId UserId
     * @param module Module Name
     *
     * @return ReportData object
     *
     * @throws RemoteException
     * @throws ClientException
     */
    ReportData getBacklogUsers(String userId, String module)
            throws RemoteException, ClientException;

    /**
     * Get the Tax Upto Date.
     *
     * @param regnNo Registration Number
     * @param flag = 1 Chassis Number = 2 Registration Number
     *
     * @return ReportData object
     *
     * @throws RemoteException
     * @throws ClientException
     */
    ReportData getTaxUptoDate(String regnNo, int flag)
            throws RemoteException, ClientException;

    /**
     * Get the Demand Notice Detail.
     *
     * @param regnNo Registration Number
     * @param taxMode Tax Mode
     * @param payDate Payment Date of Tax
     *
     * @return ReportData object
     *
     * @throws RemoteException
     * @throws ClientException
     */
    ReportData getDemandNotice(String regnNo, String taxMode, String payDate)
            throws RemoteException, ClientException;

    /**
     * Get the Tax Statement Detail.
     *
     * @param chassisNo_vehNo RegnNo or chassisNo
     * @param flag = 1 Chassis Number = 2 Registration Number
     *
     * @return ReportData object
     *
     * @throws RemoteException
     * @throws ClientException
     */
    ReportData getTaxStatementDetail(String chassisNo_vehNo, int flag)
            throws RemoteException, ClientException;

    /**
     * Generate Certificate printData for given vehicle Number.
     *
     * @param vehNo Vehicle Number
     * @param vehType Vehicle Type
     *
     * @return object for the Certificate
     */
    ReportData getRCDataByVehicleNo(String vehNo, int vehType)
            throws RemoteException, ClientException;

    /**
     * Generate Registration Certificate printData for given receipt Number.
     *
     * @param receiptNo Receipt Number
     * @param rcptType Receipt Type
     *
     * @return object for the Certificate
     */
    ReportData getRCDataByReceiptNo(String receiptNo, int rcptType)
            throws RemoteException, ClientException;

    /**
     * Get Day End Fee Account Statement report from given parameter.
     *
     * @param tableNm Table Name for Account Summary
     *
     * @throws RemoteException
     * @throws ClientException
     */
    public ReportData getDayEndStatement(String tableNm)
            throws RemoteException, ClientException;

    /**
     * Generate Audit Recovery printData for given dates.
     *
     * @param fromDate Registration From Date
     * @param uptoDate Registration Upto Date
     *
     * @return object for the Audit recovery
     */
    public ReportData getAuditRecovery(String fromDate, String uptoDate)
            throws RemoteException, ClientException;

    /**
     * Generate Balance Tax printData for given dates.
     *
     * @param fromDate Registration From Date
     * @param uptoDate Registration Upto Date
     *
     * @return object for the Balance Tax
     */
    public ReportData getBalanceTax(String fromDate, String uptoDate)
            throws RemoteException, ClientException;

    /**
     * Generate Tax Exempted printData for given dates.
     *
     * @param fromDate Registration From Date
     * @param uptoDate Registration Upto Date
     *
     * @return object for the Tax Exempted
     */
    public ReportData getTaxExempted(String fromDate, String uptoDate)
            throws RemoteException, ClientException;

    /**
     * Generate Audit Report printData for given dates.
     *
     * @param userId User ID
     * @param subMdoule Sub MOdule
     *
     * @return object for the Audit Report
     */
    public ReportData getAuditReport(String userId, String subModule)
            throws RemoteException, ClientException;

    /**
     * Generate Theft Reported Vehicles for given dates.
     *
     * @param fromDate FIR From Date
     * @param uptoDate FIR Upto Date
     *
     * @return object for the Theft Report
     */
    public ReportData getTheftReport(String fromDate, String uptoDate)
            throws RemoteException, ClientException;

    /**
     * Generate Temporary Vehicles List
     *
     * @return object for the Temporaray Vehicle
     */
    public ReportData getTempVehReport()
            throws RemoteException, ClientException;

    /**
     * Generate Verified Transaction List
     *
     * @return object for the Verified Transaction
     */
    public ReportData getVerifyTransReport()
            throws RemoteException, ClientException;

    /**
     * Generate Approved Transaction List
     *
     * @return object for the Approved Transaction
     */
    public ReportData getAproveTransReport()
            throws RemoteException, ClientException;

    /**
     * Generate NOC Vehicles List
     *
     * @return object for the NOC vehicles
     */
    public ReportData getNOCReport()
            throws RemoteException, ClientException;

    public ReportData getRegMiscReport(String option, String fromDate, String toDate)
            throws RemoteException, ClientException;

    public ReportData getCountRegisterVehicleReport(String vehClass, String fromDate, String toDate)
            throws RemoteException, ClientException;

    public ReportData getTaxTokenPrintVehicles(String fromDate, String toDate)
            throws RemoteException, ClientException;

    public ReportData getTaxClearedVehicles(String regn_no, String tax_from, String tax_upto)
            throws RemoteException, ClientException;

    public ReportData getTaxClearedVehiclesPostaCharges(String from, String upto)
            throws RemoteException, ClientException;

    public ReportData getScreenReport(String regn_no)
            throws RemoteException, ClientException;

}

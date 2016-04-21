package common.remote;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : PrinterServer
// PURPOSE          :  Printer Interface
// NOTES            : None
// LAST MODIFIED    :
//  20040227 ASF011 Removed makeCashStatementPrintData() and makeConsolidatedStatementPrintData()
//  20040223 AKT025 Auditing - Removed 'Superfluous Content'
//  20031015 CPO012 Added  makeCashStatementPrintData() and makeConsolidatedStatementPrintData()
// 20030818 CPO007 Created - Added makeCertificatePrintDataByVehicleNo(), 
//                 makeCertificatePrintDataByReceiptNo(), makeRegisterPrintData()
//                 (Created by GUM original file from YOG)
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
import common.printing.*;

/**
 * Printing service methods.
 *
 * @author GUM
 */
public interface PrinterServer extends Remote {

    /**
     * Generate Receipt print data for given Receipt Number.
     *
     * @param receiptNo Receipt Number
     * @param rcptType Recipt Type
     *
     * @return Print Data object for the receipt
     */
    PrintData makeReceiptPrintData(String receiptNo, int rcptType)
            throws RemoteException, ClientException;

    /**
     * Generate Certificate print data for given Vehicle Number.
     *
     * @param vehNo Vehicle Number
     * @param vehType Vehicle Type
     *
     * @return Print Data object for the receipt
     */
    PrintData makeCertificatePrintDataByVehicleNo(String vehNo, int vehType)
            throws RemoteException, ClientException;

    /**
     * Generate Certificate print data for given Receipt Number.
     *
     * @param receiptNo Receipt Number
     * @param rcptType Recipt Type
     *
     * @return Print Data object for Certificate
     */
    PrintData makeCertificatePrintDataByReceiptNo(String receiptNo, int rcptType)
            throws RemoteException, ClientException;

    /**
     * Generate Register print data for given Registration Dates.
     *
     * @param regnDate1 Registration date Lower Limit.
     * @param regnDate2 Registration date Upper Limit.
     * @param vehType Vehicle Type
     *
     * @return Print Data object for Register Printing
     */
    PrintData makeRegisterPrintData(String regnDate1, String regnDate2, int vehType)
            throws RemoteException, ClientException;

    /**
     * Generate Owner Acceptance Form print data for given Receipt No.
     *
     * @param receiptNo Receipt No
     * @param vehNo Vehicle Number
     * @param vehType Vehicle Type
     *
     * @return Print Data object for Owner Accept Printing
     */
    PrintData makePrintUserAcceptanceForm(String receiptNo, String VehNo, int vehType)
            throws RemoteException, ClientException;

    // Added By Prashant Kumar Singh -- 4_aug_2010
    PrintData makeForm20forVehicle(String madeVehno, int printConstant) throws RemoteException, ClientException;

}

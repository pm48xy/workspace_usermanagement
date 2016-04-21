package server.transaction;
/////////////////////////////////////////////////////////////////////////////
// CLASS            : Transaction
// PURPOSE          : Class used for generating unique transaction id
// NOTES            : None
// LAST MODIFIED    :
//
//////////////////////////////////////////////////////////////////////////////
// Copyright 2010 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
/////////////////////////////////////////////////////////////////////////////

/**
 * Importing standard java classes
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Importing Client/Common java classes
 */
import nic.java.util.Debug;
import common.ClientException;
import server.db.connection.TransactionManagerLocal;

/**
 * This class is used for generating unique transaction id
 *
 * @author Tapas Kumar Patra
 */
public class Transaction {

    /**
     * Method to get the unique temporary transaction id
     *
     * @param tmgr
     * @return
     * @throws common.ClientException
     */
    public synchronized String getUniqueTempTransactionId(TransactionManagerLocal tmgr) throws ClientException {
        //Generate Unique Temporary Transaction No
        long seqNo = 0;
        int digits = 10;
        String whereiam = "PDOFormPermitNationalPermitDetails.getUniqueTempTransactionId()";
        String strTempTransactionNo = "";
        String strQry = "UPDATE VP_SEQUENCE_NO SET TEMP_SEQUENCE_NO = TEMP_SEQUENCE_NO + 1 ";

        try {
            tmgr.executeDML(strQry);
            strQry = "SELECT STATE_CD, RTO_CD, TEMP_SERIES, TEMP_SEQUENCE_NO FROM VP_SEQUENCE_NO ";
            PreparedStatement pstmt = tmgr.prepareStatement(strQry);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                strTempTransactionNo = rs.getString("STATE_CD").trim() + rs.getString("RTO_CD").trim() + rs.getString("TEMP_SERIES").trim();
                seqNo = rs.getLong("TEMP_SEQUENCE_NO") - 1;
            }
            digits = 16 - strTempTransactionNo.length();
            String format = String.format("%%0%dd", digits);
            String z = String.format(format, seqNo);
            strTempTransactionNo = strTempTransactionNo + z;
        } catch (SQLException sqle) {
            Debug.logexc(sqle, whereiam);
            throw new ClientException(sqle.getMessage());
        }
        return strTempTransactionNo;
    }

    /**
     * Method to get the unique temporary transaction id
     *
     * @param tmgr
     * @return
     * @throws common.ClientException
     */
    public synchronized String getUniqueRcptTransactionId(TransactionManagerLocal tmgr) throws ClientException {
        //Generate Unique Temporary Transaction No
        long seqNo = 0;
        int digits = 10;
        String whereiam = "PDOFormPermitNationalPermitDetails.getUniqueRcptTransactionId()";
        String strRcptTransactionNo = "";
        String strQry = "UPDATE VP_SEQUENCE_NO SET " + "  RCPT_SEQUENCE_NO = RCPT_SEQUENCE_NO + 1 ";
        try {
            tmgr.executeDML(strQry);
            strQry = "SELECT STATE_CD, RTO_CD, RCPT_SERIES, RCPT_SEQUENCE_NO FROM VP_SEQUENCE_NO";
            PreparedStatement pstmt = tmgr.prepareStatement(strQry);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                strRcptTransactionNo = rs.getString("STATE_CD").trim() + rs.getString("RTO_CD").trim() + rs.getString("RCPT_SERIES").trim();
                seqNo = rs.getLong("RCPT_SEQUENCE_NO") - 1;
            }
            digits = 16 - strRcptTransactionNo.length();
            String format = String.format("%%0%dd", digits);
            String z = String.format(format, seqNo);
            strRcptTransactionNo = strRcptTransactionNo + z;
        } catch (SQLException sqle) {
            Debug.logexc(sqle, whereiam);
            throw new ClientException(sqle.getMessage());
        }
        return strRcptTransactionNo;
    }

}

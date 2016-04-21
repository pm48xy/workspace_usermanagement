package common;
///////////////////////////////////////////////////////////////////////////////
// CLASS            : PrintConstants
// PURPOSE          : Defines constants specific to database table structure.
// NOTES            : None
// LAST MODIFIED    :
//  20031015 CPO012 Added PRINT_CASH_STMT and PRINT_CONSOLIDATED_STMT Print Constant
//  20030818 CPO007 Created (GUM)
///////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//
// NONE
//
// Importing Common java packages/classes
//
// NONE

/**
 * Defines constants specific to printing.
 *
 * @author GUM
 */
public interface PrintConstants {

    /**
     * Fitness receipt flag
     */
    int PRINT_FITNESS_RCPT = 1;

    /**
     * Permit receipt flag
     */
    int PRINT_PERMIT_RCPT = 2;

    /**
     * Registration receipt flag - for registered vehicle fee screen
     */
    int PRINT_REGISTRATION_RCPT = 3;

    /**
     * RC flag
     */
    int PRINT_RC = 4;

    /**
     * Temporary Registration Fee receipt flag
     */
    int PRINT_TEMP_REG_FEE_RCPT = 5;

    /**
     * Tax receipt flag
     */
    int PRINT_TAX_RCPT = 6;

    /**
     * DSA Fee Collection receipt flag
     */
    int PRINT_DSA_RCPT = 7;

    /**
     * Fitness register flag
     */
    int PRINT_FR = 8;

    /**
     * Vehicle Particulars flag
     */
    int PRINT_VEHICLE_PARTICULARS = 9;

    /**
     * FC flag
     */
    int PRINT_FC = 10;

    /**
     * PR flag
     */
    int PRINT_PR = 11;

    /**
     * Vehicle register flag
     */
    int PRINT_VR = 12;

    /**
     * Registration receipt flag - for New vehicle fee screen
     */
    int PRINT_NEW_VEHICLE_RCPT = 13;

    /**
     * Cash statement flag
     */
    int PRINT_CASH_STMT = 14;

    /**
     * Cash statement flag
     */
    int PRINT_CONSOLIDATED_STMT = 15;

    /**
     * Fee Adjustment Registration*
     */
    int PRINT_ADJUSTMENT = 16;

    /**
     * Fee Adjustment Fitness*
     */
    int PRINT_FITNESS_ADJUSTMENT = 17;

    /**
     * NOC flag
     */
    int PRINT_NOC = 18;

    /* choice number */
    int PRINT_FEE_CHOICE = 19;

    /**
     * Print Owner Acceptance Form
     */
    int PRINT_OA_FORM = 20;

    /*Print temp reg cert*/
    int PRINT_TEMP_REG = 21;

    int PRINT_FORM_24 = 22; // Added By prashant -- 4_aug_2010

    /**
     * Case for printing of Contract Carriage Permit
     */
    int PRINT_CC_AUTO_PERMIT = 30;
    int PRINT_CC_PERMIT = 52;
    int PRINT_ALL_IND_TOURIST_PERMIT = 44;
    int PRINT_SC_PERMIT = 23; // Used for Printing Stage Carriage Permit
    int PRINT_INS_CC_NCR_DEL = 47;
    int PRINT_INS_CC_NOT_NCR_DEL = 46;
    int PRINT_INS_STAGE_CARRIAGE = 45;
    int PRINT_GC_PERMIT = 24;
    int PRINT_GC_PERMIT_PART_B = 32;
    int PRINT_AUTH_PERMIT1 = 33;
    int PRINT_NP_PERMIT = 26;       // Added By Prashant-- 17Aug2010
    int PRINT_TMP_PERMIT = 29;
    int PRINT_AUTH_PERMIT = 31;

    int PRINT_TEMP_REGISTRATION_RCPT = 27; // Added By Prashant - 27_Oct_2010

    int PRINT_AUTH_SLIP = 28;

}

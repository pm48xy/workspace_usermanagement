package nic.java.util;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : Debug
// PURPOSE          : Provides static methods used for Debugging.
// NOTES            : None
// LAST MODIFIED    :
//  20030926 GUM020 Implemented TransactionManager
//  20030919 GUM019 Package re-structuring
//  20030310 RCN001 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.io.*;
import javax.servlet.http.*;

/**
 * Provides Debug/Log System. Provides static methods used for Debugging.
 *
 * @author RCN
 */
abstract public class Debug {

    /**
     * DEBUG (To be set from outside)
     */
    public static boolean DEBUG = false;     // Default
    /**
     * LOG (To be set from outside)
     */
    public static boolean LOG = true;        // Default
    /**
     * LOG_SQL (To be set from outside)
     */
    public static boolean LOG_SQL = true;    // Default
    /**
     * LOG_SQL_SELECT (To be set inside only)
     */
    public static boolean LOG_SQL_SELECT = true;    // Default

    //Done by GES so that log file does not contain select stmts
    //public static boolean LOG_SQL_SELECT = false;
    //Done by GES so that log file contain insert and update stmts only
    //public static boolean LOG_SQL_UPDATE = true;
    //public static boolean LOG_SQL_INSERT = true;
    /**
     * DEBUG_GC : Show the GC messages if true
     */
    public static final boolean DEBUG_GC = false;
    /**
     * DEBUG_OC : Show the Object Creation messages if true
     */
    public static final boolean DEBUG_OC = false;

    /**
     * Bug flag
     */
    public static final String BUG = "[BUG]|";
    /**
     * Exception flag
     */
    public static final String EXC = "<EXC>|";
    /**
     * Error flag
     */
    public static final String ERR = "[ERR]|";
    /**
     * Warning flag
     */
    public static final String WRN = "[WRN]|";
    /**
     * Status flag
     */
    public static final String STA = " ... |";
    /**
     * Info flag
     */
    public static final String INF = " ___ |";
    /**
     * Data value print flag
     */
    public static final String PRI = "     |";
    /**
     * SQL print flag
     */
    public static final String SQL = "     |";
    /**
     * User logged-in flag
     */
    public static final String USI = "USR-I|";
    /**
     * User logged-out flag
     */
    public static final String USO = "USR-O|";
    /**
     * Email-Sending flag
     */
    public static final String EMA = "email|";
    /**
     * Transaction flag
     */
    public static final String TNX = "[TNX]|";

    /**
     * Provided so that System.out.println() can be accessed easily in DEBUG
     * mode.
     *
     * @param str Text in the format like (Debug.BUG + "My message")
     */
    private static final void print(String str) {
        java.util.Date date = new java.util.Date();
        System.out.println(str.substring(0, BUG.length())
                + DateUtils.getDateInDDMMYYYY_HHMMSS(date)
                + "| "
                + str.substring(BUG.length(), str.length()));
    }

    /**
     * Provided so that System.out.println() can be accessed easily in DEBUG
     * mode.
     *
     * @param str Text in the format like (Debug.BUG + "My message")
     */
    public static final void Sop(String str) {
        if (DEBUG) {
            print(str);
        }
    }

    /**
     * No matter DEBUG flag is on or off, print the message. This is going to be
     * used for Check Constraint Violation.
     *
     * @param str Text.
     */
    public static final void SopC(String str) {
        System.out.println("CHK_VI : " + str);
    }

    /**
     * Do no System.out.println().
     *
     * @param str Text in the format like (Debug.BUG + "My message")
     */
    public static final void SopX(String str) {
    }

    /**
     * Provided so that GC messages can be displayed if DEBUG_GC is true
     *
     * @param str Text in the format like (Debug.BUG + "My message")
     */
    public static final void SopGC(String str) {
        if (DEBUG_GC) {
            Sop(str);
        }
    }

    /**
     * Provided so that Object Creation messages can be displayed if DEBUG_OC is
     * true
     *
     * @param str Text in the format like (Debug.BUG + "My message")
     */
    public static final void SopOC(String str) {
        if (DEBUG_OC) {
            Sop(str);
        }
    }

    /**
     * Provided so that System.out.println() can be accessed easily in LOG mode.
     *
     * @param str Text in the format like (Debug.BUG + "My message")
     */
    public static final void log(String str) {
        if (LOG) {
            print(str);
        }
    }

    /**
     * Provided so that System.out.println() can be accessed easily in LOG mode.
     *
     * @param str Text in the format like ("My message")
     */
    public static final void logWithoutStamp(String str) {
        if (LOG) {
            System.out.println(str);
        }
    }

    /**
     * Do no System.out.println() for logging statement.
     *
     * @param str Text in the format like (Debug.BUG + "My message")
     */
    public static final void logX(String str) {
    }

    /**
     * Provided so that System.out.println() can be accessed easily in LOG_SQL
     * mode.
     *
     * @param str SQL String
     */
    public static final void logsql(String str) {
        if (LOG_SQL) {
            boolean selectSql = str.substring(0, 6).toUpperCase().startsWith("SELECT");
            if (selectSql) {
                if (LOG_SQL_SELECT) {
                    print(SQL + "      s      " + str);
                }
            } else {
                print(SQL + str);
            }
        }
    }

    /**
     * public static final void logsql(String str) { if (LOG_SQL) { //boolean
     * insertSql = str.substring(0,6).toUpperCase().startsWith("INSERT");
     * //boolean updateSql =
     * str.substring(0,6).toUpperCase().startsWith("UPDATE"); boolean selectSql
     * = str.substring(0,6).toUpperCase().startsWith("SELECT"); if (!selectSql)
     * { print(SQL+" s "+str); }
     *
     * }
     * }
     */
    /**
     * Do no System.out.println() for logging sql statement.
     *
     * @param str SQL String
     */
    public static final void logsqlX(String str) {
    }

    /**
     * Provided so that System.out.println() can be accessed easily in LOG_EXC
     * mode (Exception).
     *
     * @param e Exception
     */
    public static final void logexc(Throwable e) {
        logexc(e, null);
    }

    /**
     * Provided so that System.out.println() can be accessed easily in LOG_EXC
     * mode (Exception).
     *
     * @param e Exception
     * @param str String message
     */
    public static final void logexc(Throwable e, String str) {
        String msg = (str != null) ? (" : " + str) : ("");
        print(EXC + e.getClass().getName() + " : " + e.getMessage() + msg);
    }

    /**
     * Transaction.
     *
     * @param str Message to print
     */
    public static final void logtnx(String str) {
        print(TNX + str);
    }
}

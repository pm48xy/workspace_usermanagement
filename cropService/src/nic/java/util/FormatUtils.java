package nic.java.util;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : FormatUtils
// PURPOSE          : Provides number of utility static methods and constants 
//                    for Formatting
// NOTES            : None
// LAST MODIFIED    :
//  20031117 JIS022 Added fdwc()
//  20030919 GUM019 Package re-structuring
//  20030811 AKS013 Auditing - TCC : Possible Errors
//  20030602 AKS003 QA modifications
//  20030528 GES001 str.trim().length() check in fd()
//  20030523 GUM005 Documentation corrected
//  20030519 RCN007 Added getRightAligned()
//  20030506 GUM002 Added function fs(), fd(), formatDateString(), toDate()
//  20030310 RCN001 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.util.*;
import java.text.*;
import java.text.NumberFormat;

/**
 * Provides number of utility static methods and constants for Formatting.
 *
 * @author RCN
 */
abstract public class FormatUtils {

    /**
     * Database name of the database used by the server. This variable is
     * initialized by server on SERVER side copy of this common class and by
     * client on CLIENT side copy of this common class. CARE must be taken to
     * initialize it properly on both sides. IDEALLY THIS VARIABLE SHOULD BE
     * INITIALIZED ONLY ONCE AT THE START TIME ON SERVER SIDE AND THEN ON CLIENT
     * SIDE. The client knows the database name by calling a remote method.
     *
     * Why we have done this : This is done to make widely used methods like
     * ff(), fd() to be used easily. Caller of these methods worry nothing about
     * the database used and call to these methods becomes database independent
     * for him. However these methods internally uses this variable to figure
     * out as how to do the data formating based on the database sytax.
     */
    public static String DB_SYSTEM = null;

    /**
     * Return an null string as empty string.
     *
     * @param str Given string object that could be null
     *
     * @return Returns "" if the given string object is null else the string as
     * it is returned.
     */
    public static String formatNullString(String str) {
        return str == null ? "" : str;
    }

    /**
     * Convert a given number into n digit format.
     *
     * @param n eg 5
     * @param x eg 1
     *
     * @return eg "00001"
     */
    public static String getInNDigitFormat(int n, int x) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumIntegerDigits(n);
        nf.setMaximumIntegerDigits(n);
        nf.setGroupingUsed(false); // Do not use grouping coma (eg 83,219,222.93)
        return nf.format(x);
    }

    /**
     * Get the given number as comma grouped. For example 1048576 will be
     * returned as 1,048,576
     *
     * @param x Given integral number
     *
     * @return 1,048,576 for 1048576
     */
    public static String getCommaGroupedNumber(int x) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setGroupingUsed(true);
        return nf.format(x);
    }

    /**
     * Right align a given number for the specified places
     *
     * @param value Number to be aligned
     * @param places Number of places of alignment
     */
    public static String getRightAligned(int value, int places) {
        String num = "" + value;
        int ndigit = num.length();
        if (places < 0 || places <= ndigit) {
            return num;
        }

        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < places - ndigit; i++) {
            sbuf.append(" ");
        }
        sbuf.append(num);

        return sbuf.toString();
    }

    /**
     * Formats a given String value in a sql format of type ('value', ) or
     * (null, ). This escapes the APOSTROPE (') char for Oracle so that string
     * can be entered via SQL statement
     *
     * @param field Given column field value
     *
     * @return ('value', ) or (null, ) after char ' properly escaped for SQL
     */
    public static String formatText(String field) {
        // Format
        if (field == null || (field.trim()).equals("")) {
            return "NULL, ";
        } else {
            // Escape single quotes if any
            String escfield = escapeSingleQuote(field);
            return "'" + escfield + "', ";
        }
    }

    /**
     * Wrapper method for the formatText() method.
     *
     * @param str Given string object that could be null
     *
     * @return Returns "NULL" if the given string object is null or "", else "'"
     * + str + "', " is returned.
     */
    public static String fs(String str) {
        return formatText(str);
    }

    //kml 15.10.2008 start
    /**
     * Wrapper method for the formatText() method. created for smart card only
     *
     * @param str Given string object that could be null
     *
     * @return Returns "NULL" if the given string object is null or "", else "'"
     * + str + "', " is returned.
     */
    public static String fs_smart(String str) {
        return formatText_smart(str);
    }

    /**
     * Formats a given String value in a sql format of type ('value', ) or
     * (null, ). This escapes the APOSTROPE (') char for Oracle so that string
     * can be entered via SQL statement
     *
     * created for smart card only
     *
     * @param field Given column field value
     *
     * @return ('value', ) or (null, ) after char ' properly escaped for SQL
     */
    public static String formatText_smart(String field) {
        // Format
        if (field == null) {
            return "NULL, ";
        } else {
            // Escape single quotes if any
            String escfield = escapeSingleQuote(field);
            return "'" + escfield + "', ";
        }
    }

    //kml 15.10.2008 end
    /**
     * Method to format a date string as per database requirements without comma
     * at the end.
     *
     * @param str Given string object that could be null.
     *
     * @return Returns "NULL" if the given string object is null or "" else date
     * in DB format as system settings.
     */
    public static String fdwc(String str) throws FormatUtilsException {
        String formattedDate = fd(str);
        int lastIndexOfComma = formattedDate.lastIndexOf(",");
        formattedDate = formattedDate.substring(0, lastIndexOfComma);

        // Return
        return formattedDate;
    }

    /**
     * Method to format a date string as per database requirements.
     *
     * @param str Given string object that could be null.
     *
     * @return Returns "NULL, " if the given string object is null or "" else
     * date in DB format as system settings.
     */
    public static String fd(String str) throws FormatUtilsException {
        String formattedDate = null;
        if (str != null && str.trim().length() > 0) {
            Date dt = toDate(str);

            if (dt == null) {
                throw new FormatUtilsException("BUG : Control should never come here :"
                        + " Null parsed date : " + str);
            }

            if (DB_SYSTEM == null) {
                throw new FormatUtilsException("BUG : Null database found");
            } else if (DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_DB2)) {
                formattedDate = "DATE('" + formatDateString(dt, "dd.MM.yyyy") + "'), ";
            } else if (DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_ORACLE)) {
                formattedDate = "TO_DATE('" + formatDateString(dt, "dd-MMM-yyyy") + "', 'DD-MON-YYYY'), ";
            } else if (DB_SYSTEM.equalsIgnoreCase(CommonUtils.DB_SYSTEM_MSSQL)) {
                formattedDate = "'" + formatDateString(dt, "dd-MMM-yyyy") + "', ";
            } else {
                throw new FormatUtilsException("BUG : Control should never come here :"
                        + " Unkown Db found : " + DB_SYSTEM);
            }
        } else {
            formattedDate = "NULL, ";
        }

        return formattedDate;
    }

    /**
     * Convert a date to required String format.
     *
     * @param date Given date.
     * @param format Given String ("dd-MM-yyyy" for 01-01-2000 format
     * "dd-MMM-YYYY" for 01-Jan-2000 format)
     *
     * @return Date in required format.
     */
    public static String formatDateString(java.util.Date date, String format) {
        if ((date == null) || (format == null)) {
            return null;
        }

        // Set the date
        SimpleDateFormat fm = new SimpleDateFormat(format);
        return fm.format(date);
    }

    /**
     * Convert a string to date.
     *
     * @param s Given date string
     *
     * @return Date object
     */
    public static Date toDate(String s) {
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT);
        try {
            return df.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Formats a given String value in a sql format of type ('value' ) or (null
     * ).
     *
     * @param field Given column field
     *
     * @return ('value' ) or (null )
     */
    public static String formatTextWithoutComma(String field) {
        // Format
        if (field == null || (field.trim()).equals("")) {
            return "null ";
        } else {
            // Escape single quotes if any
            String escfield = escapeSingleQuote(field);
            return "'" + escfield + "' ";
        }
    }

    /**
     * Formats a given String value in a sql format of type ('value' ) or (null
     * ).
     *
     * @param field Given column field
     *
     * @return ('value' ) or (null )
     */
    public static String fswc(String field) {
        return formatTextWithoutComma(field);
    }

    /**
     * Formats a given String value representing date in a sql format of type
     * ('to_date(...)' ) or (null ).
     *
     * @param date_field Given column date field
     * @param date_format Required format (eg DD/MM/YYYY)
     *
     * @return ('to_date(...)' ) or (null )
     */
    public static String formatDate(String date_field, String date_format)
            throws FormatUtilsException {

        if (date_field == null || (date_field.trim()).equals("")) {
            return "null, ";
        } else {
            return "to_date('" + date_field + "', '" + date_format + "'), ";
        }
    }

    /**
     * Formats a given String value representing date in a sql format of type
     * ('to_date(...)' ) or (null ) without the comma at the end.
     *
     * @param date_field Given column date field
     * @param date_format Required format (eg DD/MM/YYYY)
     *
     * @return ('to_date(...)' ) or (null )
     */
    public static String formatDateWithoutComma(String date_field,
            String date_format)
            throws FormatUtilsException {

        if (date_field == null || (date_field.trim()).equals("")) {
            return "null ";
        } else {
            return "to_date('" + date_field + "', '" + date_format + "') ";
        }
    }

    /**
     * Formats a given userid String based on whether it is "None" or some other
     * valid user name.
     *
     * @param userid User ID
     *
     * @return ('userid', ) or (null, )
     */
    public static String formatUser(String userid) {
        String s_USERID = "'" + userid + "', ";

        // Check special case of USERID for "None"
        if (userid == null) {
            s_USERID = "null, ";
        }

        return s_USERID;
    }

    /**
     * Formats a given userid String based on whether it is "None" or some other
     * valid user name Without Comma.
     *
     * @param userid User ID
     *
     * @return ('userid' ) or (null )
     */
    public static String formatUserWithoutComma(String userid) {
        String s_USERID = "'" + userid + "' ";

        // Check special case of USERID for "None"
        if (userid == null) {
            s_USERID = "null ";
        }

        return s_USERID;
    }

    /**
     * Format the HTML tags, Spaces, New Line etc in a given string so that the
     * string content can be shown properly in the browser. If the input string
     * obj is null, then returns empty string.
     *
     * @param str Given string.
     *
     * @return Formatted string (eg "Hello <B>Ram</B>" -> "Hello
     * &lt;B&gt;There&lt;/B&gt;". If the input string obj is null, then returns
     * empty string.
     */
    public static String formatHtmlTags(String str) {
        // If the input string obj is null, then returns
        // empty string.    
        if (str == null) {
            return "";
        }

        // Local Variables
        StringBuffer sb = new StringBuffer();
        int len = str.length();
        int largeWordLength = 0;
        char ch = '\u0000';

        // Process the given string
        for (int i = 0; i < len; i++) {
            ch = str.charAt(i);
            if (ch == ' ') {
                // Replace all "SPACE SPACE" situation with "&nbsp; SPACE"
                // SPECIAL_CASE :
                //      If the first char is a SPACE replace that
                //      with "&nbsp;" as <TT>...</TT> ignore all
                //      initial SPACES
                if ((i < len - 1 && str.charAt(i + 1) == ' ') || i == 0) {
                    sb.append("&nbsp;");
                } else {
                    sb.append(ch);
                }
                largeWordLength = 0;
            } else if (ch == '\n') {  // Note : On Win32 NEW_LINE is '\r\n'
                sb.append("<BR>");
                largeWordLength = 0;
            } else if (ch == '<') {
                sb.append("&lt;");
                largeWordLength++;
            } else if (ch == '>') {
                sb.append("&gt;");
                largeWordLength++;
            } else {
                sb.append(ch);
                largeWordLength++;
            }

            // If a word in the text is of size more than 60 chars
            // then break that word in two by putting a new line
            if (largeWordLength > 60) {
                sb.append("<BR>");
                largeWordLength = 0;
            }
        }
        return sb.toString();
    }

    /**
     * Format the HTML tags, Spaces, New Line etc in a given string so that the
     * string content can be shown properly in the browser. If the input string
     * obj is null, then returns empty string.
     *
     * @param str Given string.
     *
     * @return Formatted string (eg "Hello <B>Ram</B>" -> "Hello
     * &lt;B&gt;There&lt;/B&gt;". If the input string obj is null, then returns
     * empty string.
     */
    public static String formatHtmlTagsForXML(String str) {
        // If the input string obj is null, then returns
        // empty string.    
        if (str == null) {
            return "";
        }

        // Local Variables
        StringBuffer sb = new StringBuffer();
        int len = str.length();
        int largeWordLength = 0;
        char ch = '\u0000';

        // Process the given string
        for (int i = 0; i < len; i++) {
            ch = str.charAt(i);
            if (ch == ' ') {
                // Replace all "SPACE SPACE" situation with "&nbsp; SPACE"
                // SPECIAL_CASE :
                //      If the first char is a SPACE replace that
                //      with "&nbsp;" as <TT>...</TT> ignore all
                //      initial SPACES
                if ((i < len - 1 && str.charAt(i + 1) == ' ') || i == 0) {
                    sb.append("&amp;nbsp;");
                } else {
                    sb.append(ch);
                }
                largeWordLength = 0;
            } else if (ch == '<') {
                sb.append("&lt;");
                largeWordLength++;
            } else if (ch == '>') {
                sb.append("&gt;");
                largeWordLength++;
            } else if (ch == '&') {
                sb.append("&amp;");
                largeWordLength = 0;
            } else if (ch == '"') {
                sb.append("&quot;");
                largeWordLength = 0;
            } else if (ch == '\'') {
                sb.append("&apos;");
                largeWordLength = 0;
            } else {
                sb.append(ch);
                largeWordLength++;
            }

            // If a word in the text is of size more than 60 chars
            // then break that word in two by putting a new line
            if (largeWordLength > 60) {
                sb.append("&lt;BR&gt;");
                largeWordLength = 0;
            }
        }
        return sb.toString();
    }

    /**
     * Replaces every ' with '' in the given string. This is done so that the
     * SQL for Oracle text data can be entered that contains Single Quote.
     *
     * @param str Given string
     *
     * @return Modified string
     */
    public static String escapeSingleQuote(String str) {
        return replaceWith(str, "'", "''");
    }

    //venkat
    /**
     * Deleting ''(single quotes) in the given string. This is done so that the
     * SQL for Oracle text data can be entered that contains no Single Quote.
     *
     * @param str Given string
     *
     * @return Modified string
     */
    public static String deleteSingleQuote(String str) {
        return replaceWith(str, "'", "");
    }
    //venkat

    /**
     * Replace the given string part with given replace string.
     *
     * @param str Given string (eg "c:\rcn\a.txt;d:\db\mail.elm")
     * @param replace Separator (eg ";")
     * @param with Given string (eg "<BR>") with which to replace the 'replace'
     * string.
     *
     * @return eg "c:\rcn\a.txt<BR>d:\db\mail.elm"
     */
    public static String replaceWith(String str,
            String replace,
            String with) {
        // Return str if any string given is null OR the
        // replace string is empty one
        if (str == null || replace == null || with == null
                || replace.equals("")) {
            return str;
        }

        // Replace the given 'replace substring' from the given
        // string with the 'with substring'
        StringBuffer sb = new StringBuffer();
        int len = replace.length();
        int toIndex = 0;
        int fromIndex = 0;
        while (fromIndex != -1) {
            toIndex = str.indexOf(replace, fromIndex);
            if (toIndex != -1) {
                // Get the substring
                sb.append(str.substring(fromIndex, toIndex));
                sb.append(with);
                fromIndex = toIndex + len;
            } else {
                // Get the remainder of the string, if any
                if (fromIndex <= str.length()) {
                    sb.append(str.substring(fromIndex, str.length()));
                }
                fromIndex = toIndex;
            }
        }
        return sb.toString();
    }

    /**
     * Simply removes the <...> tags. Period.
     *
     * @param html HTML text
     */
    public static String htmlToText(String html) {
        // Locals
        char ch = '\u0000';
        int idx = 0;
        int len = html.length();
        StringBuffer sb = new StringBuffer();

        // Conversion
        for (int i = 0; i < len; i++) {
            ch = html.charAt(i);

            // If this char is '<' then find the first '>' char
            // after it. Do not write the text between '<' and '>'
            // (both inclusive). Write the text found otherwise
            // to the buffer
            if (ch != '<') {
                sb.append(ch);
            } else {
                idx = html.indexOf('>', i + 1);
                if (idx != -1) {
                    i = idx;
                } else {
                    sb.append(html.substring(i, len));
                    i = len;
                }
            }
        }

        // Return
        return sb.toString();
    }
}

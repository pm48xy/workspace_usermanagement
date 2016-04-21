package nic.java.util;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : CommonUtils
// PURPOSE          : Provides number of utility static methods and constants 
// NOTES            : None
// LAST MODIFIED    :
//  20040101 JIS025 Corrected Javadoc
//  20031224 JIS023 Added replaceNullWithEmptyString()
//  20030919 GUM019 Package re-structuring
//  20030903 GES012 Corrected javadoc
//  20030816 SIM009 Renaming lpad(str) to formatNumberPart(str)
//  20030811 AKS013 Auditing - TCC : Possible Errors. Added isZero().
//  20030805 AKS012 Auditing - TCC : Performance Category. Added 
//                  MAX_CHARS_VEHNO_SERIES_PART, MAX_CHARS_VEHNO_NUMBER_PART
//  20030806 ASF005 Added lpad()
//  20030702 JIS013 Removed zip methods, changed isBlank method to isNullOrBlank,
//                  Moved getSpace() from UtilityProcedures
//  20030602 AKS003 Added beep()
//  20030526 AKT003 Added convertMemory(), roundOff(), add comments for NEW_LINE
//  20030515 JIS009 Added constants PATH_SEPARATOR, FILE_SEPARATOR
//  20030513 GUM002 Added DB_SYSTEM_*
//  20030410 AKS001 Documentaion
//  20030310 RCN001 Created
//  20030416 JIS002 Modified - added method isBlank(String)			 
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.util.*;
import java.io.*;
import java.sql.*;
import java.lang.reflect.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.JspWriter;
import java.util.zip.*;
import java.text.*;

/**
 * Provides number of utility static methods and constants.
 *
 * @author RCN
 */
abstract public class CommonUtils {

    /**
     * New Line.
     *
     * Verbatim from "Java in a Nutshell", 3rd edition p. 192:
     *
     * "Line Separators"
     *
     * Different systems use different characters or sequences of characters as
     * line separators. Do not hardcode "\n", "\r" or "\r\n" as the line
     * separator in your program. Instead, use the println() method of
     * PrintStream or PrintWriter, which automatically terminates a line with
     * the line separator appropriate for the platform, or use the value of the
     * line.separator system property."
     *
     * \n denotes a particular _character_, namely the newline character. For
     * historical reasons, going all the way back to teletypers as far as I can
     * recall, a change of line was defined to be a carriage return character
     * (\r) which sent the carriage (typing head) back to the left edge of the
     * page, followed by a newline character which made the printer or teletyper
     * advance the paper one line.
     *
     * When various operating systems appeared on the scene, some kept the
     * cumbersome \r\n two-character combination to mark the end of a line in a
     * file, whereas others used only one of them.
     *
     * The reasons why tutorials generally don't mention line.separator is, I
     * believe, two-fold. For one thing, there's usually no difference between
     * \n and \n\r when showing output on the screen - so "\n" will generally do
     * the right thing. (But remember, just because _you_ mean for a println()
     * to go to the screen, it might later be re-directed to go to a file or
     * even a teletyper). Secondly, in a tutorial you don't want to bog the
     * reader down in too many details. Better teach him how to use print and
     * println now, and tell him how to write portable programs later.
     */
    public static final String NEW_LINE = System.getProperty("line.separator");

    /**
     * Path Separator
     */
    public static final String PATH_SEPARATOR = System.getProperty("path.separator");

    /**
     * File Separator
     */
    //public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String FILE_SEPARATOR = "/";

    /*
     * Database Systems
     */
    /**
     * DB System - DB2
     */
    public static final String DB_SYSTEM_DB2 = "DB2";
    /**
     * DB System - ORACLE
     */
    public static final String DB_SYSTEM_ORACLE = "ORACLE";
    /**
     * DB System - MS-SQL
     */
    public static final String DB_SYSTEM_MSSQL = "MSSQL";

    /**
     * DB System - MS-SQL
     */
    public static final String DB_SYSTEM_POSTGRES = "POSTGRES";

    /**
     * Maximum number of chars in vehicle number - series part
     */
    public static final int MAX_CHARS_VEHNO_SERIES_PART = 6;

    /**
     * Maximum number of chars in vehicle number - number part
     */
    public static final int MAX_CHARS_VEHNO_NUMBER_PART = 4;

    /**
     * Wrapper parseInt method for Integer's parseInt so that we can throw our
     * customized exception.
     *
     * @param val String format number like "123"
     * @return Integer value like 123
     * @throws UtilsException
     */
    public static int parseInt(String val) throws UtilsException {
        int ival = 0;

        // Parse
        try {
            ival = Integer.parseInt(val);
        } catch (NumberFormatException nfe) {
            throw new UtilsException("The value \"" + val + "\" is not a number!");
        }

        // Return
        return ival;
    }

    /**
     * Illegal Char list that we are not allowing to be entered
     */
    public static final char[] ILLEGAL_CHARS = {'\''}; //-- All chars are allowed now

    /**
     * Method to get the Illegal Char Error Message.
     *
     * @return Error message generated using ILLEGAL_CHARS list.
     */
    public static final String illegalCharErrMsg() {
        if (ILLEGAL_CHARS == null) {
            return "";
        }

        StringBuffer msg = new StringBuffer("Please do not use char &nbsp;");
        for (int i = 0; i < ILLEGAL_CHARS.length; i++) {
            msg.append(ILLEGAL_CHARS[i] + " ");
        }
        return msg.toString();
    }

    /**
     * Method to check the given Object for all of its String type data if it
     * contains any of the ILLEGAL_CHARS chars. This method uses Reflection
     * Technique to do so.
     *
     * @param o Any given java object.
     *
     * @return True if object contain illegal char in its String data else
     * false.
     *
     * @throws UtilsException
     *
     * @throws IllegalAccessException
     */
    public static boolean hasIllegalStringData(Object o)
            throws UtilsException, IllegalAccessException {

        // Return if the ILLEGAL_CHARS is empty (null)
        if (ILLEGAL_CHARS == null) {
            return false;
        }

        // Get the class of the object
        Class c = o.getClass();

        // Get all the field of this class
        Field[] allFields = c.getDeclaredFields();

        // Allow the access for all the fields
        try {
            AccessibleObject.setAccessible(allFields, true);
        } catch (SecurityException e) {
            throw new UtilsException("SecurityException: " + e.getMessage());
        }

        // For each field get its type and if it is of java.lang.String
        // then check its value for the ILLEGAL_CHARS chars
        String fieldName = null;
        String fieldType = null;
        String fieldValue = null;
        //x// System.out.println("allFields.length = " +allFields.length);
        // Boolean Field value
        Boolean bfv = null;

        for (int i = 0; i < allFields.length; i++) {
            // Get the field name
            fieldName = allFields[i].getName();

            // Get the class name (eg "java.lang.String")
            fieldType = allFields[i].getType().getName();
            //x// System.out.println("fieldType = " +fieldType);

            // If the class name is "java.lang.String" then get the value of it
            // and check for illegal chars.
            if (fieldType.equals("java.lang.String")) {
                fieldValue = null;

                // Get the value
                try {
                    fieldValue = (String) allFields[i].get(o);
                } catch (SecurityException e) {
                    throw new UtilsException("SecurityException: " + e.getMessage());
                } catch (IllegalAccessException e) {
                    throw new UtilsException("IllegalAccessException: " + e.getMessage());
                }

                // DEBUG
                Debug.Sop(Debug.PRI
                        + "Name:" + fieldName
                        + "\t Type:" + fieldType
                        + "\t value:" + fieldValue);

                // Check if this String value contains illegal
                // char. If yes return true right now.
                if (hasIllegalChar(fieldValue)) {
                    //TODO_RCN -- Correct it.....
                    // return true;
                }
            } else if (fieldType.equals("boolean")) {
                bfv = (Boolean) allFields[i].get(o);
                Debug.Sop(Debug.PRI
                        + "Name:" + fieldName
                        + "\t Type:" + fieldType
                        + "\t value:" + bfv);
            } else if (fieldType.equals("org.w3c.dom.Element")) {
                hasIllegalStringData(allFields[i]);
            }
        } // for loop

        // No illegal char is found. So return false.
        return false;
    }

    /**
     * Method to check the given String if it contains any of the ILLEGAL_CHARS
     * chars.
     *
     * @param s String to check.
     *
     * @return True if String contain illegal char else false.
     */
    public static boolean hasIllegalChar(String s) {
        // Return false if the String is null or the ILLEGAL_CHARS is null
        if (s == null || ILLEGAL_CHARS == null) {
            return false;
        }

        // Get the char array from the String object
        char[] charArray = s.toCharArray();

        // Check each char of the string with the each
        // illegal char and return true as soon as we get
        // a illegal char match
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < ILLEGAL_CHARS.length; j++) {
                if (ILLEGAL_CHARS[j] == charArray[i]) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Converts all spaces in a URL to %20. eg
     * "http://host/hello.html?what=Hello World" to "%20" for Netscape. IE does
     * it autometically. But anyway let us convert it for both.
     *
     * @param w Given string (eg "Hello World")
     *
     * @return "Hello%20World"
     *
     * @throws UnsupportedEncodingException
     */
    public static String escape(String w) throws UnsupportedEncodingException {
        return java.net.URLEncoder.encode(w, "UTF-8");
    }

    /**
     * This is a wrapper method for JavaScript unescape() method, and
     * additionally the content fed to the unescape method is first encoded
     * using the java.net.URLEncoder.encode() method which converts Javs String
     * into a MIME format called "x-www-form-urlencoded" format.
     *
     * To convert a String, each character is examined in turn:
     *
     * 1. The ASCII characters 'a' through 'z', 'A' through 'Z', '0' through
     * '9', and ".", "-", "*", "_" remain the same. 2. The space character ' '
     * is converted into a plus sign '+'. 3. All other characters are converted
     * into the 3-character string "%xy", where xy is the two-digit hexadecimal
     * representation of the lower 8-bits of the character.
     *
     * @param form HTML form name (eg document.BugReport).
     * @param formFieldName HTML form filed name (eg TITLE).
     * @param javaStr The java string to be encoded.
     *
     * @return A JavaScript statement to set the value of a textfield/textarea.
     *
     * @throws UnsupportedEncodingException
     */
    public static String unescape(String form,
            String formFieldName,
            String javaStr)
            throws UnsupportedEncodingException {

        // Check input
        if (javaStr == null) {
            javaStr = "";
        }

        // Encode the given string object
        String encodedJavaStr = java.net.URLEncoder.encode(javaStr, "UTF-8");

        // Make the JavaScript statement for setting the value.
        String w = form + "." + formFieldName + ".value=unescape('" + encodedJavaStr + "'.split('+').join(' '));";

        // Return
        return w;
    }

    /**
     * This is a wrapper method for JavaScript unescape() method used to convert
     * a string encoded using JavaScript escape(str) into a regular string in
     * Java Program.
     *
     * A method java.net.URLDecoder.decode(str) is used for converting from a
     * MIME format called "x-www-form-urlencoded" to a String
     *
     * To convert to a String, each character is examined in turn:
     *
     * The ASCII characters 'a' through 'z', 'A' through 'Z', and '0' through
     * '9' remain the same. The plus sign '+'is converted into a space character
     * ' '. The remaining characters are represented by 3-character strings
     * which begin with the percent sign, "%xy", where xy is the two-digit
     * hexadecimal representation of the lower 8-bits of the character.
     *
     * @param jsStr The encoded string using JavaScript escape() method
     *
     * @return The java String
     *
     * @throws Exception
     */
    public static String unescape(String jsStr) throws Exception {
        // Decode the given javascript coded string
        String decodedStr = java.net.URLDecoder.decode(jsStr, "UTF-8");

        // Return
        return decodedStr;
    }

    /**
     * Checks a given String for '\' char and replaces '\' with '\\' Also
     * replaces the \r\n to \\r\\n .
     *
     * @param str String in which the escape to happen.
     *
     * @return String with escape char embedded if needed.
     */
    public static String escapeSlash(String str) {
        // Get the String in array of chars
        char[] charArray = str.toCharArray();

        // Make a String Buffer object
        StringBuffer sb = new StringBuffer();

        // Append the chars in the StringBuffer object and if
        // the char is '\' then append another '\' char
        for (int i = 0; i < charArray.length; i++) {
            // Replace \ with \\
            if (charArray[i] == '\\') {
                sb.append('\\');
            }

            // Taking Care of the NEW LINE Character.
            // On Windows the new line made of two chars \r\n
            // (System.getProperty("line.separator")). If we
            // do not do following, then while the string is
            // getting loaded in the TextArea via JavaScript
            // it will give "Unterminated string constant"
            // error. So replace \r with \\r and \n with \\n
            // ie \r\n to \\r\\n
            if (charArray[i] == '\r') {
                sb.append('\\');
                sb.append('r');
                continue;
            }
            if (charArray[i] == '\n') {
                sb.append('\\');
                sb.append('n');
                continue;
            }

            // Append the char
            sb.append(charArray[i]);
        }

        // Convert the StringBuffer to String
        return sb.toString();
    }

    /**
     * Checks a given String for " char and replaces it with \". Used for
     * JavaScript String parameters.
     *
     * @param str String in which the escape to happen.
     *
     * @return String with escape char embedded.
     */
    public static String escapeDoubleQuote(String str) {
        // Get the String in array of chars
        char[] charArray = str.toCharArray();

        // Make a String Buffer object
        StringBuffer sb = new StringBuffer();

        // Append the chars in the StringBuffer object and if
        // the char is " then replace it with \"
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] != '"') {
                sb.append(charArray[i]);
            } else {
                sb.append("\\\"");
            }
        }

        // Convert the StringBuffer to String
        return sb.toString();
    }

    /**
     * HTML pages No-Cache.
     *
     * @param response HttpServletResponse object.
     *
     * @return HTML <META> tag string for no cache pragma.
     */
    public static String noCache(HttpServletResponse response) {
        // Setting the HttpServletResponse Header for no-cache
        response.setHeader("pragma", "no-cache");

        // Also setting the same via HTML META Tag. It is not
        // gauranteed that the no-cache will happen despite the
        // META tag given
        return "<META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=iso-8859-1\">"
                + "\n<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\">";
    }

    /**
     * Gets the logged in user id.
     *
     * @param session HttpServletRequest request object.
     *
     * @return Logged in user id.
     */
    public static String getUser(HttpSession session) {
        String user = (String) session.getAttribute("userid");

        if (user == null) {
            user = "No user logged in";
        }

        return "(" + user + ")";
    }

    /**
     * Untokenize the given string for given "Separator Chars" and return the
     * individual tokens in a String array.
     *
     * @param str Given string to be untokenize (eg "Ramesh,C,Nougain")
     * @param separatorChars Separator Chars to be used for untokenization (eg
     * ",")
     *
     * @return Array of string (eg {"Ramesh", "C", "Nougain"})
     */
    public static String[] untokenize(String str,
            String separatorChars) {

        // Check input
        if (str == null || str.trim().equals("")) {
            return null;
        }

        // Un-Tokenize (or Tokenize???)
        StringTokenizer st = new StringTokenizer(str, separatorChars);
        int count = st.countTokens();
        String[] sa = new String[count];
        for (int i = 0; i < count; i++) {
            sa[i] = st.nextToken();
        }
        return sa;
    }

    /**
     * Untokenize the given string for given "Separator" String and return the
     * individual Strings in a String array.
     *
     * @param str Given string to be untokenize (eg "Ramesh,C,Nougain")
     * @param separator Separator String to be used for untokenization (eg
     * "Nou")
     *
     * @return Array of string (eg {"Ramesh,C,", "gain"})
     */
    public static String[] breakString(String str,
            String separator) {

        // Check input
        if (str == null || str.trim().equals("")) {
            return null;
        }

        // Break the String
        ArrayList al = new ArrayList();
        int len = separator.length();
        int fromIndex = 0;
        int toIndex = 0;
        while (fromIndex != -1) {
            toIndex = str.indexOf(separator, fromIndex);
            if (toIndex != -1) {
                // Get the substring
                al.add(str.substring(fromIndex, toIndex));
                fromIndex = toIndex + len;
            } else {
                // Get the remainder of the string, if any
                if (fromIndex <= str.length()) {
                    al.add(str.substring(fromIndex, str.length()));
                }
                fromIndex = toIndex;
            }
        }

        // String array
        int size = al.size();
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = (String) al.get(i);
        }

        // Return
        return strings;
    }

    /**
     * Random object
     */
    public static final Random RANDOM = new Random();

    /**
     * Returns positive long random number
     */
    public static long getRandomNumber() {
        long randn = RANDOM.nextLong();
        return Math.abs(randn);
    }

    /**
     * Converts a data array into an XML Document.
     *
     * @param data Data given in 2D array format (Columns 4 in #)
     *
     * @return HTML TABLE format string.
     *
     * @throws UtilsException
     */
    public static String toXML(String[][] data)
            throws UtilsException {

        // Make the XML document (Ignoring the first index from data
        // as contains the table title).
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < data.length; i++) {
            sb.append("    <" + data[i][1] + ">\n"
                    + "        " + data[i][2] + "\n"
                    + "    </" + data[i][1] + ">\n");
        }

        // Retuen
        return sb.toString();
    }

    /**
     * Get the classpath value.
     *
     * @return Classpath value
     */
    public static String getClasspath() {
        return System.getProperty("java.class.path");
    }

    /**
     * Get the environment properties.
     *
     * @return environment properties in a string format
     */
    public static String getProperties() {
        StringBuffer prps = new StringBuffer();
        Properties props = System.getProperties();
        Enumeration penum = props.propertyNames();
        String pname = null;
        String pdata = null;
        while (penum.hasMoreElements()) {
            pname = (String) penum.nextElement();
            pdata = props.getProperty(pname);
            prps.append(pname + " : " + pdata + "\n");
        }
        return prps.toString();
    }

    /**
     * Suffix/Prefix given path to the classpath.
     *
     * @param path Given path to add
     * @param prepend True if Prefix else false
     */
    public static void addToClasspath(String path, boolean prepend) {
        String cpath = System.getProperty("java.class.path");
        cpath = prepend ? path + PATH_SEPARATOR + cpath
                : cpath + PATH_SEPARATOR + path;
        Properties props = System.getProperties();
        props.put("java.class.path", cpath);
    }

    /**
     * Check classpath if it has the classes/jars needed.
     *
     * @return "" if all the jars are there, else the list of missing jars
     */
    public static String checkCCSClasspath() {
        // List of jars to check for
        //String[] jars = {"mail.jar", "activation.jar"};
        String[] jars = {};

        // Return
        return checkClasspath(jars);
    }

    /**
     * Check classpath if it has the classes/jars needed.
     *
     * @param jars List of .jar files to check for
     *
     * @return "" if all the jars are there, else the list of missing jars
     */
    public static String checkClasspath(String[] jars) {
        // Return value
        StringBuffer missing = new StringBuffer();

        // Get the classpath individual path list
        String cpath = getClasspath();
        StringTokenizer st = new StringTokenizer(cpath, PATH_SEPARATOR);
        // TECH_NOTE : Do NOT use st.countTokens() directly in the
        //             for loop instead of count. st.countTokens()
        //             decreases with st.nextToken() calls.
        int count = st.countTokens();
        String[] tokens = new String[count];
        for (int i = 0; i < count; i++) {
            tokens[i] = st.nextToken();
        }

        // Scan the classpath's path list
        TOP:
        for (int i = 0; i < jars.length; i++) {
            for (int j = 0; j < tokens.length; j++) {
                if (tokens[j].indexOf(jars[i]) != -1) {
                    // jar file is found. Now check if it really is present.
                    if (new File(tokens[j]).exists()) {
                        continue TOP;
                    }
                }
            }
            missing.append(jars[i] + " ");
        }

        // Return
        return missing.toString();
    }

    /**
     * Get the list of the versions and other related informations about the
     * packages loaded in the JVM at the time of call in HTML Table format.
     *
     * @return Details about the loaded packages in the JVM
     */
    public static String listPackageDetails_InHTMLTable() {
        Package[] packs = Package.getPackages();

        StringBuffer html = new StringBuffer();
        html.append("<TABLE WIDTH=100% BGCOLOR=FFFFFF BORDER=0 CELLSPACING=1 CELLPADDING=0>");
        html.append("<TR BGCOLOR=DDDDDD>");
        html.append("<TD COLSPAN=9 ALIGN=CENTER><B>Note : The package details"
                + " will come below only if it is loaded by the"
                + " classloader before the call to this page and"
                + " the .jar has the manifest file appropriately"
                + " filled.</B></TD>");
        html.append("</TR>");

        html.append("<TR BGCOLOR=DDDDDD>");
        html.append("<TD VALIGN=TOP><FONT SIZE=2><B>Name</B></TD>");
        html.append("<TD VALIGN=TOP><FONT SIZE=2><B>Implementation Title</FONT></B></TD>");
        html.append("<TD VALIGN=TOP><FONT SIZE=2><B>Implementation Vendor</FONT></B></TD>");
        html.append("<TD VALIGN=TOP><FONT SIZE=2><B>Implementation Version</FONT></B></TD>");
        html.append("<TD VALIGN=TOP><FONT SIZE=2><B>Specification Title</FONT></B></TD>");
        html.append("<TD VALIGN=TOP><FONT SIZE=2><B>Specification Vendor</FONT></B></TD>");
        html.append("<TD VALIGN=TOP><FONT SIZE=2><B>Specification Version</FONT></B></TD>");
        html.append("<TD VALIGN=TOP><FONT SIZE=2><B>Hashcode</FONT></B></TD>");
        html.append("<TD VALIGN=TOP><FONT SIZE=2><B>Is Sealed</FONT></B></TD>");
        html.append("</TR>");

        for (int i = 0; i < packs.length; i++) {
            html.append("<TR BGCOLOR=EEEEEE>");
            html.append("<TD VALIGN=TOP><FONT COLOR=Blue SIZE=2><B>" + packs[i].getName() + "</B></FONT></TD>");
            html.append("<TD VALIGN=TOP><FONT SIZE=2>" + packs[i].getImplementationTitle() + "</FONT></TD>");
            html.append("<TD VALIGN=TOP><FONT SIZE=2>" + packs[i].getImplementationVendor() + "</FONT></TD>");
            html.append("<TD VALIGN=TOP><FONT SIZE=2>" + packs[i].getImplementationVersion() + "</FONT></TD>");
            html.append("<TD VALIGN=TOP><FONT SIZE=2>" + packs[i].getSpecificationTitle() + "</FONT></TD>");
            html.append("<TD VALIGN=TOP><FONT SIZE=2>" + packs[i].getSpecificationVendor() + "</FONT></TD>");
            html.append("<TD VALIGN=TOP><FONT SIZE=2>" + packs[i].getSpecificationVersion() + "</FONT></TD>");
            html.append("<TD VALIGN=TOP><FONT SIZE=2>" + packs[i].hashCode() + "</FONT></TD>");
            html.append("<TD VALIGN=TOP><FONT SIZE=2>" + packs[i].isSealed() + "</FONT></TD>");
            html.append("</TR>");
        }

        html.append("</TABLE>");
        return html.toString();
    }

    /**
     * Get the list of the versions and other related informations about the
     * packages loaded in the JVM at the time of call.
     *
     * @return Details about the loaded packages in the JVM
     */
    public static String listPackageDetails() {
        StringBuffer details = new StringBuffer();
        Package[] packs = Package.getPackages();
        for (int i = 0; i < packs.length; i++) {
            details.append(listPackageDetail(packs[i]) + "\n");
        }
        return details.toString();
    }

    /**
     * Get the the version and other related informations about the given
     * package, provided that it is loaded by the classloader at the time of
     * call.
     *
     * @return Details about the package given or the error message in case the
     * package is not loaded
     */
    public static String listPackageDetail(String pkg) {
        Package pack = Package.getPackage(pkg);
        return (pack != null) ? listPackageDetail(pack)
                : "ERROR : No package found : " + pkg;
    }

    /**
     * Get the the version and other related informations about the given
     * package, provided that it is loaded by the classloader at the time of
     * call.
     *
     * @return Details about the package given or the error message in case the
     * package is not loaded
     */
    public static String listPackageDetail(Package pack) {
        String details = ""
                + "Name                   : " + pack.getName() + "\n"
                + "Implementation Title   : " + pack.getImplementationTitle() + "\n"
                + "Implementation Vendor  : " + pack.getImplementationVendor() + "\n"
                + "Implementation Version : " + pack.getImplementationVersion() + "\n"
                + "Specification Title    : " + pack.getSpecificationTitle() + "\n"
                + "Specification Vendor   : " + pack.getSpecificationVendor() + "\n"
                + "Specification Version  : " + pack.getSpecificationVersion() + "\n"
                + "Hashcode               : " + pack.hashCode() + "\n"
                + "Is Sealed              : " + pack.isSealed() + "\n";
        return details;
    }

    /**
     * Memory Status in JVM. This returns the memory in Bytes.
     */
    public static long[] memoryStatus() {
        // Call Garbage Collector
        System.gc();

        // Get the amount of free memory and total memory in the JVM.
        Runtime runtime = Runtime.getRuntime();
        long freemem = runtime.freeMemory();
        long totalmem = runtime.totalMemory();
        /*
         runtime.traceInstructions(true);
         runtime.traceMethodCalls(true);
         */
        return new long[]{freemem, totalmem};
    }

    /**
     * Get the stack trace of an exception as string
     */
    public static String getStackTrace(Throwable e) {
        StringWriter swriter = new StringWriter();

        PrintWriter pwriter = new PrintWriter(swriter);
        e.printStackTrace(pwriter);
        pwriter.flush();

        return swriter.toString();
    }

    /**
     * Checks whether the string is blank or 'null'.
     *
     * @param strCheck string to be verified
     */
    public static boolean isNullOrBlank(String strCheck) {
        if ((strCheck == null) || (strCheck.trim().length() <= 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Round off the number to given decimal digit
     */
    public static String roundOff(double d, int n) {
        NumberFormat nfmt = NumberFormat.getInstance();
        nfmt.setMaximumFractionDigits(n);
        nfmt.setMinimumFractionDigits(n);
        nfmt.setGroupingUsed(false);
        return nfmt.format(d);
    }

    /**
     * Round off the number to given decimal digit
     */
    public static String roundOff(float f, int n) {
        NumberFormat nfmt = NumberFormat.getInstance();
        nfmt.setMaximumFractionDigits(n);
        nfmt.setMinimumFractionDigits(n);
        nfmt.setGroupingUsed(false);
        return nfmt.format(new Float(f).doubleValue()); // Converting float to double
    }

    /**
     * Convert the memory space from bytes to Bytes, KB, MB, GB.
     *
     * @param bytes memory space in terms of bytes
     */
    public static String convertMemory(long bytes) {
        // Find how many multiples of 1024 are there
        int count = 0;
        long l_bytes = bytes;
        while (l_bytes > 1024) {
            count++;
            l_bytes = l_bytes / 1024;
        }

        // Count maximum upto GB
        if (count > 3) {
            count = 3;
        }

        // Depending upon the count value it will give the value
        String block = "";
        switch (count) {
            case 0:
                block = "Bytes";
                break;
            case 1:
                block = "KB";
                break;
            case 2:
                block = "MB";
                break;
            case 3:
            default:
                block = "GB";
                break;
        }

        double num = new Long(bytes).doubleValue();
        String size = roundOff(num / Math.pow(1024, count), 2) + " " + block;

        return size;
    }

    /**
     * System beep
     */
    public void beep() {
        java.awt.Toolkit.getDefaultToolkit().beep();
    }

    /**
     * Here get the string of spaces of particular length
     *
     * @param n Number of spaces needed
     *
     * @return String String containing spaces
     */
    public static String getSpace(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Method for right padding upto CommonUtils.MAX_CHARS_VEHNO_SERIES_PART
     * characters with SPACE. If series is sent null then it returns " ".
     *
     * @param str String to be formatted (e.g "NIDC")
     *
     * @return padded string (e.g "NIDC" to "NIDC ")
     */
    public static String formatSeriesPart(String str) {
        return rpad(str, ' ', CommonUtils.MAX_CHARS_VEHNO_SERIES_PART);
    }

    /**
     * Method for left padding upto CommonUtils.MAX_CHARS_VEHNO_NUMBER_PART
     * characters with zero. If num is sent null then it returns "0000".
     *
     * @param str String to be formatted (e.g "1")
     *
     * @return padded string (e.g "1" to "0001")
     */
    public static String formatNumberPart(String str) {
        return lpad(str, '0', CommonUtils.MAX_CHARS_VEHNO_NUMBER_PART);
    }

    /**
     * Method for left padding upto given number of characters with given
     * padding char. If num is sent null then it returns eg "0000" if the
     * padding char is '0' and num Of Chars is 4.
     *
     * @param str String to be formatted (e.g "1", "abc")
     * @param paddingChar Char with which the padding needs to be done (e.g '0',
     * 'g', '-')
     * @param numOfChars Number of chars that the final string should have after
     * padding, if done.
     *
     * @return padded string (e.g "1" to "0001")
     */
    public static String lpad(String str, char paddingChar, int numOfChars) {
        StringBuffer paddedStr = new StringBuffer();
        if (str == null) {
            for (int i = 0; i < numOfChars; i++) {
                paddedStr.append(paddingChar);
            }
        } else {
            int len = str.length();
            for (int i = 0; i < numOfChars - len; i++) {
                paddedStr.append(paddingChar);
            }
            paddedStr.append(str);
        }

        return paddedStr.toString();
    }

    /**
     * Method for right padding upto given number of characters with given
     * padding char. If num is sent null then it returns eg "0000" if the
     * padding char is '0' and num Of Chars is 4.
     *
     * @param str String to be formatted (e.g "1", "abc")
     * @param paddingChar Char with which the padding needs to be done (e.g '0',
     * 'g', '-')
     * @param numOfChars Number of chars that the final string should have after
     * padding, if done.
     *
     * @return padded string (e.g "1" to "1000")
     */
    public static String rpad(String str, char paddingChar, int numOfChars) {
        StringBuffer paddedStr = new StringBuffer();
        if (str == null) {
            for (int i = 0; i < numOfChars; i++) {
                paddedStr.insert(0, paddingChar);
            }
        } else {
            int len = str.length();
            for (int i = 0; i < numOfChars - len; i++) {
                paddedStr.insert(0, paddingChar);
            }
            paddedStr.insert(0, str);
        }

        return paddedStr.toString();
    }

    /**
     * Checks if the given value is ZERO (within our tolerance).
     *
     * @param value Given value to be checked for Zero
     */
    public static boolean isZero(double value) {
        // Following logic is taken from Together Control Center
        return (Math.abs(value - 0.0) < Double.MIN_VALUE * 2);
    }

    /**
     * Returns a formatted string for SELECT sql which checks for null column
     * value. And replaces it by empty string.
     *
     * @param value Name of the column to be replaced
     *
     * @return Returns Formatted string for SELECT sql
     */
    public static String replaceNullWithEmptyString(String value) {
        return ((value != null) ? value : " ");
    }

    /**
     * Main for testing
     *
     * @throws UtilsException
     */
    public static void main(String[] args) throws UtilsException {
        //checkCCSClasspath
        String missingJars = checkCCSClasspath();
        if (missingJars.equals("")) {
            System.out.println("No Missing jar");
        } else {
            System.out.println("Missing jars : " + missingJars);
        }

        //TestCases lpad
        System.out.println("lpad(\"1\") = " + formatNumberPart("1"));
        System.out.println("lpad(\"2a\") = " + formatNumberPart("2a"));
        System.out.println("lpad(\"2a 1\") = " + formatNumberPart("2a 1"));
        System.out.println("lpad(\"2a 211\") = " + formatNumberPart("2a 211"));

        System.out.println("lpad(\"1\", 'x', 9) = " + lpad("1", 'x', 9));
        System.out.println("lpad(\"2a\", '-', 3) = " + lpad("2a", '-', 3));
        System.out.println("lpad(\"2a 1\", '*', 9) = " + lpad("2a 1", '*', 9));
        System.out.println("lpad(\"2a 211\", '#', 7) = " + lpad("2a 211", '#', 7));
    }
}

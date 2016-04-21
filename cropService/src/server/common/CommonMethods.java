package server.common;
/////////////////////////////////////////////////////////////////////////////
// CLASS            : CommonMethods
// PURPOSE          : Class used for providing common methods
// NOTES            : None
// LAST MODIFIED    :
//
//////////////////////////////////////////////////////////////////////////////
// Copyright 2009 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
/////////////////////////////////////////////////////////////////////////////

/**
 * Importing standard java classes
 */
//on 17may 2013
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import server.config.ApplicationConfig;
//end
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.faces.model.SelectItem;
import javax.sql.RowSet;
import server.config.ApplicationConfig;
import server.db.DBUtils;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * This class is used to provide common methods
 *
 * @author NIC
 */
public class CommonMethods {

    private static final DateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Method to separate the string with particular separator
     *
     * @param ruleStr
     * @param sep
     * @return
     */
    public static ArrayList fillMasterTab(String... args) {
        ArrayList list = new ArrayList();
        String vm_sql = "";
        System.out.println("fillMasterTab called");
        try {
            if (args.length == 1) {
                vm_sql = "SELECT CODE,DESCR FROM " + args[0] + "";

            } else if (args.length == 2) {
                vm_sql = "SELECT CODE,DESCR FROM " + args[0] + " WHERE VH_TYPE='" + args[1] + "'";
            }
            System.out.println("vm_sql " + vm_sql);
            RowSet rs = DBUtils.fetchDetachedRowSet(vm_sql);
            list.add(new SelectItem("-1", "SELECT"));
            while (rs.next()) {
                list.add(new SelectItem(rs.getString("code"), rs.getString("descr")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static String getVhTypeDescr(String code) {
        String result = "";
        String vm_sql = "";
        System.out.println("getVhTypeDescr called");
        try {

            vm_sql = "SELECT CODE,DESCR FROM VM_RJ_VH_TYPES WHERE CODE='" + code + "'";

            System.out.println("vm_sql " + vm_sql);
            RowSet rs = DBUtils.fetchDetachedRowSet(vm_sql);

            while (rs.next()) {
                result = (String) rs.getString("DESCR");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    public static ArrayList<SelectItem> getCheckpostNameList(String code) {
        ArrayList<SelectItem> list = new ArrayList<SelectItem>();
        String vm_sql = "";
        System.out.println("getCheckpostNameList called");
        try {

            vm_sql = "SELECT CODE,CHECKPOST_NAME FROM VM_CHECKPOST_NAME WHERE DISTRICT_CD='" + code + "'";

            System.out.println("vm_sql " + vm_sql);
            RowSet rs = DBUtils.fetchDetachedRowSet(vm_sql);
            list.add(new SelectItem("-1", "SELECT"));
            while (rs.next()) {
                list.add(new SelectItem(rs.getString("CODE"), rs.getString("CHECKPOST_NAME")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static ArrayList getAllCheckpostANameList() {
        ArrayList list = new ArrayList();
        String vm_sql = "";
        System.out.println("getVhTypeDescr called");
        try {

            vm_sql = "SELECT CODE,CHECKPOST_NAME FROM VM_CHECKPOST_NAME";

            System.out.println("vm_sql " + vm_sql);
            RowSet rs = DBUtils.fetchDetachedRowSet(vm_sql);
            list.add(new SelectItem("-1", "SELECT"));
            while (rs.next()) {
                System.out.println("" + rs.getString("CHECKPOST_NAME"));
                list.add(new SelectItem(rs.getString("CHECKPOST_NAME"), rs.getString("CHECKPOST_NAME")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public String[] separateRuleNos(String ruleStr, char sep) {
        String[] rule = null;
        int i = 1;
        int index = 0;
        int noOfComma = findNoRules(ruleStr, String.valueOf(sep));
        if (noOfComma > 0) {
            rule = new String[noOfComma];
        }
        while (i > 0) {
            i = ruleStr.indexOf(sep);

            if (i > 0) {
                rule[index] = ruleStr.substring(0, i);
            } else {
                rule[index] = ruleStr;
            }
            ruleStr = ruleStr.substring(i + 1, ruleStr.length());
            index++;
        }
        return rule;
    }

    /**
     * This method count no of seperators in string
     *
     * @return no of token
     */
    public int findNoRules(String ruleStr, String sep) {
        int noOfRule = 0;
        int noOfComma = 0;
        String str = "";
        for (int p = 0; p < ruleStr.length(); p++) {
            str = ruleStr.charAt(p) + "";
            if (str.equalsIgnoreCase(sep)) {
                noOfComma++;
            }
        }
        noOfRule = noOfComma + 1;
        return noOfRule;
    }

    /**
     * Method to get the date pattern based on the database type
     *
     * @param strDateTime
     * @return
     */
    public static String getDate(String strDateTime) {
        int gDbType = ApplicationConfig.DB_TYPE;
        String rtnDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
        Date toDate = null;
        try {
            toDate = sdf.parse(strDateTime);
        } catch (ParseException pex) {
            System.out.println(pex.toString());
        }

        if (strDateTime != null && !strDateTime.equalsIgnoreCase("")) {

            switch (gDbType) {
                //SQL Server
                case 1:
                    rtnDate = sdf1.format(toDate);
                    break;
                //Oracle
                case 2:
                    rtnDate = "TO_DATE('" + sdf2.format(toDate) + "','DD-MON-YYYY')";
                    break;
                //DB2 Server
                case 3:
                    rtnDate = "DATE('" + sdf3.format(toDate) + "','dd.MM.yyyy')";
                    break;
                //PostgresSQL
                case 4:
                    rtnDate = "'" + sdf4.format(toDate) + "'";
                    break;
                default:
                    rtnDate = "NULL";
                    break;
            }

        }
        return rtnDate;
    }

    /**
     * Method to get date and time based on the database
     *
     * @param strDateTime
     * @return
     */
    public static String getDateTime(String strDateTime) {
        System.out.println("given date formate..." + strDateTime);
        int gDbType = ApplicationConfig.DB_TYPE;
        String rtnDate = "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
//        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH24:MI:SS");
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        Date toDate = null;
        try {
            toDate = sdf.parse(strDateTime);
        } catch (ParseException pex) {
            System.out.println(pex.toString());
        }

        if (strDateTime != null && !strDateTime.equalsIgnoreCase("")) {

            switch (gDbType) {
                //SQL Server
                case 1:
                    rtnDate = "'" + sdf1.format(toDate) + "'";
                    System.out.println("test 1");
                    break;
                //Oracle
                case 2:
                    rtnDate = "TO_DATE('" + sdf2.format(toDate) + "','DD-MON-YYYY HH24:MI:SS')";
                    System.out.println("test 2");
                    break;
                //DB2 Server
                case 3:
                    rtnDate = "DATE('" + sdf3.format(toDate) + "','dd.MM.yyyy hh:mm:ss')";
                    System.out.println("test 3");
                    break;
                //PostgresSQL
                case 4:
                    rtnDate = "'" + sdf4.format(toDate) + "'";
                    System.out.println("test 4");
                    break;
                default:
                    rtnDate = "NULL";
                    break;
            }

        }
        return rtnDate;
    }

    /**
     * Method to convert date into 'dd-MMM-yyyy' format
     *
     * @param dt
     * @return
     */
    public static String convertToStandardDateFormat(Date dt) {
        String rtnDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        if (dt != null) {
            rtnDate = sdf.format(dt);
        }
        return rtnDate;
    }

    /**
     * Method to convert date into 'dd-MMM-yyyy hh:mm:ss' format
     *
     * @param dt
     * @return
     */
    public static String convertToStandardDateTimeFormat(Date dt) {
        String rtnDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

        if (dt != null) {
            rtnDate = sdf.format(dt);
        }
        return rtnDate;
    }

    /**
     * Method to return the current date in String format
     *
     * @return
     */
    public static String getCurrentDateAsString() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date toDate = cal.getTime();

        return getDate(convertToStandardDateFormat(toDate));
    }

    /**
     * Method to return current date and time in String format
     *
     * @return
     */
    public static String getCurrentDateTimeAsString() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date toDate = cal.getTime();
        return getDateTime(convertToStandardDateTimeFormat(toDate));
    }

    /**
     * Method to get the current date
     *
     * @return
     */
    public static Date getCurrentDate() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        return cal.getTime();
    }

    public static String getIntegerFormat(String str) {
        String rtnString = str;
        if (str != null && !str.equalsIgnoreCase("")) {
            if (str.indexOf(".") > 0) {
                rtnString = str.substring(0, str.indexOf("."));
            }
        }
        return rtnString;
    }

    public static java.util.Date toUtilDate(java.sql.Date timestamp) {

        long milliseconds = timestamp.getTime();
        return new java.util.Date(milliseconds);
    }

    public static java.util.Date sqlDateToutilDate(java.sql.Date sDate) throws ParseException {
        SimpleDateFormat utilDateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        return (java.util.Date) utilDateFormatter.parse(utilDateFormatter.format(sDate));
    }

    /**
     * Method to return standard date time in string format
     *
     * @return
     */
    public static String getStandardCurrentDateTimeAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        return sdf.format(getCurrentDate());

    }

    /**
     * Method to get date difference between from and to dates
     *
     * @param frDt
     * @param toDt
     * @return
     */
    public static long getDateDiffernceInDays(Date frDt, Date toDt) {
        long frmTime = frDt.getTime();
        long toTime = toDt.getTime();
        return ((toTime - frmTime) / (24 * 60 * 60 * 1000));

    }

//    public static int getLastDayOfMonth(Date date)
//    {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//    }
    public static String getLastDay(Date d) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date dddd = calendar.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        return sdf1.format(dddd);
    }

    public static String getFirstDay(Date d) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date dddd = calendar.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        return sdf1.format(dddd);
    }

    public static String md5_hex(String passwd) {
        String rtnMd5 = "";
        if (passwd != null && !passwd.equalsIgnoreCase("")) {
            try {
                rtnMd5 = MD5(passwd);
            } catch (Exception ex) {
                System.out.println("Problem in md5 encryption....!!!!");
            }
        }
        return rtnMd5;
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    }

    /**
     * Method to replace the special characters with null
     *
     * @param str
     * @return
     */
    public static String convertSanitizedStr(String str) {
        return (str.replaceAll("[\'\"([%])<>?:=&#$*]", ""));
    }

    /**
     * Method to replace the special characters with null
     *
     * @param obj
     * @return
     */
    public static String convertSanitizedStr(Object obj) {
        String str = "";
        if (obj == null) {
            str = "";
        } else {
            str = obj.toString();
        }
        return (str.replaceAll("[\'\"([%])<>?:=&#$*]", ""));
    }

    /**
     * Method to convert sql date into standard date format
     *
     * @param date
     * @return
     */
    public static java.sql.Date getSQLDateFromStandardDateFormatddMMyyyy(String date) {
        java.sql.Date sqlDate = null;
        if (date != null && date.length() == 11) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                java.util.Date dt = sdf.parse(date);
                long time = dt.getTime();
                sqlDate = new java.sql.Date(time);
            } catch (ParseException pex) {
                pex.printStackTrace();
                System.out.println("Date Parse Exception:" + pex.toString());
            }
        }
        return sqlDate;
    }

    public static java.sql.Date getSQLDateFromStandardDateFormat(String date) {
        java.sql.Date sqlDate = null;
        if (date != null && date.length() == 11) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            try {
                java.util.Date dt = sdf.parse(date);
                long time = dt.getTime();
                sqlDate = new java.sql.Date(time);
            } catch (ParseException pex) {
                pex.printStackTrace();
                System.out.println("Date Parse Exception:" + pex.toString());
            }
        }
        return sqlDate;
    }

    /**
     * Method to convert sql date into standard date time format
     *
     * @param sqlDate
     * @return
     */
    public static String convertSQLDateToStandardDateTimeFormat(java.sql.Date sqlDate) {
        String datetime = "";
        if (sqlDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
            datetime = sdf.format(utilDate);
        }
        return datetime;
    }

    public static java.sql.Date convertStringToSQLDate(String date) {
        java.sql.Date dt = null;

        if (date != null && date.length() > 0) {
            java.util.Date utilDate = getStringToDate(date);
            if (utilDate != null) {
                long t = utilDate.getTime();
                dt = new java.sql.Date(t);
            }
        }
        return dt;
    }

    public static Date getStringToDate(String strDt) {
        //return variable
        Date dt = null;
        //Constructs a SimpleDateFormat using the given pattern
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            //Parses the given string to a date
            dt = sdf.parse(strDt);
        } catch (ParseException pex) {
            System.out.println("Date Parse Exception:" + pex.toString());
        }
        return dt;
    }

    public static java.sql.Date utilDateToSqlDate(java.util.Date uDate) throws ParseException {
        return java.sql.Date.valueOf(sqlDateFormatter.format(uDate));
    }

    public static java.sql.Date convertDateStringToPostgreSQLDate(String date) throws ParseException {
        java.sql.Date dt = null;

        if (date != null && date.length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            java.util.Date udate = sdf.parse(date);
            java.sql.Date sdate = utilDateToSqlDate(udate);

        }
        return dt;
    }

    public static java.sql.Date getCurrentSQLDate() {
        java.sql.Date sqlDate = null;
        java.util.Date dt = getCurrentDate();
        long time = dt.getTime();
        sqlDate = new java.sql.Date(time);
        return sqlDate;

    }

    public static java.util.Date convertStringToUtilDate(String strDate) throws ParseException {
        java.util.Date udate = null;
        if (strDate != null && strDate.length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            udate = sdf.parse(strDate);
        }
        return udate;
    }

    public static java.util.Date convertStringToDate(String strDate) throws ParseException {
        //return variable
        Date dt = null;
        //Constructs a SimpleDateFormat using the given pattern
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //Parses the given string to a date
            dt = sdf.parse(strDate);
        } catch (ParseException pex) {
            System.out.println("Date Parse Exception:" + pex.toString());
        }
        return dt;
    }

    public static String convertDateToString(java.util.Date date) {
        String strDate = "";
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            strDate = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    public static java.sql.Date convertStringToSqlDate(String strDate) {
        java.sql.Date sqlDate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utildate = sdf.parse(strDate);
            sqlDate = new java.sql.Date(utildate.getTime());
        } catch (ParseException pex) {
            System.out.println("Parse Exception:" + strDate + ":" + pex.getMessage());
        }

        return sqlDate;
    }

    /**
     * Method to get the date pattern based on the database type
     *
     * @param strDateTime
     * @return
     */
    public static String getDateGoods(String strDateTime) {
        int gDbType = ApplicationConfig.DB_TYPE;
        String rtnDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
        Date toDate = null;
        try {
            toDate = sdf.parse(strDateTime);
        } catch (ParseException pex) {
            System.out.println(pex.toString());
        }

        if (strDateTime != null && !strDateTime.equalsIgnoreCase("")) {

            switch (gDbType) {
                //SQL Server
                case 1:
                    rtnDate = sdf1.format(toDate);
                    break;
                //Oracle
                case 2:
                    rtnDate = "TO_DATE('" + sdf2.format(toDate) + "','DD-MON-YYYY')";
                    break;
                //DB2 Server
                case 3:
                    rtnDate = "DATE('" + sdf3.format(toDate) + "','dd.MM.yyyy')";
                    break;
                //PostgresSQL
                case 4:
                    rtnDate = "'" + sdf4.format(toDate) + "'";
                    break;
                default:
                    rtnDate = "NULL";
                    break;
            }
        }
        return rtnDate;
    }

    /**
     * Method to get the date pattern based on the database type
     *
     * @param strDateTime
     * @return
     */
    public static String getDisplayDate(String strDateTime) {
        String rtnDate = "";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MMM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        Date toDate = null;
        try {
            toDate = sdf1.parse(strDateTime);
            rtnDate = "'" + sdf2.format(toDate) + "'";
        } catch (ParseException pex) {
            System.out.println(pex.toString());
        }
        return rtnDate;
    }

    public static boolean isNCRVehicle(String stateCode) {
        boolean isNCR = false;
        if (stateCode.equalsIgnoreCase("DL") || stateCode.equalsIgnoreCase("HR") || stateCode.equalsIgnoreCase("RJ")) {
            isNCR = true;
        }
        return isNCR;
    }
    //ambrish added methods for enc/decr
    //New addition------------start

    public static String Encryption(String strVal) {
        StringBuffer encVal = new StringBuffer();
        char ch[] = strVal.toCharArray();
        for (char c : ch) {
            encVal.append(Integer.toHexString((byte) c));
        }
        return encVal.toString();
    }

    public static String Decryption(String strVal) {

        StringBuilder sb = new StringBuilder();
        try {

            for (int i = 0; i < strVal.length() - 1; i += 2) {
                String output = strVal.substring(i, (i + 2));
                int decimal = Integer.parseInt(output, 16);
                //Integer.pa
                sb.append((char) decimal);
            }

        } catch (NumberFormatException nfex) {
            // this.showMessage("Return string is not encrypted!!!!!!!");
            nfex.printStackTrace();
//            return null;
        }
        return sb.toString();

    }
    //end

    public static void main(String args[]) {
    }
}

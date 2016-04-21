package nic.java.util;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : DateUtils
// PURPOSE          : Provides number of utility static methods and constants 
//                    for Date
// NOTES            : None
// LAST MODIFIED    :
//  20031224 JIS023 Modified week day check
//  20031117 JIS022 Modified getDate1MinusDate2_Months() for calculating 
//                  date difference in months
//  20030919 GUM019 Package re-structuring
//  20030807 JIS014 Fixed VB0087
//  20030716 AKS009 Auditing - TCC : Critical Errors
//  20030715 GUM009 Removed getServerDate() from here added to Server.java
//                  as this is <<remote>>
//  20030715 AKS008 Auditing - Removed 'Superfluous Content'
//  20030702 JIS013 Added new methods
//  20030606 SIM001 Changed addToDate() variable date to tempDate
//  20030523 JIS012 Added new methods
//  20030602 AKS003 Added new methods (Contribution by JIS)
//  20030520 JIS010 Added parseDate(Date)
//  20030513 GUM002 Using dd-MM-yyyy instead dd/MM/yyyy
//  20030410 AKS001 Documentaion
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

/**
 * Provides number of utility static methods and constants for Date.
 *
 * @author RCN
 */
abstract public class DateUtils {

    /**
     * Milliseconds in one day
     */
    public static final long MILLISECONDS_IN_ONE_DAY = 24 * 60 * 60 * 1000;

    /**
     * Default Date format
     */
    public static final String DATE_FORMAT = "dd-MM-yyyy"; // 'MM' means months, 'mm' means Minutes

    /**
     * DAY
     */
    public static final int DAY = 1;

    /**
     * MONTH
     */
    public static final int MONTH = 2;

    /**
     * YEAR
     */
    public static final int YEAR = 3;

    /**
     * Get Current local Date.
     *
     * @return local date object.
     */
    public static java.util.Date getCurrentLocalDate() {
        return new java.util.Date();
    }

    /**
     * Convert a date to 'DD/MM/YYYY' String format.
     *
     * @param date Given date.
     *
     * @return Date in DD/MM/YYYY format.
     */
    public static String getDateInDDMMYYYY(java.util.Date date) {
        // Check input
        if (date == null) {
            return "";
        }

        // Set the date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Get the month, day and year
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        // Get the 'DD/MM/YYYY' format
        String strDate = FormatUtils.getInNDigitFormat(2, day) + "-"
                + FormatUtils.getInNDigitFormat(2, month) + "-"
                + FormatUtils.getInNDigitFormat(4, year);

        // Return
        return strDate;
    }

    /**
     * Convert a date's time to 'HH24:MI:SS' String format.
     *
     * @param date Given date.
     *
     * @return Time in HH24:MI:SS format.
     */
    public static String getTimeInHHMMSS(java.util.Date date) {
        // Set the date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Get the hours, minutes and seconds
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        // Get the 'HH24:MI:SS' format
        String strTime = FormatUtils.getInNDigitFormat(2, hours) + ":"
                + FormatUtils.getInNDigitFormat(2, minutes) + ":"
                + FormatUtils.getInNDigitFormat(2, seconds);

        // Return
        return strTime;
    }

    /**
     * Convert a date to 'DD/MM/YYYY HH24:MI:SS' String format with current
     * time.
     *
     * TECH_NOTE : java.sql.Date Vs java.sql.Date Vs java.sql.Timestamp
     * --------------------------------------------------------
     * ResultSet.getDate() returns java.sql.Date providing DD-MON-YYYY value
     * only ResultSet.getTime() returns java.sql.Time providing HH:MM:SS value
     * only ResultSet.getTimestamp() returns java.sql.Timestamp providing
     * yyyy-mm-dd hh:mm:ss.fffffffff value
     *
     * @param date Given date.
     *
     * @return Date in DD/MM/YYYY HH24:MI:SS format.
     */
    public static String getDateInDDMMYYYY_HHMMSS_NOW(java.util.Date date) {
        // Check input
        if (date == null) {
            return "";
        }

        // Set the date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Get the 'DD/MM/YYYY HH24:MI:SS' format
        String strDate = getDateInDDMMYYYY(date)
                + " "
                + getTimeInHHMMSS(DateUtils.getCurrentLocalDate());

        // Return
        return strDate;
    }

    /**
     * Convert a date's time to 'HH12:MI AM/PM' String format.
     *
     * @param date Given date.
     *
     * @return Time in HH12:MI AM/PM format.
     */
    public static String getTimeInHHMM_AMPM(java.util.Date date) {
        // Set the date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Get the hours, minutes and seconds
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);

        String am_pm = "";
        if (hours < 12) {
            am_pm = "AM";
        } else if (hours == 12) {
            am_pm = "PM";
        } else {
            hours -= 12;
            am_pm = "PM";
        }

        // Get the 'HH12:MI AM/PM' format
        String strTime = FormatUtils.getInNDigitFormat(2, hours) + ":"
                + FormatUtils.getInNDigitFormat(2, minutes) + " "
                + am_pm;

        // Return
        return strTime;
    }

    /**
     * Convert a date to 'DD/MM/YYYY HH24:MI:SS' String format.
     *
     * TECH_NOTE : java.sql.Date Vs java.sql.Date Vs java.sql.Timestamp
     * --------------------------------------------------------
     * ResultSet.getDate() returns java.sql.Date providing DD-MON-YYYY value
     * only ResultSet.getTime() returns java.sql.Time providing HH:MM:SS value
     * only ResultSet.getTimestamp() returns java.sql.Timestamp providing
     * yyyy-mm-dd hh:mm:ss.fffffffff value
     *
     * @param date Given date.
     *
     * @return Date in DD/MM/YYYY HH24:MI:SS format.
     */
    public static String getDateInDDMMYYYY_HHMMSS(java.util.Date date) {
        // Check input
        if (date == null) {
            return "";
        }

        // Set the date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Get the 'DD/MM/YYYY HH24:MI:SS' format
        String strDate = getDateInDDMMYYYY(date)
                + " "
                + getTimeInHHMMSS(date);

        // Return
        return strDate;
    }

    /**
     * Return a Date for the DATE_FORMAT format string
     *
     * @param dateStr Date format string
     *
     * @return Java Date object if the input is valid, else null
     */
    public static Date parseDate(String dateStr) {
        // Check input
        if (dateStr == null) {
            return null;
        }

        // Create the formatter object
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        // Do not parse the string if it do not adhere to the format given
        formatter.setLenient(false);

        // Parse
        ParsePosition pos = new ParsePosition(0);
        Date date = formatter.parse(dateStr, pos);

        // Return
        return date;
    }

    /**
     * Return a String for the given java.util.Date as per DATE_FORMAT
     *
     * @param date Date object
     *
     * @return String for the given java.util.Date as per DATE_FORMAT, null in
     * case of error
     */
    public static String parseDate(Date date) {
        // Check input
        if (date == null) {
            return null;
        }

        // Create the formatter object
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        // Do not parse the string if it do not adhere to the format given
        formatter.setLenient(false);

        // Parse
        return formatter.format(date);
    }

    /**
     * Compares two given valid dates to return a number flag
     *
     * @param dateStr1 Date format string ('MM/DD/YYYY HH24:MM:SS' format)
     * @param dateStr2 Date format string ('MM/DD/YYYY HH24:MM:SS' format)
     *
     * @return Number flag as per following -1 Inputs are wrong (null) 0 Dates
     * are equal 1 First date is before the second one 2 First date is after the
     * second one
     */
    public static int compareDates(String dateStr1, String dateStr2) {
        // Parse the date
        Date date1 = parseDate(dateStr1);
        Date date2 = parseDate(dateStr2);

        // Return
        return compareDates(date1, date2);
    }

    /**
     * Compares two given valid dates to return a number flag
     *
     * @param date1 Date object
     * @param date2 Date object
     *
     * @return Number flag as per following -1 Inputs are wrong (null) 0 Dates
     * are equal 1 First date is before the second one 2 First date is after the
     * second one
     */
    public static int compareDates(Date date1, Date date2) {
        // Check input
        if (date1 == null || date2 == null) {
            return -1;
        }

        int dateDifference = 0;

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // Retrieve days, months and years for both dates
        int day1 = calendar1.get(Calendar.DATE);
        int month1 = calendar1.get(Calendar.MONTH);
        int year1 = calendar1.get(Calendar.YEAR);

        int day2 = calendar2.get(Calendar.DATE);
        int month2 = calendar2.get(Calendar.MONTH);
        int year2 = calendar2.get(Calendar.YEAR);

        // Compare years
        if (year1 < year2) {
            dateDifference = 1;
        } else if (year1 > year2) {
            dateDifference = 2;
        } // Years are same so compare months
        else {
            if (month1 < month2) {
                dateDifference = 1;
            } else if (month1 > month2) {
                dateDifference = 2;
            } // Months are same so compare days
            else {
                if (day1 < day2) {
                    dateDifference = 1;
                } else if (day1 > day2) {
                    dateDifference = 2;
                } else {
                    dateDifference = 0;
                }
            }
        }

        // Return
        return dateDifference;
    }

    /**
     * Get the date after given days
     *
     * @param date Date object
     * @param days Number of days
     *
     * @return New Date
     * @throws DateUtilsException
     */
    public static Date getDateAfterGivenDays(Date date, int days)
            throws DateUtilsException {

        // Check input
        if (date == null) {
            throw new DateUtilsException("DEV_ERROR : Check the date '" + date);
        }

        // Time in milliseconds since "the epoch" (January 1, 1970,
        // 00:00:00 GMT)
        long t1 = date.getTime();
        long t2 = days * MILLISECONDS_IN_ONE_DAY;
        long newms = t1 + t2;
        Date newDate = new Date(newms);

        // Return
        return newDate;
    }

    /**
     * For given two dates return the difference in days.
     *
     * @param dateStr1 Date format string ('MM/DD/YYYY HH24:MM:SS' format)
     * @param dateStr2 Date format string ('MM/DD/YYYY HH24:MM:SS' format)
     *
     * @return Number of days difference
     *
     * @throws DateUtilsException
     */
    public static long getDate1MinusDate2_Days(String dateStr1, String dateStr2)
            throws DateUtilsException {

        // Parse the date
        Date date1 = parseDate(dateStr1);
        Date date2 = parseDate(dateStr2);

        // Return
        return getDate1MinusDate2_Days(date1, date2);
    }

    /**
     * For given two dates return the difference in days.
     *
     * @param date1 Date object
     * @param date2 Date object
     *
     * @return Number of days difference
     *
     * @throws DateUtilsException
     */
    public static long getDate1MinusDate2_Days(Date date1, Date date2)
            throws DateUtilsException {

        // Check input
        if (date1 == null || date2 == null) {
            throw new DateUtilsException("DEV_ERROR : Check the dates '" + date1 + "', '" + date2 + "'");
        }

        // Time in milliseconds since "the epoch" (January 1, 1970,
        // 00:00:00 GMT)
        long t1 = date1.getTime();
        long t2 = date2.getTime();
        long diff = t2 - t1;
        long days = diff / MILLISECONDS_IN_ONE_DAY; // Integral Division

        // Return
        return days;
    }

    /**
     * For given two dates return the difference in months.
     *
     * @param dateStr1 Date format string (DATE_FORMAT format)
     * @param dateStr2 Date format string (DATE_FORMAT format)
     *
     * @return Number of months difference
     *
     * @throws DateUtilsException
     */
    public static int getDate1MinusDate2_Months(String dateStr1, String dateStr2)
            throws DateUtilsException {

        // Parse the date
        Date date1 = parseDate(dateStr1);
        Date date2 = parseDate(dateStr2);

        // Return
        return getDate1MinusDate2_Months(date1, date2);
    }

    /**
     * For given two dates return the difference in months.
     *
     * @param date1 Date object
     * @param date2 Date object
     *
     * @return Number of months difference
     *
     * @throws DateUtilsException
     */
    public static int getDate1MinusDate2_Months(Date date1, Date date2)
            throws DateUtilsException {

        // Check input
        if (date1 == null || date2 == null) {
            throw new DateUtilsException("DEV_ERROR : Check the dates '" + date1 + "', '" + date2 + "'");
        }

        int monthDiff = 0;
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        int month1 = calendar1.get(Calendar.MONTH);
        int month2 = calendar2.get(Calendar.MONTH);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);

        if (month1 == month2) {
            if (year1 == year2) {
                monthDiff = 1;
            } else {
                monthDiff = ((year2 - year1) * 12) + 1;
            }
        } else {
            if (year1 == year2) {
                monthDiff = month2 - month1 + 1;
            } else {
                int totalMonthDiff = (year2 - year1) * 12;
                int tempMonthDiff = month2 - month1 + 1;
                monthDiff = totalMonthDiff + tempMonthDiff;
            }
        }

        if (day2 < day1) {
            monthDiff -= 1;
        }

        // Return
        return monthDiff;
    }

    /**
     * Returns Day or Month or Year Part Of Date from the given date.
     *
     * @param date Date from which Day/Month/Year is to be found.
     * @param partOfDate Part of date (Day, Month, Year)
     *
     * @return part of date (Day, Month, Year)
     *
     * @throws DateUtilsException When date or partOfDate is invalid
     */
    public static int getDatePart(String date, int partOfDate)
            throws DateUtilsException {

        int datePart = 0;

        // Check input
        if (date == null) {
            throw new DateUtilsException("DEV_ERROR : Check the date '" + date + "'");
        }

        Date tempDate = parseDate(date);
        if (tempDate == null) {
            throw new DateUtilsException("DEV_ERROR : Check the date format '"
                    + tempDate + "'");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempDate);

        switch (partOfDate) {
            case DAY:
                datePart = calendar.get(Calendar.DAY_OF_MONTH);
                break;
            case MONTH:
                datePart = calendar.get(Calendar.MONTH) + 1;
                break;
            case YEAR:
                datePart = calendar.get(Calendar.YEAR);
                break;
            default:
                throw new DateUtilsException("DEV_ERROR : partOfDate "
                        + partOfDate + " is not valid");
        }

        // Return
        return datePart;
    }

    /**
     * Creates a date with given Day, Month and Year.
     *
     * @param day Day part of Date
     * @param month Month part of Date
     * @param year Year part of Date
     *
     * @return Date object created
     *
     * @throws DateUtilsException When Day, Month or Year is zero or negative
     * value
     */
    public static Date createDateObject(int day, int month, int year)
            throws DateUtilsException {

        // Check input
        if (day <= 0) {
            throw new DateUtilsException("DEV_ERROR : Check the day '" + day + "'");
        }

        if (month <= 0) {
            throw new DateUtilsException("DEV_ERROR : Check the month '" + month + "'");
        }

        if (year <= 0) {
            throw new DateUtilsException("DEV_ERROR : Check the year '" + year + "'");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        // Return
        return calendar.getTime();
    }

    /**
     * Creates a date with given Day,Month and Year.
     *
     * @param day Day part of Date
     * @param month Month part of Date
     * @param year Year part of Date
     *
     * @return String Created date
     *
     * @throws DateUtilsException When Day, Month or Year is zero or negative
     * value
     */
    public static String createDate(int day, int month, int year)
            throws DateUtilsException {

        Date date = createDateObject(day, month, year);
        return parseDate(date);
    }

    /**
     * Returns the starting date of the month in which given date lies. eg. if
     * date is 23/03/2003 the returned date would be 01/03/2003
     *
     * @param date Date for which the starting date of month is to be found
     *
     * @return String Start date of the month
     *
     * @throws DateUtilsException When date is invalid
     */
    public static String getStartOfMonthDate(String date) throws DateUtilsException {
        String startDate = null;
        Date tempDate = parseDate(date);

        if (tempDate != null) {
            // Set the date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tempDate);
            calendar.set(Calendar.DATE, 1);
            startDate = parseDate(calendar.getTime());
        } else {
            throw new DateUtilsException("DEV_ERROR : Check the date " + date);
        }

        // Return
        return startDate;
    }

    /**
     * Returns the last date of the month in which given date lies. eg. if date
     * is 23/03/2003 the returned date would be 31/03/2003
     *
     * @param date Date for which the last date of month is to be found
     *
     * @return String Last date of the month
     *
     * @throws DateUtilsException When date is invalid
     */
    public static String getLastOfMonthDate(String date) throws DateUtilsException {
        String lastDate = null;
        Date tempDate = parseDate(date);

        if (tempDate != null) {
            // Set the date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tempDate);
            calendar.set(Calendar.DATE, 1);
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            lastDate = parseDate(calendar.getTime());
        } else {
            throw new DateUtilsException("DEV_ERROR : Check the date " + date);
        }

        // Return
        return lastDate;
    }

    /**
     * Adds or Subtracts days, months, years to the date.
     *
     * @param date Date in which days/months/years are to be added
     * @param datePart Part of date which is to be added - day/month/year
     * @param duration Duration of datePart if positive value passed adds to the
     * date, if negative value is passed then subtracts it.
     *
     * @return Date with added Date part
     *
     * @throws DateUtilsException When date or datePart is invalid, duration is
     * zero
     */
    public static Date addToDate(Date date, int datePart, int duration)
            throws DateUtilsException {

        String strDate = null;
        Date modifiedDate = null;

        if (date == null) {
            throw new DateUtilsException("DEV_ERROR : Check the date " + date);
        }

        if (duration == 0) {
            throw new DateUtilsException("DEV_ERROR : invalid duration " + duration);
        }

        // Set the date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        switch (datePart) {
            case DAY:
                calendar.add(Calendar.DATE, duration);
                break;
            case MONTH:
                calendar.add(Calendar.MONTH, duration);
                break;
            case YEAR:
                calendar.add(Calendar.YEAR, duration);
                break;
            default:
                throw new DateUtilsException("DEV_ERROR : invalid Date part "
                        + datePart + " is not valid");
        }

        strDate = parseDate(calendar.getTime());
        modifiedDate = parseDate(strDate);

        // Return
        return modifiedDate;
    }

    /**
     * Adds or Subtracts days, months, years to the date.
     *
     * @param date Date in which days/months/years are to be added
     * @param datePart Part of date which is to be added - day/month/year
     * @param duration Duration of datePart if positive value passed adds to the
     * date, if negative value is passed then subtracts it.
     *
     * @return Date with added Date part
     *
     * @throws DateUtilsException When date or datePart is invalid, duration is
     * zero
     */
    public static Date addToDate(String date, int datePart, int duration)
            throws DateUtilsException {

        Date tempDate = parseDate(date);
        return addToDate(tempDate, datePart, duration);
    }

    /**
     * Check whether date1 is after date2
     *
     * @param strDate1 First date in string format
     * @param strDate2 Second date in string format
     *
     * @return true if passed date1 is after date2 else false
     */
    public static boolean isAfter(String strDate1, String strDate2) {
        boolean result = false;
        if (DateUtils.compareDates(strDate1, strDate2) == 2) {
            result = true;
        }

        // Return        
        return result;
    }

    /**
     * Check whether date1 is before date2
     *
     * @param strDate1 First date in string format
     * @param strDate2 Second date in string format
     *
     * @return true if passed date1 is before date2 else false
     */
    public static boolean isBefore(String strDate1, String strDate2) {
        boolean result = false;
        if (DateUtils.compareDates(strDate1, strDate2) == 1) {
            result = true;
        }

        // Return
        return result;
    }

    /**
     * Checks if the given date is a week day. The week days are - Monday to
     * Friday, if the date falls on any of these days the function returns true,
     * else returns false.
     *
     * @param date Date which is checked if it is a week day
     *
     * @return Returns true if the date is a week day, else returns false
     *
     * @throws DateUtilsException When date parameter is null
     */
    public static boolean isWeekDay(java.util.Date date) throws DateUtilsException {
        if (date == null) {
            throw new DateUtilsException("DEV_ERROR : Check the date '" + date + "'");
        }

        boolean weekDay = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.MONDAY:
            case Calendar.TUESDAY:
            case Calendar.WEDNESDAY:
            case Calendar.THURSDAY:
            case Calendar.FRIDAY:
            case Calendar.SATURDAY:
                weekDay = true;
                break;

            case Calendar.SUNDAY:
                weekDay = false;
                break;

            default:
                Debug.log(Debug.BUG + "Control should never come here"
                        + " TaxCalculator:isWeekDay");
                break;
        }

        //Return
        return weekDay;
    }

    /**
     * Here get the number of days in particular month
     *
     * @param c GregorianCalendar
     *
     * @return int Number of days in particular month
     */
    public static int daysInMonth(GregorianCalendar c) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        daysInMonths[1] += c.isLeapYear(c.get(GregorianCalendar.YEAR)) ? 1 : 0;
        return daysInMonths[c.get(GregorianCalendar.MONTH)];
    }

    /**
     * Here get the date in format 'DD-MM-YYYY'
     *
     * @return String Date in format 'DD-MM-YYYY'
     */
    public static String formatDate(String strDate) {
        int dashPos1 = 0, dashPos2 = 0, dashPos3 = 0;
        String strDay = "00", strMonth = "00", strYear = "0000", strcurrCent = "";
        strDate = strDate.replace('.', '-');
        strDate = strDate.replace('/', '-');
        //
        dashPos1 = strDate.indexOf('-');
        if (dashPos1 > -1) {
            dashPos2 = strDate.indexOf('-', dashPos1 + 1);
            if (dashPos2 > -1) {
                dashPos3 = strDate.indexOf('-', dashPos2 + 1);
                if (dashPos3 > -1) {
                    strDate = strDate.substring(0, dashPos3);
                }
            }
        }
        //
        dashPos1 = strDate.indexOf('-');
        if (dashPos1 > -1) {
            strDay = strDate.substring(0, dashPos1).trim();
            switch (strDay.length()) {
                case 0:
                    strDay = "00";
                    break;
                case 1:
                    strDay = "0" + strDay;
                    break;
                default:
                    strDay = strDay.substring(0, 2);
                    break;
            }
            dashPos2 = strDate.indexOf('-', dashPos1 + 1);
            if (dashPos2 > -1) {
                strMonth = strDate.substring(dashPos1 + 1, dashPos2).trim();
                switch (strMonth.length()) {
                    case 0:
                        strMonth = "00";
                        break;
                    case 1:
                        strMonth = "0" + strMonth;
                        break;
                    default:
                        strMonth = strMonth.substring(0, 2);
                        break;
                }
                strYear = strDate.substring(dashPos2 + 1).trim();
                strcurrCent = getCurrentDate().substring(6, 8);
                switch (strYear.length()) {
                    case 0:
                        strYear = strcurrCent + "00";
                        break;

                    case 1:
                        strYear = strcurrCent + "0" + strYear;
                        break;

                    case 2:
                        strYear = strcurrCent + strYear;
                        break;

                    case 3:
                        strYear = "0" + strYear;
                        break;

                    default:
                        strYear = strYear.substring(0, 4);
                        break;
                }
            }

            strDate = strDay + '-' + strMonth + '-' + strYear;
        }

        return strDate;
    }

    /**
     * Here get the current date in format 'dd-MM-yyyy'
     *
     * @return String Current date in format 'dd-MM-yyyy'
     */
    public static String getCurrentDate() {
        //    get current date & time
        /*
         ** on some JDK, the default TimeZone is wrong
         ** we must set the TimeZone manually!!!
         **   Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("EST"));
         */
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DateUtils.DATE_FORMAT);

        /*
         ** on some JDK, the default TimeZone is wrong
         ** we must set the TimeZone manually!!!
         **     sdf.setTimeZone(TimeZone.getTimeZone("EST"));
         */
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(cal.getTime());
    }

    /**
     * Here get the date in format YYYYMMDD
     *
     * @param c GregorianCalendar
     *
     * @return String Date in YYYYMMDD string format
     */
    public static String getStrDate(GregorianCalendar c) {
        //    Returns as a String (YYYYMMDD) a GregorianCalendar date
        int m = c.get(GregorianCalendar.MONTH) + 1;
        int d = c.get(GregorianCalendar.DATE);
        //return  "" +  c.get(GregorianCalendar.YEAR) + (m < 10 ? "0" + m : m) + (d < 10 ? "0" + d : d);
        return "" + c.get(GregorianCalendar.YEAR) + m + d;
    }

    /**
     * Here validate the date of format 'DD-MM-YYYY'
     *
     * @param strDate Date in 'DD-MM-YYYY' format
     *
     * @return boolean Input date is valid or not.
     */
    public static boolean validateDate(String strDate) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DateUtils.DATE_FORMAT);

        /*
         ** on some JDK, the default TimeZone is wrong
         ** we must set the TimeZone manually!!!
         **     sdf.setTimeZone(TimeZone.getTimeZone("EST"));
         */
        sdf.setTimeZone(TimeZone.getDefault());
        try {
            sdf.setLenient(false);  // this is important!
            Date dt2 = sdf.parse(strDate);
            //System.out.println("Date is ok = " + dt2);
            return true;
        } catch (ParseException e) {
            //System.out.println(e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            //System.out.println("Invalid date");
            return false;
        }
    }

    /**
     * Here get the current date in format 'YYYY-MM-DD'
     *
     * @return Current date in format 'YYYY-MM-DD'
     */
    public static String getCurrentDate_YYYY_MM_DD() {
        //    get current date in Format YYYY-MM-DD
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final String dateFormat = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(cal.getTime());
    }

    /**
     * Get the date before given days
     *
     * @param date Date object
     * @param days Number of days
     *
     * @return New Date
     * @throws DateUtilsException
     */
    public static Date getDateBeforeGivenDays(Date date, int days)
            throws DateUtilsException {

        // Check input
        if (date == null) {
            throw new DateUtilsException("DEV_ERROR : Check the date '" + date);
        }

        // Time in milliseconds since "the epoch" (January 1, 1970,
        // 00:00:00 GMT)
        long t1 = date.getTime();
        long t2 = days * MILLISECONDS_IN_ONE_DAY;
        long newms = t1 - t2;
        Date newDate = new Date(newms);

        // Return
        return newDate;
    }

    /**
     * Main for testing
     *
     * @throws DateUtilsException
     */
    public static void main(String[] args) throws DateUtilsException {
        //getDate1MinusDate2_Days
        /*
         System.out.println(getDate1MinusDate2_Days("12/03/2001 20:10:12", "12/04/2001 20:10:12"));
         System.out.println(getDate1MinusDate2_Days("12/03/2001 20:10:12", "12/04/2001 20:10:11"));
         System.out.println(getDate1MinusDate2_Days("12/03/2001 20:10:12", "12/04/2001 20:10:13"));
         System.out.println(getDate1MinusDate2_Days("12/03/2001 20:10:12", "12/02/2001 20:10:12"));                      
         */
        /*
         //parseDate
         System.out.println(parseDate("12/03/2001 20:10:12"));
         System.out.println(parseDate("2001 20:10:12"));
         System.out.println(parseDate((Date)null));        
        
         System.out.println("getStartOfMonthDate : " +getStartOfMonthDate("18-4-2001"));
         System.out.println("getLastOfMonthDate : " +getLastOfMonthDate("18-4-2001"));
        
         //getDatePart()
         System.out.println("DAY " +getDatePart("18-4-2001", DAY));
         System.out.println("MONTH " +getDatePart("18-4-2001", MONTH));
         System.out.println("YEAR " +getDatePart("18-4-2001", YEAR));
        
         //addToDate()
         System.out.println("addToDate : " +addToDate("31-3-2001", DAY, 1));
         System.out.println("addToDate : " +addToDate("18-4-2001", MONTH, 5));
         System.out.println("addToDate : " +addToDate("18-4-2001", YEAR, 1));
         System.out.println("addToDate : " +addToDate("18-4-2001", DAY, -1));
         System.out.println("addToDate : " +addToDate("18-4-2001", MONTH, -1));
         System.out.println("addToDate : " +addToDate("18-4-2001", YEAR, -1));
        
         System.out.println("createDate : " +createDate(1,3,2004));
         */
        System.out.println("Difference in months 1:" + getDate1MinusDate2_Months(parseDate("1-1-2000"), parseDate("1-1-2000")));
        System.out.println("Difference in months 0:" + getDate1MinusDate2_Months(parseDate("20-1-2000"), parseDate("1-1-2000")));
        System.out.println("Difference in months 12:" + getDate1MinusDate2_Months(parseDate("1-1-2000"), parseDate("1-1-2001")));
        System.out.println("Difference in months 11:" + getDate1MinusDate2_Months(parseDate("10-1-2000"), parseDate("5-1-2001")));
        System.out.println("Difference in months 12:" + getDate1MinusDate2_Months(parseDate("10-1-2000"), parseDate("15-1-2001")));

        System.out.println("Difference in months 1:" + getDate1MinusDate2_Months(parseDate("1-1-2000"), parseDate("1-2-2000")));
        System.out.println("Difference in months 1:" + getDate1MinusDate2_Months(parseDate("10-1-2000"), parseDate("10-2-2000")));
        System.out.println("Difference in months 0:" + getDate1MinusDate2_Months(parseDate("10-1-2000"), parseDate("5-2-2000")));
        System.out.println("Difference in months 1:" + getDate1MinusDate2_Months(parseDate("10-1-2000"), parseDate("15-2-2000")));

        System.out.println("Difference in months 14:" + getDate1MinusDate2_Months(parseDate("1-1-2000"), parseDate("1-2-2001")));
        System.out.println("Difference in months 15:" + getDate1MinusDate2_Months(parseDate("10-1-2000"), parseDate("10-3-2001")));
        System.out.println("Difference in months 14:" + getDate1MinusDate2_Months(parseDate("10-1-2000"), parseDate("1-3-2001")));
        System.out.println("Difference in months 15:" + getDate1MinusDate2_Months(parseDate("10-1-2000"), parseDate("15-3-2001")));
        System.out.println("Difference in months 8:" + getDate1MinusDate2_Months(parseDate("10-6-2000"), parseDate("10-1-2001")));
        System.out.println("Difference in months 7:" + getDate1MinusDate2_Months(parseDate("10-6-2000"), parseDate("1-1-2001")));
        System.out.println("Difference in months 8:" + getDate1MinusDate2_Months(parseDate("10-6-2000"), parseDate("15-1-2001")));

        System.out.println("Difference in months 20:" + getDate1MinusDate2_Months(parseDate("10-6-2000"), parseDate("15-1-2002")));
        // getDateBeforeGivenDays()
        Calendar calendar = Calendar.getInstance();
        Date dt = null;
        //
        System.out.println("getDateBeforeGivenDays : " + getDateBeforeGivenDays(new Date(), 1));
        System.out.println("getDateBeforeGivenDays : " + getDateBeforeGivenDays(new Date(), 365));
        //
        calendar.set(2003, 2, 1);
        dt = calendar.getTime();
        System.out.println("getDateBeforeGivenDays : " + getDateBeforeGivenDays(dt, 1));
        //
        calendar.set(2000, 2, 1);
        dt = calendar.getTime();
        System.out.println("getDateBeforeGivenDays : " + getDateBeforeGivenDays(dt, 1));

        // Test compare dates function
        java.util.Date date1 = getCurrentLocalDate();
        java.util.Date date2 = parseDate("25-07-2003");
        java.util.Date date3 = getCurrentLocalDate();
        System.out.println(compareDates(date1, date3));
        System.out.println(compareDates(date1, date2));
    }
}

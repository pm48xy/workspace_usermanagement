package nic.java.util;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : NumberToWords
// PURPOSE          : Converts a Number into words (Indian Number System)
// NOTES            : None
// LAST MODIFIED    :
//  20030919 GUM019 Package re-structuring
//  20030818 CPO007 Created (Original code by YOG)
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//
//NONE

/**
 * Converts a Number into words (Indian Number System)
 *
 * @author YOG
 */
public class NumberToWords {

    ////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ////////////////////////////////////////////////////////////////////////
    /**
     * One to Nineteen Words. Numbers between 1 and 19 (both inclusive)
     */
    private static final String STR_NUMBER_EEN[] = {"ONE", "TWO", "THREE",
        "FOUR", "FIVE", "SIX",
        "SEVEN", "EIGHT", "NINE",
        "TEN", "ELEVEN", "TWELVE",
        "THIRTEEN", "FOURTEEN", "FIFTEEN",
        "SIXTEEN", "SEVENTEEN", "EIGHTEEN",
        "NINETEEN"};
    /**
     * 20, 30, ..., 90
     */
    private static final String STR_NUMBER_TY[] = {"TWENTY", "THIRTY", "FOURTY",
        "FIFTY", "SIXTY", "SEVENTY",
        "EIGHTY", "NINETY"};

    ////////////////////////////////////////////////////////////////////////
    // METHODS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Return the String representation in words for a given Number. Breaks a
     * given number into various Indian number parts. There are total five parts
     * ie ...
     * <PRE>
     *                  Crores Lakhs Thousands Hundreds Tens
     * 25709704517      2570,  97,   04,       5,       17
     * </PRE>
     *
     * @param number Given number to convert into the number word parts
     *
     * @return String representation of given number (eg 99120008763 : NINE
     * THOUSAND NINE HUNDRED TWELVE CRORE EIGHT THOUSAND SEVEN HUNDRED SIXTY
     * THREE ONLY)
     */
    public static String numberToWords(long number) {
        return convertNumberToWords(number) + " ONLY";
    }

    /**
     * Return the String representation in words for a given Number
     *
     * @param number Number
     *
     * @return String representation of given number
     */
    private static String convertNumberToWords(long number) {
        StringBuffer sb = new StringBuffer();

        String[] parts = breakIntoParts(number);
        String toword = null;

        // Crore part
        if (parts[4] != null && !parts[4].equals("")) {
            String crores = convertNumberToWords(Long.parseLong(parts[4]));
            sb.append(crores + " CRORE");
        }

        // Lakh part        
        if (parts[3] != null && !parts[3].equals("")) {
            toword = toWord(parts[3]);
            if (!toword.equals("")) {
                sb.append(" " + toword + " LAKH");
            }
        }

        // Thousand part
        if (parts[2] != null && !parts[2].equals("")) {
            toword = toWord(parts[2]);
            if (!toword.equals("")) {
                sb.append(" " + toword + " THOUSAND");
            }
        }

        // Hundred part        
        if (parts[1] != null && !parts[1].equals("")) {
            toword = toWord(parts[1]);
            if (!toword.equals("")) {
                sb.append(" " + toword + " HUNDRED");
            }
        }

        // Ten part        
        if (parts[0] != null && !parts[0].equals("")) {
            toword = toWord(parts[0]);
            if (!toword.equals("")) {
                sb.append(" " + toword);
            }
        }

        // Handle negative numbers
        if (parts[5] != null) {
            sb.insert(0, parts[5] + " ");
        }

        // Return
        String numberInWords = sb.toString();
        return numberInWords;
    }

    /**
     * Breaks a given number into various Indian number parts. There are total
     * five parts this method returns ie ...
     * <PRE>
     *                  Crores Lakhs Thousands Hundreds Tens
     * 25709704517      2570,  97,   04,       5,       17
     * </PRE> If some part is not there then this returns null for that part in
     * the return String array. If the number is a negative number then the
     * sixth element of the returned array will have a not null value to reflect
     * that the number is a negative one.
     *
     * @param number Given number to convert into the number word parts
     *
     * @return String array containing the various number word parts
     */
    public static String[] breakIntoParts(long number) {
        // Number of parts we are dealing
        final int N_PARTS = 6;    // Sixth element is for negative flag

        // Create and initialize the array
        String[] numWordParts = new String[N_PARTS];
        for (int i = 0; i < N_PARTS; i++) {
            numWordParts[i] = null;
        }

        // Handle negative number
        if (number < 0) {
            number = -number;
            numWordParts[5] = "(negative)";
        }

        // Break parts
        String strNumber = new String(number + "");
        int nDigits = strNumber.length();
        int sindex;
        int eindex;

        // Crore part
        if (nDigits > 7) {
            sindex = 0;
            eindex = nDigits - 7;
            numWordParts[4] = strNumber.substring(sindex, eindex);
        }

        // Lakh part        
        if (nDigits > 5) {
            sindex = (nDigits > 6) ? nDigits - 7 : nDigits - 6;
            eindex = nDigits - 5;
            numWordParts[3] = strNumber.substring(sindex, eindex);
        }

        // Thousand part        
        if (nDigits > 3) {
            sindex = (nDigits > 4) ? nDigits - 5 : nDigits - 4;
            eindex = nDigits - 3;
            numWordParts[2] = strNumber.substring(sindex, eindex);
        }

        // Hundred part        
        if (nDigits > 2) {
            sindex = nDigits - 3;
            eindex = nDigits - 2;
            numWordParts[1] = strNumber.substring(sindex, eindex);
        }

        // Ten part        
        if (nDigits >= 1) {
            sindex = (nDigits > 1) ? nDigits - 2 : nDigits - 1;
            eindex = nDigits;
            numWordParts[0] = strNumber.substring(sindex, eindex);
        }

        // Return the parts array
        return numWordParts;
    }

    /**
     * Returns a word representaion of given maximum of two digit number (eg 17,
     * 9)
     *
     * @param part A maximum of two digit part (eg 13, 30, 98 etc)
     *
     * @return Words representation (eg 13 - Thirteen, 21 - Twenty one)
     */
    private static String toWord(String part) {
        // Locals
        int m;    // 10s place   (mn eg 17  - 1)
        int n;    // Unit place  (mn eg 17  - 7)
        String word = "";

        // Handle number separately for more than 19 and separately for <= 19
        int p = Integer.parseInt(part);
        if (p > 19) {
            m = Integer.parseInt(part.substring(0, 1));
            n = Integer.parseInt(part.substring(1, 2));
            word = STR_NUMBER_TY[m - 2];
            if (n != 0) {
                word += " " + STR_NUMBER_EEN[n - 1];
            }
        } else {
            n = Integer.parseInt(part);
            if (n != 0) {
                word = STR_NUMBER_EEN[n - 1];
            }
        }

        return word;
    }

    /**
     * Test
     */
    public static void main(String[] args) {
        String[] parts;
        parts = breakIntoParts(475858456);
        System.out.println("475858456 = " + parts[4] + " " + parts[3] + " " + parts[2] + " " + parts[1] + " " + parts[0]);
        parts = breakIntoParts(0);
        System.out.println("0 = " + parts[4] + " " + parts[3] + " " + parts[2] + " " + parts[1] + " " + parts[0]);
        parts = breakIntoParts(10);
        System.out.println("10 = " + parts[4] + " " + parts[3] + " " + parts[2] + " " + parts[1] + " " + parts[0]);
        parts = breakIntoParts(102);
        System.out.println("102 = " + parts[4] + " " + parts[3] + " " + parts[2] + " " + parts[1] + " " + parts[0]);
        parts = breakIntoParts(1001);
        System.out.println("1001 = " + parts[4] + " " + parts[3] + " " + parts[2] + " " + parts[1] + " " + parts[0]);
        parts = breakIntoParts(98299);
        System.out.println("98299 = " + parts[4] + " " + parts[3] + " " + parts[2] + " " + parts[1] + " " + parts[0]);
        parts = breakIntoParts(198299);
        System.out.println("198299 = " + parts[4] + " " + parts[3] + " " + parts[2] + " " + parts[1] + " " + parts[0]);
        parts = breakIntoParts(9098009);
        System.out.println("9098009 = " + parts[4] + " " + parts[3] + " " + parts[2] + " " + parts[1] + " " + parts[0]);
        parts = breakIntoParts(29098009);
        System.out.println("29098009 = " + parts[4] + " " + parts[3] + " " + parts[2] + " " + parts[1] + " " + parts[0]);
        parts = breakIntoParts(329098009);
        System.out.println("329098009 = " + parts[4] + " " + parts[3] + " " + parts[2] + " " + parts[1] + " " + parts[0]);
        parts = breakIntoParts(3029098009L);
        System.out.println("3029098009 = " + parts[4] + " " + parts[3] + " " + parts[2] + " " + parts[1] + " " + parts[0]);
        parts = breakIntoParts(1234567890L);
        System.out.println("1234567890 = " + parts[4] + " " + parts[3] + " " + parts[2] + " " + parts[1] + " " + parts[0]);

        System.out.println("-10              : " + NumberToWords.numberToWords(-10));
        System.out.println("1                : " + NumberToWords.numberToWords(1));
        System.out.println("12               : " + NumberToWords.numberToWords(12));
        System.out.println("123              : " + NumberToWords.numberToWords(123));
        System.out.println("1234             : " + NumberToWords.numberToWords(1234));
        System.out.println("12345            : " + NumberToWords.numberToWords(12345));
        System.out.println("123456           : " + NumberToWords.numberToWords(123456));
        System.out.println("1234567          : " + NumberToWords.numberToWords(1234567));
        System.out.println("12345678         : " + NumberToWords.numberToWords(12345678));
        System.out.println("222445606        : " + NumberToWords.numberToWords(222445606));
        System.out.println("122334553        : " + NumberToWords.numberToWords(122334553));
        System.out.println("834746568        : " + NumberToWords.numberToWords(834746568));
        System.out.println("475858456        : " + NumberToWords.numberToWords(475858456));
        System.out.println("1234567890123    : " + NumberToWords.numberToWords(1234567890123L));
        System.out.println("47,58,58,4,56    : " + NumberToWords.numberToWords(475858456));
        System.out.println("908070605041     : " + NumberToWords.numberToWords(908070605041L));
        System.out.println("100100500        : " + NumberToWords.numberToWords(100100500));
        System.out.println("99120008763      : " + NumberToWords.numberToWords(99120008763L));
    }
}

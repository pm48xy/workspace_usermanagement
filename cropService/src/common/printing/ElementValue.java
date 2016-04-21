package common.printing;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : ElementValue
// PURPOSE          : A value (String) to print at specified location
// NOTES            : None
// LAST MODIFIED    :
//  20030818 CPO007 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.util.*;
import java.text.NumberFormat;
import java.io.*;
//
// Importing Client/Common java packages/classes
//
// NONE

/**
 * A value (String) to print at specified location
 *
 * @author CPO
 */
public class ElementValue implements Serializable {

    ////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ////////////////////////////////////////////////////////////////////////
    /**
     * X (pixel) location
     */
    private int x;

    /**
     * Y (pixel) location
     */
    private int y;

    /**
     * Data to print
     */
    private String value;

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////
    public ElementValue(int x, int y, String value)
            throws PrintDataException {

        // Check input
        if (value == null || x < 0 || y < 0) {
            throw new PrintDataException("Incorrect value passed : x: "
                    + x + " y: " + y + " value: " + value);
        }

        // Initialize
        this.x = x;
        this.y = y;
        this.value = value;
    }

    ////////////////////////////////////////////////////////////////////////
    // getters/setters METHODS
    ///////////////////////////////////////////////////////////////////////
    /**
     * Returns x coordinate location
     *
     * @return x the X coordinate location
     */
    public int getX() {
        return x;
    }

    /**
     * Returns Y coordinate location
     *
     * @return y the Y coordinate location
     */
    public int getY() {
        return y;
    }

    /**
     * Returns value of element
     *
     * @return value string value of element
     */
    public String getValue() {
        return value;
    }

    ////////////////////////////////////////////////////////////////////////
    // METHODS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Debug Method
     */
    public String dump() {
        return "x,y,value : " + x + "," + y + "," + value;
    }
}

package common.printing;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : ElementLabel
// PURPOSE          : A Label (String) to print at specified location
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
 * A Label (String) to print at specified location
 *
 * @author CPO
 */
public class ElementLabel implements Serializable {

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
    private String label;

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////
    public ElementLabel(int x, int y, String label)
            throws PrintDataException {

        // Check input
        if (x < 0 || y < 0 || label == null) {
            throw new PrintDataException("Incorrect label passed : x:"
                    + x + " y: " + y + " label: " + label);
        }

        // Initialize
        this.x = x;
        this.y = y;
        this.label = label;
    }

    ////////////////////////////////////////////////////////////////////////
    // getters/setters METHODS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Returns X coordinate location
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
     * Return label string
     *
     * @return label the label String
     */
    public String getLabel() {
        return label;
    }

    ////////////////////////////////////////////////////////////////////////
    // METHODS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Debug Method
     */
    public String dump() {
        return "x,y,label : " + x + "," + y + "," + label;
    }
}

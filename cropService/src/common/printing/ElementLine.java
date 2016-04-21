package common.printing;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : ElementLine
// PURPOSE          : A Line to be printed at specified location
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
 * A Line to be printed at specified location
 *
 * @author CPO
 */
public class ElementLine implements Serializable {

    ////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ////////////////////////////////////////////////////////////////////////
    /**
     * X1 (pixel) location
     */
    private int x1;

    /**
     * Y1 (pixel) location
     */
    private int y1;

    /**
     * X2 (pixel) location
     */
    private int x2;

    /**
     * Y2 (pixel) location
     */
    private int y2;

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Constructor
     */
    public ElementLine(int x1, int y1, int x2, int y2)
            throws PrintDataException {

        // Check input
        if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0) {
            throw new PrintDataException("Incorrect data passed :"
                    + "x1: " + x1 + " y1: " + y1
                    + " x2: " + x2 + " y2: " + y2);
        }

        // Initialize
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    ////////////////////////////////////////////////////////////////////////
    // getters/setters METHODS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Returns start point X coordinate location
     *
     * @return x1 the start point X coordinate location
     */
    public int getX1() {
        return x1;
    }

    /**
     * Returns start point Y coordinate location
     *
     * @return y1 the start point Y coordinate location
     */
    public int getY1() {
        return y1;
    }

    /**
     * Returns end point X coordinate location
     *
     * @return x2 the end point X coordinate location
     */
    public int getX2() {
        return x2;
    }

    /**
     * Returns end point Y coordinate location
     *
     * @return y2 the end point Y coordinate location
     */
    public int getY2() {
        return y2;
    }

    ////////////////////////////////////////////////////////////////////////
    // METHODS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Debug Method
     */
    public String dump() {
        return "x1,y1,x2,y2 : " + x1 + "," + y1 + "," + x2 + "," + y2;
    }
}

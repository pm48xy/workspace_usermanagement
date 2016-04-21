package common.printing;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : PrintValues
// PURPOSE          : Contains the list of the print elements
// NOTES            : None
// LAST MODIFIED    : 20030415 modified function PrintValues
//  20030903 GES012 Corrected javadoc
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
import java.awt.*;
import java.awt.print.*;
import java.io.*;
//
// Importing Client/Common java packages/classes
//
// NONE

/**
 * Contains the list of the print elements
 *
 * @author CPO
 */
public class PrintValues implements Serializable {

    ////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ////////////////////////////////////////////////////////////////////////
    /**
     * ElementValue list
     */
    private ArrayList elementValues = new ArrayList();

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Constructor
     */
    public PrintValues() {
    }

    /**
     * Constructor to initialize the PrintValues object with the values and
     * position vectors.
     *
     * @param values - Vector containing the values
     * @param x - vector with the X coordinates
     * @param y - vector with the Y coordinates
     */
    public PrintValues(Vector values, Vector x, Vector y)
            throws PrintDataException {

        // Check inputs
        System.out.println(values.size() + ": " + x.size() + ":" + y.size());

        if (values.size() == 0 || x.size() == 0 || y.size() == 0) {
            throw new PrintDataException("DEV_ERROR : Some of the values given is null");
        }

        // Make the values elements
        int inSize = values.size();
        int inPosX;
        int inPosY;
        for (int i = 0; i < inSize; i++) {
            if (values.elementAt(i) == null) {
                throw new PrintDataException("DEV_ERROR : Null values found");
            }

            inPosX = Integer.parseInt(x.elementAt(i).toString());
            inPosY = Integer.parseInt(y.elementAt(i).toString());

            if (values.elementAt(i) != null) {
                elementValues.add(new ElementValue(inPosX, inPosY, values.elementAt(i).toString()));
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////
    // getters/setters METHODS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Returns arraylist containing ElementValues
     *
     * @return elementValues ArrayList containing ElementValues
     */
    public ArrayList getElementValues() {
        return elementValues;
    }

    ////////////////////////////////////////////////////////////////////////
    // METHODS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Return the list of the ElementValue
     *
     * @return pe the array of ElementValue
     */
    public ElementValue[] getElementValuesArray() throws PrintDataException {
        int size = elementValues.size();
        if (size == 0) {
            throw new PrintDataException("No values to print. How come!");
        }

        ElementValue[] pe = new ElementValue[size];
        for (int i = 0; i < size; i++) {
            pe[i] = (ElementValue) elementValues.get(i);
        }

        return pe;
    }

    /**
     * Add the given print element values
     *
     * @param x - X coordinate
     * @param y - Y coordinate
     * @param value - element string
     */
    public void addElementValue(int x, int y, String value)
            throws PrintDataException {

        elementValues.add(new ElementValue(x, y, value));
    }

    /**
     * Implement the print method. Prints the page at the specified index into
     * the specified Graphics context in the specified format.
     *
     * @param graphics - the context into which the page is drawn
     * @param pageFormat - the size and orientation of the page being drawn
     * @param pageIndex - the zero based index of the page to be drawn
     *
     * @return PAGE_EXISTS if the page is rendered successfully or NO_SUCH_PAGE
     * if pageIndex specifies a non-existent page
     *
     * @throws PrinterException - thrown when the print job is terminated
     */
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {

        Graphics2D graphics2D = (Graphics2D) graphics;

        if (pageIndex == 0) {
            graphics2D.translate(pageFormat.getImageableX(),
                    pageFormat.getImageableY());

            // start
            try {
                ElementValue[] printElems = getElementValuesArray();
                Font ft = new Font("Arial", Font.PLAIN, 9);
                graphics2D.setFont(ft);
                for (int i = 0; i < printElems.length; i++) {
                    graphics2D.drawString(printElems[i].getValue(),
                            printElems[i].getX(),
                            printElems[i].getY());
                }
            } catch (Exception ex) {
                //TODO Handle it better way
                throw new PrinterException(ex.getMessage());
            }

            return Printable.PAGE_EXISTS;
        } else {
            return Printable.NO_SUCH_PAGE;
        }
    }

    /**
     * Debug Method
     */
    public void dump() {
        int size = elementValues.size();
        for (int i = 0; i < size; i++) {
            ((ElementValue) elementValues.get(i)).dump();
        }
    }
}

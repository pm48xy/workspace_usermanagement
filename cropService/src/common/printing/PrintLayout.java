package common.printing;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : PrintValues
// PURPOSE          : Contains the list of the print elements
// NOTES            : None
// LAST MODIFIED    : 20030415 modified function PrintValues
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
public class PrintLayout implements Serializable {

    ////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ////////////////////////////////////////////////////////////////////////
    /**
     * LineElement list
     */
    private ArrayList lineElements = new ArrayList(); // <ElementLine>
    private ArrayList labelElements = new ArrayList(); // <ElementLabel>

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////
    public PrintLayout() {
    }

    /**
     * Constructor to initialize the PrintLayout object with the label and line
     * co-ordinates.
     *
     * @param lineElements - ArrayList with the line coordinates
     * @param labelElements - ArrayList with the label text and coordinates
     *
     */
    public PrintLayout(ArrayList lineElements, ArrayList labelElements) {
        this.lineElements = lineElements;
        this.labelElements = labelElements;
    }

    ////////////////////////////////////////////////////////////////////////
    // getters/setters METHODS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Returns arraylist containing line coordinates
     *
     * @return lineElementes the arraylist containing line coordinates
     */
    public ArrayList getElementLines() {
        return lineElements;
    }

    /**
     * Returns arraylist containing label coordinates
     *
     * @return labelElementes the arraylist containing label coordinates
     */
    public ArrayList getElementLabels() {
        return labelElements;
    }

    ////////////////////////////////////////////////////////////////////////
    // METHODS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Return the list of the lineElements.
     */
    public ElementLine[] getElementLineArray() throws PrintDataException {
        int size = lineElements.size();
        if (size == 0) {
            throw new PrintDataException("No data to print. How come!");
        }

        ElementLine[] el = new ElementLine[size];
        for (int i = 0; i < size; i++) {
            el[i] = (ElementLine) lineElements.get(i);
        }

        return el;
    }

    /**
     * Return the list of the labelElements.
     */
    public ElementLabel[] getElementLabelArray() throws PrintDataException {
        int size = labelElements.size();
        if (size == 0) {
            throw new PrintDataException("No data to print. How come!");
        }

        ElementLabel[] elabel = new ElementLabel[size];
        for (int i = 0; i < size; i++) {
            elabel[i] = (ElementLabel) labelElements.get(i);
        }

        return elabel;
    }

    /**
     * Add the given line element data
     */
    public void addElementLine(int x1, int y1, int x2, int y2)
            throws PrintDataException {

        lineElements.add(new ElementLine(x1, y1, x2, y2));
    }

    /**
     * Add the given label element data
     */
    public void addElementLabel(int x, int y, String label)
            throws PrintDataException {

        labelElements.add(new ElementLabel(x, y, label));
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
                ElementLine[] lineElems = getElementLineArray();
                for (int i = 0; i < lineElems.length; i++) {
                    graphics2D.drawLine(lineElems[i].getX1(),
                            lineElems[i].getY1(),
                            lineElems[i].getX2(),
                            lineElems[i].getY2());
                }

                ElementLabel[] labelElems = getElementLabelArray();
                //Font ft = new Font("Arial", Font.BOLD, 9);
                Font ft = new Font("Arial", Font.BOLD, 7);
                graphics2D.setFont(ft);
                for (int i = 0; i < labelElems.length; i++) {
                    graphics2D.drawString(labelElems[i].getLabel(),
                            labelElems[i].getX(),
                            labelElems[i].getY());
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
        int lineSize = lineElements.size();
        int labelSize = lineElements.size();
        for (int i = 0; i < lineSize; i++) {
            ((ElementLine) lineElements.get(i)).dump();
        }

        for (int i = 0; i < labelSize; i++) {
            ((ElementLine) labelElements.get(i)).dump();
        }
    }
}

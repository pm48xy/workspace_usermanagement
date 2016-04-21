package common.printing;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : PrintData
// PURPOSE          : Contains the list of the print elements
// NOTES            : None
// LAST MODIFIED    :
//  20030903 GES012 Corrected javadoc
///  20030818 CPO007 Created
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
 * specific Printable data.
 */
public class PrintData implements Printable, Serializable {

    ////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ////////////////////////////////////////////////////////////////////////
    /**
     * PrintLayout Object
     */
    private PrintLayout printLayout;

    /**
     * PrintValues Object
     */
    private PrintValues printValues;

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ////////////////////////////////////////////////////////////////////////
    public PrintData(PrintLayout printLayout, PrintValues printValues) {
        this.printLayout = printLayout;
        this.printValues = printValues;
    }

    ////////////////////////////////////////////////////////////////////////
    // getters/setters METHODS
    ///////////////////////////////////////////////////////////////////////
    /**
     * Returns PrintLayout
     *
     * @return printLayout the PrintLayout object
     */
    public PrintLayout getPrintLayout() {
        return printLayout;
    }

    /**
     * Returns PrintValues
     *
     * @return printValues the PrintValues object
     */
    public PrintValues getPrintValues() {
        return printValues;
    }

    ////////////////////////////////////////////////////////////////////////
    // METHODS
    ////////////////////////////////////////////////////////////////////////
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

        printLayout.print(graphics, pageFormat, pageIndex);
        return printValues.print(graphics, pageFormat, pageIndex);
    }

    /**
     * Debug Method
     */
    public void dump() {
        printLayout.dump();
        printValues.dump();
    }
}

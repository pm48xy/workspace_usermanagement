package common.printing;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : PrintPreviewPanel
// PURPOSE          : Print Preview Panel
// NOTES            : None
// LAST MODIFIED    :
//  20040303 GUM030 Auditing - 'Performance Audits'
//  20030818 CPO007 Created
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
//
// Importing Server/Common java packages/classes
//
import common.printing.*;

/**
 * Print Preview Panel
 */
public class PrintPreviewPanel extends javax.swing.JPanel implements MouseMotionListener {

    /////////////////////////////////////////////////////////////////////////
    // CONSTANTS
    /////////////////////////////////////////////////////////////////////////
    private static final int WIDTH_TOOLTIP = 60;
    private static final int HEIGHT_TOOLTIP = 20;
    private static final int HEIGHT_WINDOW_TITLE_BAR = 20;

    /////////////////////////////////////////////////////////////////////////
    // INSTANCE VARIABLES
    /////////////////////////////////////////////////////////////////////////
    /**
     * PrintData to display
     */
    private PrintData pd;

    /**
     * Flag to show the grid or not
     */
    private boolean showGrid;

    /**
     * GUI Frame
     */
    private JFrame parent;
    private JWindow splash;
    private JTextField tf;

    /////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR(S)
    /////////////////////////////////////////////////////////////////////////
    public PrintPreviewPanel(PrintData pd, boolean showGrid, JFrame parent) {
        this.pd = pd;
        this.showGrid = showGrid;

        if (showGrid) {
            addMouseMotionListener(this);
            this.parent = parent;
            tf = new JTextField();
            tf.setEditable(false);
            tf.setBackground(Color.yellow);
            splash = new JWindow();
            splash.setSize(WIDTH_TOOLTIP, HEIGHT_TOOLTIP);
            splash.getContentPane().add(tf);
            splash.setBackground(Color.yellow);
            setTooltipLocation();
            splash.setVisible(true);
            splash.toFront();
        }
    }

    /////////////////////////////////////////////////////////////////////////
    // METHOD(S)
    /////////////////////////////////////////////////////////////////////////
    /**
     * Override the paint method
     */
    public void paint(Graphics g) {
        /**
         * Draw a grid
         */
        if (showGrid) {
            Color gridColorMajor = new Color(170, 170, 170);
            Color gridColorMinor = new Color(220, 220, 220);
            Color gridColor;
            for (int y = 0; y < 1000; y += 10) {
                gridColor = (y % 100 == 0) ? gridColorMajor : gridColorMinor;
                g.setColor(gridColor);
                g.drawLine(0, y, 1000, y);
            }
            for (int x = 0; x < 1000; x += 10) {
                gridColor = (x % 100 == 0) ? gridColorMajor : gridColorMinor;
                g.setColor(gridColor);
                g.drawLine(x, 0, x, 1000);
            }
        }

        // Set the background color black
        g.setColor(Color.black);

        /**
         * Draw PrintLayout
         */
        PrintLayout pl = pd.getPrintLayout();
        ArrayList lineElements = pl.getElementLines();
        ArrayList labelElements = pl.getElementLabels();

        ElementLine eleline = null;
        int lineSize = lineElements.size();
        for (int i = 0; i < lineSize; i++) {
            eleline = (ElementLine) lineElements.get(i);
            g.drawLine(eleline.getX1(), eleline.getY1(), eleline.getX2(), eleline.getY2());
        }

        ElementLabel elelabel = null;
        int lableSize = labelElements.size();
        for (int i = 0; i < lableSize; i++) {
            elelabel = (ElementLabel) labelElements.get(i);
            g.drawString(elelabel.getLabel(), elelabel.getX(), elelabel.getY());
        }

        /**
         * Draw PrintValues
         */
        PrintValues pv = pd.getPrintValues();
        ArrayList valueElements = pv.getElementValues();

        ElementValue elevalue = null;
        int valueSize = valueElements.size();
        for (int i = 0; i < valueSize; i++) {
            elevalue = (ElementValue) valueElements.get(i);
            g.drawString(elevalue.getValue(), elevalue.getX(), elevalue.getY());
        }
    }

    /**
     * Override the update method
     */
    public void update(Graphics g) {
        repaint();
    }

    /**
     * Sets the Tool Tip Location at diagonal end-points
     */
    private void setTooltipLocation() {
        Point loc = parent.getLocation();
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int w = parent.getWidth();
        int h = parent.getHeight();

        int x1 = x + w - WIDTH_TOOLTIP;
        int y1 = y + h - HEIGHT_TOOLTIP;
        splash.setLocation(x1, y1);
    }

    /**
     * Implement MouseMotionListener.mouseDragged()
     */
    public void mouseDragged(java.awt.event.MouseEvent mouseEvent) {
    }

    /**
     * Implement MouseMotionListener.mouseMoved()
     */
    public void mouseMoved(java.awt.event.MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        tf.setText(x + "," + y);
        setTooltipLocation();
        splash.toFront();
        repaint();
    }

    /**
     * Set the position of the window. Get the size of the parent and the child
     * and set the child to the center of the parent. It will also check if the
     * parent window is partly display in the screen then it will display child
     * window at appropiate place.
     *
     * @param parent parent window on which child will be set at its center
     * @param child child window
     */
    public static void centerWindow(java.awt.Window parent, java.awt.Window child) {
        // If the parent window is null then center the child window wrt
        // the desktop screen size.
        if (parent == null) {
            Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle frameDim = child.getBounds();
            child.setLocation((screenDim.width - frameDim.width) / 2,
                    (screenDim.height - frameDim.height) / 2);
            return;
        }

        //
        // Center the child window wrt the parent window
        //
        Dimension parentDim = parent.getSize();
        Dimension childDim = child.getSize();

        // Get the coordinate for centering (This is relative to each other)
        int x = (parentDim.width - childDim.width) / 2;
        int y = (parentDim.height - childDim.height) / 2;

        // Now translate the coordinate w.r.t. the screen
        Point p = parent.getLocation();
        p.translate(x, y);

        // Adjust the top-left corner point of the dialog for any negative
        // values as otherwise the dialog may be chopped-off at left and/or
        // top of the screen.
        p.x = (p.x > 0) ? p.x : 0;
        p.y = (p.y > 0) ? p.y : 0;

        // Adjust the top-left corner point for values for which
        // the dialog crosses the right and/or bottom of the screen.
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        p.x = ((p.x + childDim.width) < screen.width)
                ? p.x
                : (screen.width - childDim.width);
        p.y = ((p.y + childDim.height) < screen.height)
                ? p.y
                : (screen.height - childDim.height);
        // NOTE: The above code may not work properly in the case when the
        // dialog size is bigger than the screen size. Should avoid that.
        // Set the location of the child  
        child.setLocation(p);
    }

    /**
     * Center the window wrt the desktop screen.
     *
     * @param window Window reference
     */
    public static void centerWindow(java.awt.Window window) {
        centerWindow(null, window);
    }

    /**
     * test
     */
    public static void test(PrintData pd, boolean showGrid) throws PrintDataException {
        JFrame f = new JFrame("Print Layout Config Preview");
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

        // Create PrintPreviewPanel Object and add it to the Frame
        PrintPreviewPanel ppp = new PrintPreviewPanel(pd, showGrid, f);
        f.getContentPane().add(ppp, BorderLayout.CENTER);
        f.setSize(800, 600);
        centerWindow(f);
        f.setVisible(true);
    }
}

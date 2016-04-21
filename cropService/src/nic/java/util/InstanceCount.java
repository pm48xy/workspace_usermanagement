package nic.java.util;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : InstanceCount
// PURPOSE          : Keeps count of the live objects in the JVM
//                    and total number of objects created so far
//                    during the life of the JVM running. 
// NOTES            : None
// LAST MODIFIED    :
//  20030919 GUM019 Package re-structuring
//  20030811 AKS013 Auditing - TCC : Possible Errors
//  20030716 AKS009 Auditing - TCC : Critical Errors
//  20030702 JIS013 Implemented Serializable interface in class Count
//  20030519 RCN007 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.util.*;
import java.io.*;

/**
 * Provides Instance Count at any stage. Keeps count of the objects live in the
 * JVM and total number of objects created so far during the life of the JVM
 * running. This is essentially used for debugging. A class wanting to know its
 * instance count should call "InstanceCount.add(this)" in all the constructors
 * and InstanceCount.remove(this)" in the finalize() method.
 *
 * @author R.C.Nougain
 */
public abstract class InstanceCount implements Serializable {

    ////////////////////////////////////////////////////////////////////////
    // CONSTANTS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Debugging on/off variable DEBUG is set separately by client and server in
     * ConfigClient and ConfigServer.java. This variable DO_COUNTING is set
     * using the DEBUG varibale from ConfigClient and ConfigServer.java on
     * client and server side respectively.
     *
     * Why we have done this : This is done to manage the instance count on/off
     * on the client and server side.
     */
    public static boolean DO_COUNTING = false;

    ////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ////////////////////////////////////////////////////////////////////////
    /**
     * List of Count
     */
    private static ArrayList counts = new ArrayList();

    /**
     * Class represeting count for a class name
     */
    private static class Count implements Serializable {

        private String className;
        private int countAlive;
        private int countTotal;

        public Count(String cName) {
            className = cName;
            countAlive = 1;
            countTotal = 1;
        }

        public void increase() {
            countAlive++;
            countTotal++;
        }

        public void decrease() {
            countAlive--;
        }

        public String toString() {
            return FormatUtils.getRightAligned(countAlive, 4) + " "
                    + FormatUtils.getRightAligned(countTotal, 4) + " "
                    + className;
        }
    }

    ////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR(S)
    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    // METHODS
    ////////////////////////////////////////////////////////////////////////
    /**
     * Increment the instance count.
     *
     * @param object Object for which the count need to manage
     */
    public static void add(Object object) {
        process(object, true);
    }

    /**
     * Decrement the instance count.
     *
     * @param object Object for which the count need to manage
     */
    public static void remove(Object object) {
        process(object, false);
    }

    /**
     * Process the count request
     *
     * @param object Object for which the count need to manage
     * @param addFlag True if the given object is created false if garbage
     * collected
     */
    private static void process(Object object, boolean addFlag) {
        // .......................................
        // If the count is made off do not proceed
        // .......................................
        if (!DO_COUNTING) {
            return;
        }

        // Get the class name
        String className = object.getClass().getName();

        // Get the size
        int size = counts.size();
        Count count = null;

        // Add or remove the object count from the list
        for (int i = 0; i < size; i++) {
            count = (Count) counts.get(i);
            if (className.equals(count.className)) {
                // Increase/Decrease the count
                if (addFlag) {
                    count.increase();
                } else {
                    count.decrease();
                }

                // Return as either add or remove is done
                return;
            }
        }

        // Control reaches here means there is no entry in the list for 
        // this class name
        if (addFlag) {
            counts.add(new Count(className));
        } else {
            // THIS SHOULD NEVER HAPPEN
            Debug.log(Debug.ERR + "Control should never come here : InstanceCount.process()."
                    + " No problem proceeding!");
        }
    }

    /**
     * Return a copy of instance count list
     *
     * @return copy of instance count list
     */
    public static ArrayList getCountsClone() {
        return (ArrayList) counts.clone();
    }

    /**
     * A utility method to generate a report for the instance count list in
     * given ArrayList
     *
     * @param countList Given ArrayList of "Count" objects
     *
     * @return Report in String format
     */
    public static String makeDump(ArrayList countList) {
        // Get the size
        int size = countList.size();
        StringBuffer sbuf = new StringBuffer();
        Count count = null;

        // Add or remove the object count from the list
        for (int i = 0; i < size; i++) {
            count = (Count) countList.get(i);
            sbuf.append(count);
            if (i != size - 1) {
                sbuf.append(CommonUtils.NEW_LINE);
            }
        }

        return sbuf.toString();
    }

    /**
     * Debug method
     */
    public static String dump() {
        return makeDump(counts);
    }
}

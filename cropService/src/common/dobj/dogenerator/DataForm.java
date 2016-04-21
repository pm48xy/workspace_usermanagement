package common.dobj.dogenerator;
//////////////////////////////////////////////////////////////////////////////
// CLASS            : Data Form 
// PURPOSE          : Represents a Form data
// METHOD           : See code
// RESTRICTIONS     : None
// NOTES            : None
// LAST MODIFIED    :
//  20030811 AKS013 Auditing - TCC : Possible Errors
//  20030805 AKS012 Auditing - TCC : Performance Category
//  20030804 SIM005 Added Copyright Documentation
//  20030513 GUM002 DataFieldException changed to DataFieldException
//  20030506 RCN005 Created
//////////////////////////////////////////////////////////////////////////////
// Copyright 2003 National Informatics Centre, NIC. http://www.nic.in
// All Rights Reserved.
//////////////////////////////////////////////////////////////////////////////
//
// Importing standard java packages/classes
//

import java.util.*;

/**
 * Represents a Form data.
 *
 * @author RCN
 */
public class DataForm {

    ///////////////////////////////////////////////////////////////////////
    // INSTANCE VARIABLES 
    ///////////////////////////////////////////////////////////////////////
    /**
     * Form name
     */
    private String name;

    /**
     * Package Name
     */
    private String pkg;

    /**
     * Form description
     */
    private String description;

    /**
     * List of DataFields
     */
    private ArrayList dataFields = new ArrayList();

    ///////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////
    /**
     * Constructor.
     *
     * @param description is the description of the pkg
     * @param pkg is the pkg name
     * @param name is the name of the form
     */
    public DataForm(String name, String pkg, String description) {
        this.name = name;
        this.pkg = pkg;
        this.description = description;
    }

    ///////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////
    /**
     * Adds a Data Field
     */
    public DataField addDataField(String name, String dbtype, String dv,
            String ll, String ul,
            String tt, String desc,
            String formNullable, String tableName)
            throws DataFieldException {

        DataField field = new DataField(name, dbtype, dv,
                ll, ul,
                tt, desc,
                formNullable, tableName);
        dataFields.add(field);

        return field;
    }

    /**
     * Get the Data Field list.
     */
    public DataField[] getDataFields() {
        int size = dataFields.size();
        if (size == 0) {
            return null;
        }

        DataField[] fields = new DataField[size];
        for (int i = 0; i < size; i++) {
            fields[i] = (DataField) dataFields.get(i);
        }

        return fields;
    }

    ///////////////////////////////////////////////////////////////////////
    // GETTERS
    ///////////////////////////////////////////////////////////////////////    
    public String getName() {
        return name;
    }

    public String getPkg() {
        return pkg;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Dump method
     */
    public String dump() {
        StringBuffer data = new StringBuffer(""
                + "--------------------------------------------------------"
                + DataField.NEW_LINE
                + " name = " + name
                + " package = " + pkg
                + " description = " + description
                + DataField.NEW_LINE
                + "--------------------------------------------------------"
                + DataField.NEW_LINE
                + DataField.dumpStructure()
                + DataField.NEW_LINE);

        int size = dataFields.size();
        if (size == 0) {
            data.append("ERROR : No DataFields in the Form. This is not allowed."
                    + DataField.NEW_LINE);
        } else {
            DataField df = null;
            for (int i = 0; i < size; i++) {
                df = (DataField) dataFields.get(i);
                data.append(df.dump() + DataField.NEW_LINE);
            }
        }

        return data.toString();
    }
}

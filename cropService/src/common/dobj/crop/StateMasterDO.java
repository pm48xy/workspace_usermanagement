/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.dobj.crop;

import common.dobj.DO;

/**
 *
 * @author ambrish
 */
public class StateMasterDO extends DO {

    private String stateUT;
    private String stateName;

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String dump() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "StateMasterDO{" + "stateUT=" + stateUT + ", stateName=" + stateName + '}';
    }

    /**
     * @return the stateUT
     */
    public String getStateUT() {
        return stateUT;
    }

    /**
     * @param stateUT the stateUT to set
     */
    public void setStateUT(String stateUT) {
        this.stateUT = stateUT;
    }

    /**
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param stateName the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}

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
public class DistrictMasterDO extends DO {

    private String stateUT;
    private String distCd;
    private String distName;

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String dump() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
     * @return the distCd
     */
    public String getDistCd() {
        return distCd;
    }

    /**
     * @param distCd the distCd to set
     */
    public void setDistCd(String distCd) {
        this.distCd = distCd;
    }

    /**
     * @return the distName
     */
    public String getDistName() {
        return distName;
    }

    /**
     * @param distName the distName to set
     */
    public void setDistName(String distName) {
        this.distName = distName;
    }

}

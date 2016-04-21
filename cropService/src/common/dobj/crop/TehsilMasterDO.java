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
public class TehsilMasterDO extends DO {

    private String stateUT;
    private String distCd;
    private String tehsCd;
    private String tehsName;

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
     * @return the tehsCd
     */
    public String getTehsCd() {
        return tehsCd;
    }

    /**
     * @param tehsCd the tehsCd to set
     */
    public void setTehsCd(String tehsCd) {
        this.tehsCd = tehsCd;
    }

    /**
     * @return the tehsName
     */
    public String getTehsName() {
        return tehsName;
    }

    /**
     * @param tehsName the tehsName to set
     */
    public void setTehsName(String tehsName) {
        this.tehsName = tehsName;
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String dump() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

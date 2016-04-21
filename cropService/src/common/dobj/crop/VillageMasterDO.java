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
public class VillageMasterDO extends DO {

    private String stateUT;
    private String distCd;
    private String tehsCd;
    private String villageCd;
    private String villageName;

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
     * @return the villageCd
     */
    public String getVillageCd() {
        return villageCd;
    }

    /**
     * @param villageCd the villageCd to set
     */
    public void setVillageCd(String villageCd) {
        this.villageCd = villageCd;
    }

    /**
     * @return the villageName
     */
    public String getVillageName() {
        return villageName;
    }

    /**
     * @param villageName the villageName to set
     */
    public void setVillageName(String villageName) {
        this.villageName = villageName;
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

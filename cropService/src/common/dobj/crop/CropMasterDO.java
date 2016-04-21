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
public class CropMasterDO extends DO {

    private String cropCd;
    private String cropName;

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String dump() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the cropCd
     */
    public String getCropCd() {
        return cropCd;
    }

    /**
     * @param cropCd the cropCd to set
     */
    public void setCropCd(String cropCd) {
        this.cropCd = cropCd;
    }

    /**
     * @return the cropName
     */
    public String getCropName() {
        return cropName;
    }

    /**
     * @param cropName the cropName to set
     */
    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

}

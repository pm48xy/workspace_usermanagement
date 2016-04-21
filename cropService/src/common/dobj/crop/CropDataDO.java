/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.dobj.crop;

import common.dobj.DO;
import org.apache.logging.log4j.util.Strings;

/**
 *
 * @author ambrish
 */
public class CropDataDO extends DO {

    private String stateUT;
    private String distCd;
    private String tehsCd;
    private String villCd;
    private int srNo;
    private int cropSrNo;
    private String cropCd;
    private double areaIrrigated;
    private double areaUnIrrigated;

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
     * @return the villCd
     */
    public String getVillCd() {
        return villCd;
    }

    /**
     * @param villCd the villCd to set
     */
    public void setVillCd(String villCd) {
        this.villCd = villCd;
    }

    /**
     * @return the srNo
     */
    public int getSrNo() {
        return srNo;
    }

    /**
     * @param srNo the srNo to set
     */
    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    /**
     * @return the areaIrrigated
     */
    public double getAreaIrrigated() {
        return areaIrrigated;
    }

    /**
     * @param areaIrrigated the areaIrrigated to set
     */
    public void setAreaIrrigated(double areaIrrigated) {
        this.areaIrrigated = areaIrrigated;
    }

    @Override
    public void reset() {
        setStateUT(Strings.EMPTY);
        setDistCd(Strings.EMPTY);
        setTehsCd(Strings.EMPTY);
        setVillCd(Strings.EMPTY);
        setCropCd(Strings.EMPTY);
        setSrNo(0);
        setCropSrNo(0);
        setAreaIrrigated(0.0);
        setAreaUnIrrigated(0.0);
    }

    @Override
    public String dump() {
        return toString();
    }

    /**
     * @return the cropSrNo
     */
    public int getCropSrNo() {
        return cropSrNo;
    }

    /**
     * @param cropSrNo the cropSrNo to set
     */
    public void setCropSrNo(int cropSrNo) {
        this.cropSrNo = cropSrNo;
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
     * @return the areaUnIrrigated
     */
    public double getAreaUnIrrigated() {
        return areaUnIrrigated;
    }

    /**
     * @param areaUnIrrigated the areaUnIrrigated to set
     */
    public void setAreaUnIrrigated(double areaUnIrrigated) {
        this.areaUnIrrigated = areaUnIrrigated;
    }

    @Override
    public String toString() {
        return "CropDataDO{" + "stateUT=" + stateUT + ", distCd=" + distCd + ", tehsCd=" + tehsCd + ", villCd=" + villCd + ", srNo=" + srNo + ", cropSrNo=" + cropSrNo + ", cropCd=" + cropCd + ", areaIrrigated=" + areaIrrigated + ", areaUnIrrigated=" + areaUnIrrigated + '}';
    }

}

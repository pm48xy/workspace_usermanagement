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
public class HoldingDataDO extends DO {

    private String stateUT;
    private String distCd;
    private String tehsCd;
    private String villCd;
    private int srNo;
    private double areaSown;
    private double currentFallow;
    private double areaUncultivated;
    private double areaIrrigated;

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
     * @return the areaSown
     */
    public double getAreaSown() {
        return areaSown;
    }

    /**
     * @param areaSown the areaSown to set
     */
    public void setAreaSown(double areaSown) {
        this.areaSown = areaSown;
    }

    /**
     * @return the currentFallow
     */
    public double getCurrentFallow() {
        return currentFallow;
    }

    /**
     * @param currentFallow the currentFallow to set
     */
    public void setCurrentFallow(double currentFallow) {
        this.currentFallow = currentFallow;
    }

    /**
     * @return the areaUncultivated
     */
    public double getAreaUncultivated() {
        return areaUncultivated;
    }

    /**
     * @param areaUncultivated the areaUncultivated to set
     */
    public void setAreaUncultivated(double areaUncultivated) {
        this.areaUncultivated = areaUncultivated;
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
        stateUT = Strings.EMPTY;
        distCd = Strings.EMPTY;
        tehsCd = Strings.EMPTY;
        villCd = Strings.EMPTY;
        srNo = 0;
        areaSown = 0.0;
        currentFallow = 0.0;
        areaUncultivated = 0.0;
        areaIrrigated = 0.0;
    }

    @Override
    public String dump() {
        return toString();
    }

    @Override
    public String toString() {
        return "HoldingDataDO{" + "stateUT=" + stateUT + ", distCd=" + distCd + ", tehsCd=" + tehsCd + ", villCd=" + villCd + ", srNo=" + srNo + ", areaSown=" + areaSown + ", currentFallow=" + currentFallow + ", areaUncultivated=" + areaUncultivated + ", areaIrrigated=" + areaIrrigated + '}';
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.remoteimpl;

import common.ClientException;
import common.db.MasterTableDO;
import common.db.MasterTables;
import common.db.TableDO;
import common.dobj.crop.CropMasterDO;
import common.dobj.crop.DistrictMasterDO;
import common.dobj.crop.StateMasterDO;
import common.dobj.crop.TehsilMasterDO;
import common.dobj.crop.VillageMasterDO;
import common.remote.TableServer;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.RowSet;
import server.db.DBUtils;

/**
 *
 * @author ambrish
 */
public class TableServerImpl implements TableServer, Serializable {

    @Override
    public MasterTables getStoredMasterTables() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MasterTableDO getDynamicMasterTableDO(String tableName) throws RemoteException, ClientException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TableDO getDynamicTableDO(String tableName) throws RemoteException, ClientException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[][] getMasterTableList() throws RemoteException, ClientException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int execIUDSQL(String sql) throws RemoteException, ClientException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<StateMasterDO> loadStateMasterList() throws ClientException {
        ArrayList<StateMasterDO> list = new ArrayList<StateMasterDO>();
        try {
            String sqlQuery = "SELECT state_ut, state_name  FROM state_mas";
            RowSet rs = DBUtils.fetchDetachedRowSet(sqlQuery);
            while (rs.next()) {
                StateMasterDO dobj = new StateMasterDO();
                dobj.setStateUT(rs.getString("state_ut"));
                dobj.setStateName(rs.getString("state_name"));
                list.add(dobj);
            }

        } catch (SQLException se) {
        }
        return list;
    }

    @Override
    public ArrayList<DistrictMasterDO> loadDistrictMasterList() throws ClientException {
        ArrayList<DistrictMasterDO> list = new ArrayList<DistrictMasterDO>();
        try {
            String sqlQuery = "SELECT state_ut, dist_cd, dist_name FROM dist_mas";
            RowSet rs = DBUtils.fetchDetachedRowSet(sqlQuery);
            while (rs.next()) {
                DistrictMasterDO dobj = new DistrictMasterDO();
                dobj.setStateUT(rs.getString("state_ut"));
                dobj.setDistCd(rs.getString("dist_cd"));
                dobj.setDistName(rs.getString("dist_name"));
                list.add(dobj);
            }

        } catch (SQLException se) {
        }
        return list;
    }

    @Override
    public ArrayList<TehsilMasterDO> loadTehsilMasterList() throws ClientException {
        ArrayList<TehsilMasterDO> list = new ArrayList<TehsilMasterDO>();
        try {
            String sqlQuery = "SELECT stat_cd, dist_cd, tehs_cd, tehs_name  FROM tehs_mas";
            RowSet rs = DBUtils.fetchDetachedRowSet(sqlQuery);
            while (rs.next()) {
                TehsilMasterDO dobj = new TehsilMasterDO();
                dobj.setStateUT(rs.getString("stat_cd"));
                dobj.setDistCd(rs.getString("dist_cd"));
                dobj.setTehsCd(rs.getString("tehs_cd"));
                dobj.setTehsName(rs.getString("tehs_name"));
                list.add(dobj);
            }

        } catch (SQLException se) {
        }
        return list;
    }

    @Override
    public ArrayList<VillageMasterDO> loadVillageMasterList() throws ClientException {
        ArrayList<VillageMasterDO> list = new ArrayList<VillageMasterDO>();
        try {
            String sqlQuery = "SELECT state_ut, dist_cd, tehs_cd, vill_cd, vill_name  FROM vill_mas";
            RowSet rs = DBUtils.fetchDetachedRowSet(sqlQuery);
            while (rs.next()) {
                VillageMasterDO dobj = new VillageMasterDO();
                dobj.setStateUT(rs.getString("state_ut"));
                dobj.setDistCd(rs.getString("dist_cd"));
                dobj.setTehsCd(rs.getString("tehs_cd"));
                dobj.setVillageCd(rs.getString("vill_cd"));
                dobj.setVillageName(rs.getString("vill_name"));

                list.add(dobj);
            }

        } catch (SQLException se) {
        }
        return list;
    }

    @Override
    public ArrayList<CropMasterDO> loadCropMasterList() throws ClientException {
        ArrayList<CropMasterDO> list = new ArrayList<CropMasterDO>();
        try {
            String sqlQuery = "SELECT crop_cd, crop_name FROM crop_mas";
            RowSet rs = DBUtils.fetchDetachedRowSet(sqlQuery);
            while (rs.next()) {
                CropMasterDO dobj = new CropMasterDO();
                dobj.setCropCd(rs.getString("crop_cd"));
                dobj.setCropName(rs.getString("crop_name"));
                list.add(dobj);
            }

        } catch (SQLException se) {
        }
        return list;
    }

}

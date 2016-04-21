
import common.ClientException;
import common.FormData;
import common.ModuleServer;
import common.OperationIds;
import common.ServiceIds;
import common.context.ClientContext;
import common.dobj.crop.HoldingDataDO;
import common.remote.FormServer;
import common.security.Encryption;
import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.RowSet;
import server.db.DBUtils;
import server.db.connection.TransactionManagerLocal;
import server.remoteimpl.FormServerImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ambrish
 */
public class TestMail {

    public static void main(String[] args) throws SQLException, ClientException, RemoteException {
        RowSet rs = DBUtils.fetchDetachedRowSet("select * from state_mas");
        while (rs.next()) {
            System.out.println(rs.getString(1) + "   " + rs.getString(2));
        }

        ClientContext ctx = ModuleServer.authServer().authenticateUser("ambrish", "eee8f9345bd912981ccbef1bb3222d7c", "lw8uT", null);

        System.out.println(ctx.toString());
        HoldingDataDO dobj = new HoldingDataDO();
        dobj.setAreaIrrigated(0.5);
        dobj.setAreaSown(0.3);
        dobj.setAreaUncultivated(0.1);
        dobj.setCurrentFallow(0.2);

        dobj.setSrNo(2);
        dobj.setStateUT("07");
        dobj.setDistCd("02");
        dobj.setTehsCd("02");
        dobj.setVillCd("00000014");
        FormServer formServer = new FormServerImpl();
        FormData formData = new FormData(ctx, dobj, ServiceIds.DATA_ENTRY, OperationIds.P_HOLDING_DATA);
        formServer.process(formData);
        //"07";"01";"01";"00000014"
    }
}

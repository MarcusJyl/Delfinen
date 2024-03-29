package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBBetalingStatusSpeci {

        public static boolean getMedlemsKontingent(int id) {
        boolean retVal = false;
        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "select medlems_kontingent_status from medlemmer where medlems_id = '" + id + "';";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                retVal = resultSet.getBoolean("medlems_kontingent_status");
            
            }

            //lukker
            resultSet.close();;
            statement.close();
            MyConnector.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return retVal;
    }
}

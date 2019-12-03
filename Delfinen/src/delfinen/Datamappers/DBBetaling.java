package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBBetaling extends DBCalls {

    public DBBetaling() {
        super();
    }

    public boolean getBelingsoversigt() {
        boolean retVal = false;
        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "SELECT medlems_id, medlems_navn, medlems_kontingent_status FROM delfinen.medlemmer;";
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

    @Override
    public void insert(Object... val) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import delfinen.Util.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBBetaling extends DBCalls {

    public DBBetaling() {
        super();
    }

    public  ArrayList<Item> getBetalingsoversigt() {
        ArrayList<Item> retVal = new ArrayList();
        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "SELECT medlems_id, medlems_navn, medlems_kontingent_status FROM delfinen.medlemmer;";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                boolean status = resultSet.getBoolean("medlems_kontingent_status");
                String navn = resultSet.getString("medlems_navn");
                Item tmpItem = new Item(navn, status);
                retVal.add(tmpItem);
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


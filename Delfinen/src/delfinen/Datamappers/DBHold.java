package delfinen.Datamappers;

import delfinen.Model.Medlem;
import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBHold extends DBCalls {

    public DBHold(String idName, String tableName) {
        super(idName, tableName);
    }

    @Override
    //int id, int medlemsId, int StævneId
    public void insert(Object... val) {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            String query = "insert into hold values (" + val[0] + "," + val[1] + "," + val[2] + ");";
            statement = MyConnector.createStatement();
            statement.executeUpdate(query);

            statement.close();
            MyConnector.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public ArrayList<Integer> getMedlemmer(int holdId) {
        ArrayList<Integer> medlemsIder = new ArrayList();

        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "SELECT medlems_id FROM delfinen.hold where hold_id = " + holdId + ";";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            //int id, String navn, String fødselsdato, String holdtype
            while (resultSet.next()) {
                medlemsIder.add(resultSet.getInt("medlems_id"));
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

        return medlemsIder;
    }

    public ArrayList<Integer> getHold() {
        ArrayList<Integer> stævneIder = new ArrayList();

        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "SELECT DISTINCT stævne_id FROM delfinen.hold;";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            //int id, String navn, String fødselsdato, String holdtype
            while (resultSet.next()) {
                stævneIder.add(resultSet.getInt("stævne_id"));
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

        return stævneIder;
    }

}

package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBStævne extends DBCalls {

    public DBStævne(String idName, String tableName) {
        super(idName, tableName);
    }

    //int id, String navn, String dato, 
    public void insert(Object... val) {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            String query = "insert into stævne values (null,'" + val[0] + "','" + val[1] + "');";
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

    public String getStævne(int id) {
        String retVal = "";

        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "SELECT * FROM delfinen.stævne where stævne_id = " + id + ";";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            //int id, String navn, String fødselsdato, String holdtype
            while (resultSet.next()) {
                String navn = resultSet.getString("stævne_navn");
                String dato = resultSet.getString("dato");
                retVal = id + ". " + "Stævne: " + navn + " dato: " + dato;
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

package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTræning {

    public static void insert(String træningDato, boolean seniorTræning, boolean konkurrenceTræning) {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            String query = "insert into træning values (null,'" + træningDato + "'," + seniorTræning + "," + konkurrenceTræning + ");";
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
    
        public static int getNyesteTræningsId() {
        int retVal = 0;
        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "select MAX(trænings_id) from træning;";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                retVal = resultSet.getInt("MAX(trænings_id)");
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

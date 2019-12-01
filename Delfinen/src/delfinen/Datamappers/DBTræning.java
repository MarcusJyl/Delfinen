package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTræning extends DBCalls {

    public DBTræning(String idName, String tableName) {
        super(idName, tableName);
    }

    @Override
    //String træningDato, boolean seniorTræning, boolean konkurrenceTræning
    public void insert(Object... val) {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            String query = "insert into træning values (null,'" + val[0] + "'," + val[1] + "," + val[2] + ");";
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

//        public static int getNyesteTræningsId() {
//        int retVal = 0;
//        Connection MyConnector = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            MyConnector = DBConnector.getConnector();
//            String query = "select MAX(trænings_id) from træning;";
//            statement = MyConnector.createStatement();
//            resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                retVal = resultSet.getInt("MAX(trænings_id)");
//            }
//
//            //lukker
//            resultSet.close();;
//            statement.close();
//            MyConnector.close();
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        } catch (ClassNotFoundException ex) {
//            System.out.println(ex);
//        }
//        return retVal;
//    }
}

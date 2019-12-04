
package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


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
    
}

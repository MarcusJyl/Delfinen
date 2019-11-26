package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LavMedlem {

    public static void insert() {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            String query = "insert into medlemmer values (null,'marc','2000-11-11','fuck af');";
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

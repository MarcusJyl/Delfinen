package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBStævne {
    
public static void insert(String stævne_navn, String dato) {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            String query = "insert into stævne values (null,'" + stævne_navn + "','" + dato + ");";
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

package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTræning {
    public static void insert(String træningDato, boolean seniorTræning,boolean konkurrenceTræning) {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            String query = "insert into træning values (null,'" + træningDato + "," + seniorTræning + ","+ konkurrenceTræning +");";
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
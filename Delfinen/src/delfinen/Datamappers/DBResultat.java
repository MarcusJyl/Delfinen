package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBResultat {

    public static void insertTræningsResultater(int type ,int id, String diciplin, String tid, String dato) {
        String query = "";
        
        if(type == 1){
            query = "insert into medlemmers_træningsresultat values (" + id + ",'" + diciplin + "','" + tid + "','" + dato + ");";
        } else if(type == 2){
            query = "insert into medlemmers_træningsresultat values (" + id + ",'" + diciplin + "','" + tid + "','" + dato + ");";
        }
        
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            
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

    public static void insertKonkurrence(int id, String stævne, String diciplin, String tid, String dato, int placering) {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            String query = "insert into medlemmers_træningsresultat values (" + id + ",'" + stævne + "','" + diciplin + "','" + tid + "','" + dato + "'," + placering + ");";
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

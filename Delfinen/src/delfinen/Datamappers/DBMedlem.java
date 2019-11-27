package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMedlem {

    public static void insert(String navn, String fødselsdag, String holdtype, String status, double kontingent) {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            String query = "insert into medlemmer values (null,'" + navn + "','" + fødselsdag + "','" + holdtype + "','" + status + "',"+ kontingent +");";
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

    public static void skiftHoldtype(String holdtype, int id) {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            String query = "update medlemmer set `medlems_holdtype` ='" + holdtype + "' where `medlems_id` = " + id + ";";
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

    public static int størsteMedlemsId() {
        int retVal = 0;
        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "select MAX(medlems_id) from medlemmer;";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                retVal = resultSet.getInt("MAX(medlems_id)");
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

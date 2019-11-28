package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class DBMedlem {

    public static void insert(String navn, String fødselsdag, String holdtype, String status, double kontingent) {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            String query = "insert into medlemmer values (null,'" + navn + "','" + fødselsdag + "','" + holdtype + "','" + status + "'," + kontingent + "," + false + ");";
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

    public static double getMedlemsKontingent(String navn, String dato) {
        double retVal = 0;
        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "select medlems_kontingent from medlemmer where medlems_navn = '" + navn + "' and medlems_fødselsdato = '" + dato + "';";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                retVal = resultSet.getDouble("medlems_kontingent");
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

    public static int getMedlemsId(String navn, String dato) {
        int retVal = 0;
        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "select medlems_id from medlemmer where medlems_navn = '" + navn + "' and medlems_fødselsdato = '" + dato + "';";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                retVal = resultSet.getInt("medlems_id");
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

    public static ArrayList<Integer> getalder(int id) {
        ArrayList<Integer> retVal = new ArrayList();

        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "select medlems_fødselsdato from medlemmer;";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String dato = resultSet.getString("medlems_fødselsdato");
                String[] datoArr = dato.split("-");
                int[] datoTal = new int[datoArr.length];
                
                for (int i = 0; i < datoTal.length; i++) {
                    datoTal[i] = Integer.parseInt(datoArr[i]);
                }
                
                LocalDate today = LocalDate.now();
                LocalDate birthday = LocalDate.of(datoTal[0], datoTal[1], datoTal[2]);
                Period p = Period.between(birthday, today);
                retVal.add(p.getYears());
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

package delfinen.Datamappers;

import delfinen.Model.Medlem;
import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class DBMedlem extends DBCalls {

    public DBMedlem(String idName, String tableName) {
        super(idName, tableName);
    }

    @Override
    public void insert(Object... val) {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();

            String query = "insert into medlemmer values (null,'" + val[0] + "','" + val[1] + "','" + val[2] + "','" + val[3] + "'," + val[4] + "," + false + ");";
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

    public int getMedlemsId(String navn, String dato) {
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

    public ArrayList<Medlem> getWhereAlderUnderEllerOver(String alder) {
        ArrayList<Medlem> retVal = new ArrayList();

        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "SELECT * FROM delfinen.medlemmer where medlems_fødselsdato " + alder + ";";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            //int id, String navn, String fødselsdato, String holdtype
            while (resultSet.next()) {
                int id = resultSet.getInt("medlems_id");
                String navn = resultSet.getString("medlems_navn");
                String dato = resultSet.getString("medlems_fødselsdato");
                String hold = resultSet.getString("medlems_holdtype");

                String[] datoArr = dato.split("-");
                int[] datoTal = new int[datoArr.length];
                for (int i = 0; i < datoTal.length; i++) {
                    datoTal[i] = Integer.parseInt(datoArr[i]);
                }

                Medlem tempMedlem = new Medlem(id, navn, dato, hold);
                retVal.add(tempMedlem);
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

    public ArrayList getAlleMedlemmerDerStarterMed(String navn) {
        ArrayList<Medlem> medlemmer = new ArrayList();

        ArrayList<Medlem> retVal = new ArrayList();

        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "SELECT * FROM medlemmer WHERE medlems_navn LIKE '" + navn + "%';";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            //int id, String navn, String fødselsdato, String holdtype
            while (resultSet.next()) {
                int id = resultSet.getInt("medlems_id");
                String medlemsNavn = resultSet.getString("medlems_navn");
                String dato = resultSet.getString("medlems_fødselsdato");
                String hold = resultSet.getString("medlems_holdtype");

                String[] datoArr = dato.split("-");
                int[] datoTal = new int[datoArr.length];
                for (int i = 0; i < datoTal.length; i++) {
                    datoTal[i] = Integer.parseInt(datoArr[i]);
                }

                Medlem tempMedlem = new Medlem(id, medlemsNavn, dato, hold);
                medlemmer.add(tempMedlem);
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
        return medlemmer;
    }
}

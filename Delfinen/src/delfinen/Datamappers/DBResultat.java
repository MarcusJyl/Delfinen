package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DBResultat extends DBCalls {

    @Override
    //int id, String tid, String dato, String diciplin
    public void insert(Object... val) {
        Connection MyConnector = null;
        Statement statement = null;
        try {
            MyConnector = DBConnector.getConnector();
            String query = "insert into resultater values (" + val[0] + ",'" + val[1] + "','" + val[2] + "','" + val[3] + "');";

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

    public static String[] select() {
        String retVal[] = new String[5];
        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;
        LocalDate date = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        String dato = date.toString();
        System.out.println(dato);

        try {
            MyConnector = DBConnector.getConnector();
            String query = "SELECT "
                    + "r.trænings_id, "
                    + "r.tid,"
                    + "t.trænings_id, "
                    + "t.træning_dato "
                    + "FROM delfinen.resultater r "
                    + "RIGHT JOIN træning t on r.trænings_id = t.trænings_id "
                    + "where r.tid is not null and træning_dato > '" + dato + "' "
                    + "order by r.tid;";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            int i = 0;
            while (resultSet.next() && retVal.length <= 5) {
                retVal[i] = resultSet.getString("r.tid");
                i++;
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

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

    public static String[][] select(String dici) {
        String retVal[][] = new String[5][2];
        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;
        LocalDate date = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        String dato = date.toString();

        try {
            MyConnector = DBConnector.getConnector();
            String query = "SELECT "
                    + "r.trænings_id, "
                    + "r.tid,"
                    + "t.trænings_id, "
                    + "t.træning_dato,"
                    + "m.medlems_navn "
                    + "FROM delfinen.resultater r "
                    + "RIGHT JOIN træning t on r.trænings_id = t.trænings_id "
                    + "RIGHT JOIN medlemmer m on r.medlems_id = r.medlems_id "
                    + "where r.tid is not null and træning_dato > '" + dato + "' and r.diciplin = '" + dici + "' "
                    + "order by r.tid;";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            int i = 0;
            while (resultSet.next() && i < 5) {
                retVal[i][0] = resultSet.getString("r.tid");
                retVal[i][1] = resultSet.getString("m.medlems_navn");
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

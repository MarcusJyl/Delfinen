package delfinen.Datamappers;

import delfinen.Model.Medlem;
import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import delfinen.Util.ScannerFunc;
import delfinen.Util.Item;

public class DBMedlemsOplysninger {

    public static Medlem medlemsOplysninger(int id) {
        Connection MyConnector = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            MyConnector = DBConnector.getConnector();
            String query = "SELECT * FROM medlemmer WHERE medlems_id = " + id + ";";
            statement = MyConnector.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String holdtype = resultSet.getString("medlems_holdtype");
                String status = resultSet.getString("medlems_status");
                int kontingent = resultSet.getInt("medlems_kontingent");
                boolean kontingentStatus = resultSet.getBoolean("medlems_kontingent_status");
                String navn = resultSet.getString("medlems_navn");
                String føds = resultSet.getString("medlems_fødselsdato");

                Medlem tmpMedlem = new Medlem(id, navn, føds, holdtype, status, kontingent, kontingentStatus);

                //lukker
                resultSet.close();;
                statement.close();
                MyConnector.close();

                return tmpMedlem;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return null;
    }
}

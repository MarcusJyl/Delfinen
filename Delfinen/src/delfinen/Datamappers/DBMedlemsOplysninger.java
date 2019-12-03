
package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import delfinen.Util.ScannerFunc;

public class DBMedlemsOplysninger {
        
    
    
    public static void medlemsOplysninger() {         
            Connection MyConnector = null;
            Statement statement = null;
            ResultSet resultSet = null;
            ScannerFunc sc = new ScannerFunc();
            
            
            String navn2 = sc.getUserString("Indtast medlems navn:");
            String fødselsdato2 = sc.getFøds("Indtast medlems fødselsdato i følgende format: dd-mm-yyyy");
            int id = DBMedlem.getMedlemsId(navn2, fødselsdato2);
            
            String holdtype = "";
            boolean status = false;
            int kontingent = 0;
            int kontingentStatus = 0;
            String statustmp = "";
            
            

            try {
                MyConnector = DBConnector.getConnector();
                String query = "SELECT medlems_holdtype, medlems_status, medlems_kontingent, medlems_kontingent_status FROM medlemmer WHERE medlems_id = " + id + ";";
                statement = MyConnector.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    holdtype = resultSet.getString("medlems_holdtype");
                    status = resultSet.getBoolean("medlems_status");
                    kontingent = resultSet.getInt("medlems_kontingent");
                    kontingentStatus = resultSet.getInt("medlems_kontingent_status");
                    
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
            if(status){
                statustmp = "Aktiv";
            } else {
                statustmp = "Passiv";
            }
            System.out.println(holdtype, statustmp, kontingent, kontingentStatus);
    }
}

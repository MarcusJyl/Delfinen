package delfinen.Datamappers;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBCalls {

    private String idName;
    private String tableName;

    public DBCalls(String idName, String tableName) {
        this.idName = "MAX(" + idName + ")";
        this.tableName = tableName;
    }
    public DBCalls() {
    }

    public abstract void insert(Object... val);

    public int størsteId() {
            int retVal = 0;
            Connection MyConnector = null;
            Statement statement = null;
            ResultSet resultSet = null;

            try {
                MyConnector = DBConnector.getConnector();
                String query = "select " + idName + " from " + tableName + ";";
                statement = MyConnector.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    retVal = resultSet.getInt(idName);
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

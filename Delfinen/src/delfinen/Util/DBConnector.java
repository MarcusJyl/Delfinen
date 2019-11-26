package delfinen.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import delfinen.Util.DBConfig;


public class DBConnector {

    public static Connection getConnector() throws SQLException, ClassNotFoundException {
        Connection connector = null;
        String url = "jdbc:mysql://localhost:3306/delfinen?"
                + "serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
        String user = DBConfig.username;
        String password = DBConfig.password;

        Class.forName("com.mysql.cj.jdbc.Driver");
        connector = DriverManager.getConnection(url, user, password);
        return connector;
    }

}

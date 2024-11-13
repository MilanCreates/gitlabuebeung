package dataacess;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabaseConnection {
    private  static Connection connection = null;



    private MyDatabaseConnection(){

    }

    public static Connection getConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException {
        if (connection!=null){
            return connection;
        }else {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user,pwd);
            return connection;
        }

    }

}

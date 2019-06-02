package club.eugeneliu.trade.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/qingsongdai?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false&useAffectedRows=true";
    private static final String USER = "root";
    private static final String PASSWORD = "liuyichen";

    private DatabaseConnection(){}

    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

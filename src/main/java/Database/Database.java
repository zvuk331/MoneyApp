package Database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    private String url;
    private String userName;
    private String userPassword;
    private static Database database;

    private Database(){
        try {
            url = "jdbc:mysql://localhost:3306/mysql";
            userName = "root@localhost";
            userPassword = "pasha331799";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url,userName,userPassword))
            {
                Statement statement = conn.createStatement();
                int rows = statement.executeUpdate("INSERT new_db.users(user_email, user_password) VALUES (\"testemail@gmail.com\", \"testpassword\")");
                System.out.println(rows);
            }
        } catch (Exception e){
            System.out.println("Connection error");
            e.printStackTrace();
        }

    }
    public static Database DATABASE_ON(){
        if (database == null){
            database = new Database();
        }
        return database;
    }
}

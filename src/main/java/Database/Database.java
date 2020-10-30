package Database;

import Account.Account;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/new_db";
    private static final String USERNAME = "root";
    private static final String USERPASSWORD = "pasha331799";
    private static Database database;
    private static Connection connection;
    private static PreparedStatement statement;
    static {
        try {
            connection = DriverManager.getConnection(URL,USERNAME,USERPASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Database(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
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

    // Write new account into database new_db, table users;
    public static void insertIntoDatabase(Account account){
        String email = account.getEmail();
        String password = account.getPassword();
        try {
            if (database.accountNotExist(account)){
                statement = connection.prepareStatement("INSERT INTO users (user_email,user_password) VALUES(?,?)");
                statement.setString(1,email);
                statement.setString(2,password);
                statement.execute();
            } else {
                System.out.println("Account exist");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Get all data account from database new_db, table users
    public static ArrayList<String> getAllDataAccount(Account account){
        ArrayList<String> array = new ArrayList<>();
        try {
            String email = account.getEmail();
            statement = connection.prepareStatement("SELECT * FROM users WHERE user_email=?");
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String id = Integer.toString(resultSet.getInt("users_id"));
                String userEmail = resultSet.getString("user_email");
                String userPassword = resultSet.getString("user_password");
                array.add(id);
                array.add(userEmail);
                array.add(userPassword);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return array;
    }

    // Get all data from database new_db, table users. Type : String
    public static void WRITEALLDATA(){
        try {
            statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("users_id");
                String email = resultSet.getString("user_email");
                String password = resultSet.getString("user_password");
                System.out.println("Id: " + id + " Email: " + email + " Password: " + password );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    // Checking for the existence of an account
    private boolean accountNotExist(Account account){
        try {
            statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                if (resultSet.getString("user_email").equals(account.getEmail())){
                    return false;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

}

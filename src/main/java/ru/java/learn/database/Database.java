package ru.java.learn.database;

import ru.java.learn.entity.User;
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
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(URL,USERNAME,USERPASSWORD);
//            statement = connection.prepareStatement("SELECT * FROM users");
        } catch (Exception throwables ) {
            throwables.printStackTrace();
        }
    }

    private Database(){

    }
    public static Database DATABASE_ON(){
        if (database == null){
            database = new Database();
        }
        return database;
    }

    /*private static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_db","root","pasha331799");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }*/


    // Write new account into database new_db, table users;
    public void addUserIntoDatabase(String email, String password){
        try {
                //Connection connection = getConnection();
            if (connection != null) {
                statement = connection.prepareStatement("INSERT INTO users (user_email,user_password) VALUES(?,?)");
                statement.setString(1, email);
                statement.setString(2, password);
                statement.executeQuery();
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Get all data account from database new_db, table users
    public ArrayList<String> getAllDataAccount(User user){
        ArrayList<String> array = new ArrayList<>();
        try {
            String email = user.getEmail();
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
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return array;
    }

    // Get all data from database new_db, table users. Type : String
    public void WRITEALLDATA(){
        try {
            connection = DriverManager.getConnection(URL,USERNAME,USERPASSWORD);
            statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("users_id");
                String email = resultSet.getString("user_email");
                String password = resultSet.getString("user_password");
                System.out.println("Id: " + id + " Email: " + email + " Password: " + password );
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    // Checking for the existence of an account
    public boolean userIsExist(String email, String password){
        try {
//            Connection connection = getConnection();
            statement = connection.prepareStatement("SELECT user_email AND user_password FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                if (resultSet.getString("user_email").equals(email)){
                    return true;
                }
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }



}

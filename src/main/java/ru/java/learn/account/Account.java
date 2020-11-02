package ru.java.learn.account;

import ru.java.learn.database.Database;

public class Account {
    private final String email;
    private String password;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
        Database.insertIntoDatabase(this);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package Account;

import Database.Database;
import Finance.Money;

public class User {
    private final String email;
    private String password;
    private Integer wallet;
    private Money currency;
    private Role role;

    public User(String email, String password) {
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

    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(Integer wallet) {
        this.wallet = wallet;
    }

    public Money getCurrency() {
        return currency;
    }

    public void setCurrency(Money currency) {
        this.currency = currency;
    }
}

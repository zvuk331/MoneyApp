package moneyhelper.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_user_finance")
public class UserFinance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double balance;

    @OneToMany(mappedBy = "finance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserIncome> incomes = new ArrayList<>();

    @OneToMany(mappedBy = "finance",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserCosts> costs = new ArrayList<>();

    @OneToOne(mappedBy = "finance")
    private User user;


    public UserFinance() {
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double summ) {
        this.balance = summ;
    }

    public List<UserIncome> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<UserIncome> incomes) {
        this.incomes = incomes;
    }

    public List<UserCosts> getCosts() {
        return costs;
    }

    public void setCosts(List<UserCosts> costs) {
        this.costs = costs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

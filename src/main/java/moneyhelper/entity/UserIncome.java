package moneyhelper.entity;


import javax.persistence.*;

@Entity
@Table(name = "t_user_income")
public class UserIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double value;

    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "finance_id")
    private UserFinance finance;

    public UserIncome() {

    }

    public long getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public UserFinance getFinance() {
        return finance;
    }

    public void setFinance(UserFinance finance) {
        this.finance = finance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package moneyhelper.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user_costs")
public class UserCosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double value;

    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "finance_id")
    private UserFinance finance;

    public UserCosts() {
    }

    public long getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public UserFinance getFinance() {
        return finance;
    }

    public void setValue(double value) {
        this.value = value;
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

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}

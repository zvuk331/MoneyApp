package moneyhelper.entity;


import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "t_user_income")
public class UserIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double value;

    private String type;

    @Temporal(TemporalType.DATE)
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    public String getDateAsString(Date date){
//        String pattern = "dd-M-yyyy hh:mm:ss";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        String dateAsString = simpleDateFormat.format(date);
//        return dateAsString;
//    }

}

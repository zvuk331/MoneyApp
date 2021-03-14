package moneyhelper.entity;

import javax.persistence.*;

@Entity
@Table(name = "types_of_income")
public class TypesOfIncome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    public TypesOfIncome() {
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}

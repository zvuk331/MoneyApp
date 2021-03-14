package moneyhelper.entity;

import javax.persistence.*;

@Entity
@Table(name = "types_of_costs")
public class TypesOfCosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    public TypesOfCosts() {

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

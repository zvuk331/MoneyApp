package ru.java.learn.entity;


import javax.persistence.*;
import java.util.Set;

@Entity

public class Role{
    @Id
    private Long Id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Role(Long id) {
        Id = id;
    }

    public Role(Long id, String name) {
        Id = id;
        this.name = name;
    }


}

package ru.java.learn.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "table_role")
public class Role implements GrantedAuthority {
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

    @Override
    public String getAuthority() {
        return null;
    }
}

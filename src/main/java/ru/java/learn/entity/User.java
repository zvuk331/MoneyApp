package ru.java.learn.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Email cannot be empty!")
    @Min(value = 10, message = "Email must be more 6 characters")
    private String email;
    /*, message = */

    @NotNull(message = "Password cannot be empty!")
    @Min(value = 6, message = "Password must be more 6 characters")
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "t_user_role",joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    // For example
    /*@Column(name = "password", nullable = false)
    @Size(min = 5, message = "not less 5 characters")*/








}


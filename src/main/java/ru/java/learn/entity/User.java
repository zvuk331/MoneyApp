package ru.java.learn.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @NotNull(message = "Почта не может быть пустой!")
    @Size(min = 10, message = "Почта должна быть не меньше 10 символов.")
    private String email;

    @NotNull(message = "Пароль не может быть пустым!")
    @Size(min = 6, message = "Пароль должен быть не меньше 6 символов")
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


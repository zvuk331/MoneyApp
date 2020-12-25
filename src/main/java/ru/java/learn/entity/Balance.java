package ru.java.learn.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_balance")
public class Balance {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;


    private Double income;

    private Double spending;
}

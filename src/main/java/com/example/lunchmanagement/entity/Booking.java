package com.example.lunchmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime orderedTime;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Menu menu;

    public Booking(){
        orderedTime=LocalDateTime.now();
    }
}

package com.example.lunchmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class LunchAttendance {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private boolean attending;
    @ManyToOne
    private Employee employee;

}

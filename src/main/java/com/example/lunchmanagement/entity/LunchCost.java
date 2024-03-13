package com.example.lunchmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class LunchCost {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int employeeCost;
    private int CompanyCost;
    private int totalCost;

    @OneToMany(mappedBy = "lunchCost")
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee){
        employee.setLunchCost(this);
        employees.add(employee);
    }

}

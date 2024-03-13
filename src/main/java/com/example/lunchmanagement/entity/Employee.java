package com.example.lunchmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;
    private String department;
    private String email;
    private int employeeCost;
    private int companyCost;
    private int totalCostOfEmployee;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles =new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<LunchAttendance> lunchAttendances = new ArrayList<>();

    public void addMenu(Menu menu){
        menus.add(menu);
    }
    public void addLunchAttendances(LunchAttendance lunchAttendance){
        lunchAttendance.setEmployee(this);
        lunchAttendances.add(lunchAttendance);
    }

    public void addRole(Role role) {
        role.getEmployees().add(this);
        this.roles.add(role);
    }

    public Employee(String userName, String password, String department, String email) {
        this.userName = userName;
        this.password = password;
        this.department = department;
        this.email = email;
    }
}

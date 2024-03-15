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
    private int companyCost;
    private int totalSalaryDeduction;
    private int totalCost;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles =new HashSet<>();

    @OneToMany(mappedBy = "employee")
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Booking> bookings = new ArrayList<>();

    public void addRole(Role role) {
        role.getEmployees().add(this);
        this.roles.add(role);
    }

    public void addMenu(Menu menu){
        menu.setEmployee(this);
        menus.add(menu);
    }

    public void addBooking(Booking booking){
        booking.setEmployee(this);
        bookings.add(booking);
    }

    public Employee(String userName, String password, String department, String email) {
        this.userName = userName;
        this.password = password;
        this.department = department;
        this.email = email;
    }
}

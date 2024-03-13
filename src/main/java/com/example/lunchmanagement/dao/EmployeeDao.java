package com.example.lunchmanagement.dao;

import com.example.lunchmanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {

    Employee findEmployeeByUserName(String customerName);

}

package com.example.lunchmanagement.service;

import com.example.lunchmanagement.dao.EmployeeDao;
import com.example.lunchmanagement.dao.RoleDao;
import com.example.lunchmanagement.entity.Employee;
import com.example.lunchmanagement.entity.Role;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeDao employeeDao;
    private final RoleDao roleDao;


    @Transactional
    public void register(Employee employee){
        Role role = roleDao.findRoleByRoleName("ROLE_USER")
                .orElseThrow(EntityNotFoundException::new);

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        employee.addRole(role);
        employeeDao.save(employee);
    }

    public boolean isLoggedIn() {
        // Access SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();

        // Check for non-null authentication object and authentication status
        return context.getAuthentication() != null && context.getAuthentication().isAuthenticated();
    }


}

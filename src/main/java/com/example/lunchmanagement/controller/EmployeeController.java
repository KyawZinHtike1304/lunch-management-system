package com.example.lunchmanagement.controller;

import com.example.lunchmanagement.dao.EmployeeDao;
import com.example.lunchmanagement.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeDao employeeDao;

    @GetMapping("/profile")
    public String info(Authentication authentication, Model model){
        String employeeName = authentication.getName();
        Employee employee = employeeDao.findEmployeeByUserName(employeeName);
        model.addAttribute("employee",employee);

        return "profile";
    }
}

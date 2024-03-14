package com.example.lunchmanagement.controller;

import com.example.lunchmanagement.dao.EmployeeDao;
import com.example.lunchmanagement.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/management")
public class ManagementController {

    private final EmployeeDao employeeDao;

    @GetMapping("/info")
    private String info(Model model){
        model.addAttribute("employees",employeeDao.findAll());
        return "info";
    }

    @GetMapping("/details")
    private String details(@RequestParam("empId")int empId,Model model){
        Employee employee = employeeDao.findEmployeeById(empId);
        model.addAttribute("employee",employee);

        return "infoDetails";
    }
}

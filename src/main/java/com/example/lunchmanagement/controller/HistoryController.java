package com.example.lunchmanagement.controller;

import com.example.lunchmanagement.dao.EmployeeDao;
import com.example.lunchmanagement.dao.MenuDao;
import com.example.lunchmanagement.entity.Employee;
import com.example.lunchmanagement.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class HistoryController {

    private final EmployeeDao employeeDao;
    private final MenuDao menuDao;

    @GetMapping("/history")
    public String history(Authentication authentication,Model model){
        String employeeName = authentication.getName();
        Employee employee = employeeDao.findEmployeeByUserName(employeeName);
        model.addAttribute("employee",employee);
        return "history";
    }

    @GetMapping("/management")
    private String management(Model model){
        model.addAttribute("employees",employeeDao.findAll());
        return "management";
    }

    @GetMapping("/add-history")
    public String addHistory(@RequestParam("menuId")int menuId,
                               @RequestParam("price")int price, Model model,
                             Authentication authentication){

        double percentage = 0.5;
        String employeeName = authentication.getName();

        Employee employee = employeeDao.findEmployeeByUserName(employeeName);
        int employeeCost = employee.getEmployeeCost ()+(int) (price * 0.5);
        int companyCost =employee.getCompanyCost() + (int) (price * 0.5);
        int totalCost = employee.getTotalCostOfEmployee() + employeeCost;

        employee.setEmployeeCost(employeeCost);
        employee.setCompanyCost(companyCost);
        employee.setTotalCostOfEmployee(totalCost);

        Menu menu = menuDao.findMenuById(menuId);
        menu.setOrderedDate(LocalDateTime.now());
        employee.addMenu(menu);
        menu.addEmployee(employee);

        employeeDao.save(employee);
        menuDao.save(menu);

        return "success";
    }


}

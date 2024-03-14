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

    @GetMapping("/add-history")
    public String addHistory(@RequestParam("menuId")int menuId,
                               @RequestParam("price")int price, Model model,
                             Authentication authentication){

        String employeeName = authentication.getName();

        Employee employee = employeeDao.findEmployeeByUserName(employeeName);
        int employeeCost = employee.getTotalSalaryDeduction()+(int) (price * 0.7);
        int companyCost =employee.getCompanyCost() + (int) (price * 0.3);

        employee.setTotalSalaryDeduction(employeeCost);
        employee.setCompanyCost(companyCost);

        Menu menu = menuDao.findMenuById(menuId);
        menu.setOrderedDate(LocalDateTime.now());
        employee.addMenu(menu);
        menu.addEmployee(employee);

        employeeDao.save(employee);
        menuDao.save(menu);

        return "success";
    }


}

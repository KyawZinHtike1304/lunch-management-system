package com.example.lunchmanagement.controller;

import com.example.lunchmanagement.dao.BookingDao;
import com.example.lunchmanagement.dao.EmployeeDao;
import com.example.lunchmanagement.dao.MenuDao;
import com.example.lunchmanagement.entity.Booking;
import com.example.lunchmanagement.entity.Employee;
import com.example.lunchmanagement.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class HistoryController {

    private final EmployeeDao employeeDao;
    private final MenuDao menuDao;
    private final BookingDao bookingDao;

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
        Menu menu = menuDao.findMenuById(menuId);
        Booking booking = new Booking();

        int employeeCost = employee.getTotalSalaryDeduction()+(int) (price * 0.7);
        int companyCost =employee.getCompanyCost() + (int) (price * 0.3);
        int totalCost = employee.getTotalCost() + price;

        employee.setTotalSalaryDeduction(employeeCost);
        employee.setCompanyCost(companyCost);
        employee.setTotalCost(totalCost);

        employee.addMenu(menu);
        employee.addBooking(booking);
        menu.addBooking(booking);

        employeeDao.save(employee);
        menuDao.save(menu);
        bookingDao.save(booking);

        return "success";
    }


}

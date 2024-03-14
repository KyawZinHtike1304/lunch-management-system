package com.example.lunchmanagement.controller;

import com.example.lunchmanagement.entity.Employee;
import com.example.lunchmanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("employee",new Employee());
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(Employee employee, BindingResult result){
        if (result.hasErrors()){
            return "register";
        }
        authService.register(employee);
        return "redirect:/home";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }
}

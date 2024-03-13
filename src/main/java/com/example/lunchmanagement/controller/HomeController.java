package com.example.lunchmanagement.controller;

import com.example.lunchmanagement.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MenuService menuService;

    @RequestMapping({"/","/home"})
    public String home(Model model){
        model.addAttribute("menu",menuService.listMenu());
        return "home";
    }
}

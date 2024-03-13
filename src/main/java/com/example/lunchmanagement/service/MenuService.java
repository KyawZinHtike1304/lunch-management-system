package com.example.lunchmanagement.service;

import com.example.lunchmanagement.dao.MenuDao;
import com.example.lunchmanagement.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuDao menuDao;

    public List<Menu> listMenu(){
        return menuDao.findAll();
    }
}

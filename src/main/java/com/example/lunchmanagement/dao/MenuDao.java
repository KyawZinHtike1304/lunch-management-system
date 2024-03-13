package com.example.lunchmanagement.dao;

import com.example.lunchmanagement.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDao extends JpaRepository<Menu,Integer> {
}

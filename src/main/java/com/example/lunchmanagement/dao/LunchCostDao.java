package com.example.lunchmanagement.dao;

import com.example.lunchmanagement.entity.LunchCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchCostDao extends JpaRepository<LunchCost,Integer> {
}

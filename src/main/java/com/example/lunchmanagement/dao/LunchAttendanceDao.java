package com.example.lunchmanagement.dao;

import com.example.lunchmanagement.entity.LunchAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchAttendanceDao extends JpaRepository<LunchAttendance,Integer> {
}

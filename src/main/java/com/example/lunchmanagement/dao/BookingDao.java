package com.example.lunchmanagement.dao;

import com.example.lunchmanagement.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDao extends JpaRepository<Booking,Integer> {
}

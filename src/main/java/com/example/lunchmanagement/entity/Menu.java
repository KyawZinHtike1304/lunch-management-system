package com.example.lunchmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Menu {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private String imgUrl;

    @ManyToOne
    private Employee employee;

    @OneToMany(mappedBy = "menu")
    private List<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking){
        booking.setMenu(this);
        bookings.add(booking);
    }

    public Menu(String name, int price, String imgUrl) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }
}

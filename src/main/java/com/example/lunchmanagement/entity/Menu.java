package com.example.lunchmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Menu(String name, int price, String imgUrl) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }
}

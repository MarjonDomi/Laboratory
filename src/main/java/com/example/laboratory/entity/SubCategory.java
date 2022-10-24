package com.example.laboratory.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String name;
    private String norma;
    private String rezultati;
}

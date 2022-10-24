package com.example.laboratory.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Component {
    
    public enum Values {
        BLOOD,
        COVID,
        LIVER
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private Values values;
}

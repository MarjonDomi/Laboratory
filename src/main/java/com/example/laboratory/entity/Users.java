package com.example.laboratory.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "users")
@Data

public class Users {

    public enum Roles {
        USER,
        ADMIN
    }
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String name;
    private String lastname;
    private String username;
    private String password;
    private Date birthdate;
    private String address;
    @Enumerated(EnumType.STRING)
    private Roles roles;
}


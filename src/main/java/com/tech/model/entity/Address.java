package com.tech.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String city;
    private String state;

    @OneToOne(mappedBy = "address")
    //cascade = CascadeType.ALL
    private Student student;
}

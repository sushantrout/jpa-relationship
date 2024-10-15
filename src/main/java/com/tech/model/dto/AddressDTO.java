package com.tech.model.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private int id;
    private String city;
    private String state;
    private StudentDTO student;
}

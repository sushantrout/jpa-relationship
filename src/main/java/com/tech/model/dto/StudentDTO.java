package com.tech.model.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private int id;
    private String name;
    private String email;
    private AddressDTO address;
}

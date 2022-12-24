package com.example.berzkoder02.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDto {

    @NotEmpty(message = "name is required")
    private String name;

    private String address;

    @NotEmpty(message = "email is required")
    @Email(message = "email is not valid")
    @Column(unique = true)
    private String email;
}

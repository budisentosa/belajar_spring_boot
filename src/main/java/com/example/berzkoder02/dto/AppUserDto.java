package com.example.berzkoder02.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDto {
    private String fullName;
    private String email;
    private String password;
    private String roles;
}

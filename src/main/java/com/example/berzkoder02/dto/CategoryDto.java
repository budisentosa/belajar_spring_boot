package com.example.berzkoder02.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    @NotEmpty
    private String name;

}

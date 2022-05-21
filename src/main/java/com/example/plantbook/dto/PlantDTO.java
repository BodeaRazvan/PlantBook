package com.example.plantbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlantDTO {
    private String name;
    private String description;
    private String url;
    private Double price;
}

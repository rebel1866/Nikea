package com.nikea.productservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FurnitureDto {
    private Long id;
    private String name;
    private String color;
    private Double price;
    private FurnitureType type;
    private List<FurnitureSizeDto> availableSizes;
}

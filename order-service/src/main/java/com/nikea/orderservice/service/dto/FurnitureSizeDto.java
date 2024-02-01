package com.nikea.orderservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FurnitureSizeDto {
    private Long id;
    private Integer width;
    private Integer length;
    private Integer height;
}

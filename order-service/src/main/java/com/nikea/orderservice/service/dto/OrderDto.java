package com.nikea.orderservice.service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private String id;
    private Long furnitureId;
    private Double totalPrice;
    private LocalDateTime dateTime;
    private String comment;
}

package com.nikea.productservice.dao.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private Long furnitureId;
    private Integer totalPrice;
    private LocalDateTime dateTime;
    private String comment;
}

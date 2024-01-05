package com.nikea.productservice.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "furnituresize")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FurnitureSize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "width")
    private Integer width;
    @Column(name = "length")
    private Integer length;
    @Column(name = "height")
    private Integer height;
}

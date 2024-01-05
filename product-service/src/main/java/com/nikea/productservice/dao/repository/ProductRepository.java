package com.nikea.productservice.dao.repository;

import com.nikea.productservice.dao.model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Furniture, Long> {
}

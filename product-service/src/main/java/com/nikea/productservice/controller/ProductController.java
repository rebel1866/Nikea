package com.nikea.productservice.controller;

import com.nikea.productservice.service.logic.ProductService;
import com.nikea.productservice.service.dto.FurnitureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public FurnitureDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }
    @GetMapping
    public List<FurnitureDto> getAll() {
        return productService.getAll();
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    public FurnitureDto addProduct(@RequestBody FurnitureDto furnitureDto){
        return productService.createProduct(furnitureDto);
    }
    @PutMapping("/{id}")
    public FurnitureDto editProduct(@PathVariable("id") Long id,
                                    @RequestBody FurnitureDto furnitureDto){
        return productService.editProduct(id, furnitureDto);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
    }
}

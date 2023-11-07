package com.nikea.productservice.restintegration;

import com.nikea.productservice.service.dto.FurnitureDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "PRODUCT-SERVICE", fallback = ProductServiceFallback.class)
public interface ProductService {
    @RequestMapping(value = "/product/{id}", method = GET)
    FurnitureDto getProduct(@PathVariable("id") Long id);
}

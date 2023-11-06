package com.ikea.productservice;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("PRODUCT-SERVICE")
public interface ProductClient {
    @RequestMapping(value = "/product/get", method = GET)
    void getUsers();
}

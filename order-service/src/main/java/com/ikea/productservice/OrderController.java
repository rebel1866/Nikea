package com.ikea.productservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private @Autowired ProductClient productClient;
    @GetMapping("/get")
    public void get(){
        System.out.println("get order");
        productClient.getUsers();
    }
}

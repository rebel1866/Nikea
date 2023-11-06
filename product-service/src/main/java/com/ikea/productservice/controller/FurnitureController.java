package com.ikea.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/furniture")
public class FurnitureController {
    @GetMapping("/get")
    public void get(){
        System.out.println("get furniture");
    }
}

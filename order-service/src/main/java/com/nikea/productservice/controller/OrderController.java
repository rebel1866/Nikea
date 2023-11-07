package com.nikea.productservice.controller;


import com.nikea.productservice.service.OrderService;
import com.nikea.productservice.service.dto.OrderDto;
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
@RequestMapping("/order")
public class OrderController {

    private @Autowired OrderService orderService;
    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable String id) {
        return orderService.getById(id);
    }
    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    public OrderDto addOrder(@RequestBody OrderDto orderDto){
        return orderService.createOrder(orderDto);
    }
    @PutMapping("/{id}")
    public OrderDto editOrder(@PathVariable("id") String id,
                                    @RequestBody OrderDto orderDto){
        return orderService.editOrder(id, orderDto);
    }
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id){
        orderService.deleteById(id);
    }
}

package com.nikea.productservice.controller;


import com.nikea.productservice.service.OrderService;
import com.nikea.productservice.service.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Value("${config.test.value}")
    private String testConfigValue;
    @Value("${common.value}")
    private String commonValue;
    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable String id) {
        logger.info(testConfigValue);
        logger.info(commonValue);
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

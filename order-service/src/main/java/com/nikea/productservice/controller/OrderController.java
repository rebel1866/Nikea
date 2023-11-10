package com.nikea.productservice.controller;


import com.nikea.productservice.config.ConfigProperties;
import com.nikea.productservice.service.dto.OrderDto;
import com.nikea.productservice.service.logic.OrderService;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/order")
public class OrderController {
    private @Autowired OrderService orderService;
    private @Autowired ConfigProperties configProperties;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable String id) {
        logger.info(configProperties.getTestValue());
        logger.info(configProperties.getAnotherTestValue());
        return orderService.getById(id);
    }
    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    @TimeLimiter(name = "timeLimiterApi")
    public CompletableFuture<OrderDto> addOrder(@RequestBody OrderDto orderDto){
        return CompletableFuture.supplyAsync(()-> orderService.createOrder(orderDto));
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

package com.microservices.ecomorderservice.controller;

import com.microservices.ecomorderservice.dto.Inventory;
import com.microservices.ecomorderservice.services.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/{productId}")
    public String placeOrder(@PathVariable String productId){
        return orderService.placeOrder(productId);
    }
}

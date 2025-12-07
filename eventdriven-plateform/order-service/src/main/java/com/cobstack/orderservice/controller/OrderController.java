package com.cobstack.orderservice.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cobstack.orderservice.model.Order;
import com.cobstack.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    public Flux<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public Flux<Order> getOrdersByUser(@PathVariable Long userId) {
        return service.getOrdersByUser(userId);
    }

    @PostMapping
    public Mono<Order> createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }

    @PatchMapping("/{id}")
    public Mono<Order> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        return service.updateOrderStatus(id, updates.get("status"));
    }
}
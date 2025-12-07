package com.cobstack.orderservice.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobstack.orderservice.model.Order;
import com.cobstack.orderservice.repository.OrderRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping
	public Flux<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@GetMapping("/user/{userId}")
	public Flux<Order> getOrdersByUser(@PathVariable String userId) {
		return orderRepository.findByUserId(userId);
	}

	@PostMapping
	public Mono<Order> createOrder(@RequestBody Order order) {
		order.setStatus("PENDING");
		return orderRepository.save(order);
	}

	@PatchMapping("/{id}")
	public Mono<Order> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> updates) {
		return orderRepository.findById(id).flatMap(order -> {
			order.setStatus(updates.get("status"));
			return orderRepository.save(order);
		});
	}
}

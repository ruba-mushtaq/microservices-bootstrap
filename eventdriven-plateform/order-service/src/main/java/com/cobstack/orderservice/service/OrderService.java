package com.cobstack.orderservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.cobstack.orderservice.dto.OrderEvent;
import com.cobstack.orderservice.model.Order;
import com.cobstack.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository repository;
	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${order.kafka.topic}")
	private String topic;

	public Flux<Order> getAllOrders() {
		return repository.findAll();
	}

	public Flux<Order> getOrdersByUser(Long userId) {
		return repository.findByUserId(userId);
	}

	public Mono<Order> createOrder(Order order) {
		order.setStatus("PENDING");
		order.setCreatedAt(LocalDateTime.now());

		return repository.save(order).doOnSuccess(saved -> {
			OrderEvent event = new OrderEvent(saved.getId(), saved.getUserId(), saved.getProductIds(),
					saved.getTotalAmount(), saved.getStatus());
			kafkaTemplate.send(topic, saved.getUserId().toString(), event);
		});
	}

	public Mono<Order> updateOrderStatus(Long orderId, String status) {
		return repository.findById(orderId).flatMap(order -> {
			order.setStatus(status);
			return repository.save(order);
		}).doOnSuccess(order -> {
			OrderEvent event = new OrderEvent(order.getId(), order.getUserId(), order.getProductIds(),
					order.getTotalAmount(), order.getStatus());
			kafkaTemplate.send(topic, order.getUserId().toString(), event);
		});
	}
}

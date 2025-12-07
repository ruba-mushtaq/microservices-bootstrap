package com.cobstack.paymentservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.cobstack.paymentservice.model.Payment;

import reactor.core.publisher.Flux;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Payment, Long> {
    Flux<Payment> findByOrderId(Long orderId);
}
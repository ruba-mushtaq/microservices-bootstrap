package com.cobstack.paymentservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.cobstack.paymentservice.model.Payment;
import com.cobstack.paymentservice.repository.PaymentRepository;
import com.cobstack.paymentservice.security.JwtUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired private PaymentRepository paymentRepository;
    @Autowired private WebClient webClient; // for inter-service call
    @Autowired private JwtUtil jwtUtil;
    @Value("${order-service.url}")
    private String orderServiceURL;

    @PostMapping
    public Mono<Payment> makePayment(@RequestBody Payment payment, @RequestHeader("Authorization") String token) {
        if (!jwtUtil.validateToken(token.substring(7))) {
            return Mono.error(new RuntimeException("Invalid JWT"));
        }

        payment.setStatus("SUCCESS"); // simulate payment

        return paymentRepository.save(payment)
            .flatMap(savedPayment -> webClient.patch()
                .uri(orderServiceURL+"/orders/{id}", savedPayment.getOrderId())
                .header("Authorization", token)
                .bodyValue(Map.of("status", "PAID")) 
                .retrieve()
                .bodyToMono(Void.class)
                .thenReturn(savedPayment)
            );
    }

    @GetMapping
    public Flux<Payment> getAllPayments(@RequestHeader("Authorization") String token) {
        if (!jwtUtil.validateToken(token.substring(7))) return Flux.error(new RuntimeException("Invalid JWT"));
        return paymentRepository.findAll();
    }

    @GetMapping("/order/{orderId}")
    public Flux<Payment> getPaymentsByOrder(@PathVariable Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    @GetMapping("/{id}")
    public Mono<Payment> getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id);
    }

 

    @DeleteMapping("/{id}")
    public Mono<Void> deletePayment(@PathVariable Long id) {
        return paymentRepository.deleteById(id);
    }
}

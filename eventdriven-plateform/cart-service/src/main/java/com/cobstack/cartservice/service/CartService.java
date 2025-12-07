package com.cobstack.cartservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cobstack.cartservice.dto.CartUpdatedEvent;
import com.cobstack.cartservice.model.CartItem;
import com.cobstack.cartservice.repository.CartRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${cart.kafka.topic}")
    private String topic;

    public Mono<CartItem> addToCart(String userId, Long productId, int qty) {
        return cartRepository.findByUserIdAndProductId(userId, productId)
                .flatMap(existing -> {
                    existing.setQuantity(existing.getQuantity() + qty);
                    return cartRepository.save(existing);
                })
                .switchIfEmpty(
                        cartRepository.save(
                                new CartItem(null, userId, productId, qty)
                        )
                )
                .doOnSuccess(item -> sendEvent(userId, productId, item.getQuantity(), "ADDED"));
    }

    public Mono<CartItem> updateQuantity(String userId, Long productId, int qty) {
        return cartRepository.findByUserIdAndProductId(userId, productId)
                .flatMap(item -> {
                    item.setQuantity(qty);
                    return cartRepository.save(item);
                })
                .doOnSuccess(item -> sendEvent(userId, productId, qty, "UPDATED"));
    }

    public Mono<Void> removeItem(String userId, Long productId) {
        return cartRepository.findByUserIdAndProductId(userId, productId)
                .flatMap(cartRepository::delete)
                .doOnSuccess(i -> sendEvent(userId, productId, 0, "REMOVED"));
    }

    public Flux<CartItem> getCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    private void sendEvent(String userId, Long productId, int qty, String action) {
        CartUpdatedEvent event = new CartUpdatedEvent(userId, productId, qty, action);
        kafkaTemplate.send(topic, userId.toString(), event);
    }
}
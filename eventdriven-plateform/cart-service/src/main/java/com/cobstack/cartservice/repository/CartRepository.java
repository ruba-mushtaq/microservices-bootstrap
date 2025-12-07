package com.cobstack.cartservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.cobstack.cartservice.model.CartItem;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CartRepository extends ReactiveCrudRepository<CartItem, Long> {

    Flux<CartItem> findByUserId(Long userId);
    Mono<CartItem> findByUserIdAndProductId(String userId, Long productId);
}


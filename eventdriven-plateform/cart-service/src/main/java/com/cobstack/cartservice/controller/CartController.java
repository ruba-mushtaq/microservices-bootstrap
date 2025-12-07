package com.cobstack.cartservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cobstack.cartservice.model.CartItem;
import com.cobstack.cartservice.service.CartService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{userId}/add")
    public Mono<CartItem> add(
            @PathVariable String userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return cartService.addToCart(userId, productId, quantity);
    }

    @PutMapping("/{userId}/update")
    public Mono<CartItem> update(
            @PathVariable String userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return cartService.updateQuantity(userId, productId, quantity);
    }

    @DeleteMapping("/{userId}/remove")
    public Mono<Void> remove(
            @PathVariable String userId,
            @RequestParam Long productId
    ) {
        return cartService.removeItem(userId, productId);
    }

    @GetMapping("/{userId}")
    public Flux<CartItem> getCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }
}

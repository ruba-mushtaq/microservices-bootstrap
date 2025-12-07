package com.cobstack.inventoryservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.cobstack.inventoryservice.model.InventoryItem;

import reactor.core.publisher.Mono;

public interface InventoryRepository extends ReactiveCrudRepository<InventoryItem, Long> {
    Mono<InventoryItem> findByProductId(Long productId);
}
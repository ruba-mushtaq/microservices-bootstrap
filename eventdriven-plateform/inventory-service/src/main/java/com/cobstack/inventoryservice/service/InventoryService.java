package com.cobstack.inventoryservice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cobstack.inventoryservice.dto.InventoryEvent;
import com.cobstack.inventoryservice.model.InventoryItem;
import com.cobstack.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InventoryService {

	 private final InventoryRepository repository;
	    private final KafkaTemplate<String, Object> kafkaTemplate;
	    private final String topic = "inventory-events";

	    public Mono<InventoryItem> adjustStock(Long productId, int quantityChange, String action) {
	        return repository.findByProductId(productId)
	                .defaultIfEmpty(new InventoryItem(productId, 0))
	                .flatMap(item -> {
	                    int newQty = item.getQuantity() + quantityChange;
	                    if (newQty < 0) newQty = 0; // prevent negative stock
	                    item.setQuantity(newQty);
	                    return repository.save(item);
	                })
	                .doOnSuccess(item -> {
	                    InventoryEvent event = new InventoryEvent(productId, quantityChange, action);
	                    kafkaTemplate.send(topic, productId.toString(), event);
	                });
	    }
}
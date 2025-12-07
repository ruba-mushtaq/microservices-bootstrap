package com.cobstack.inventoryservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cobstack.inventoryservice.dto.CartUpdatedEvent;
import com.cobstack.inventoryservice.dto.OrderEvent;
import com.cobstack.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventoryListener {

	 private final InventoryService service;

	    // Listen to Cart Service updates
	    @KafkaListener(topics = "cart-updated", groupId = "inventory-group")
	    public void handleCartEvent(CartUpdatedEvent event) {
	        int quantityChange = 0;
	        if ("ADDED".equals(event.getAction())) {
	            quantityChange = -event.getQuantity(); // decrease stock
	        } else if ("REMOVED".equals(event.getAction())) {
	            quantityChange = event.getQuantity(); // increase stock
	        }

	        service.adjustStock(event.getProductId(), quantityChange, event.getAction())
	               .subscribe(); // reactive
	    }

	    // Listen to Order Service events
	    @KafkaListener(topics = "order-events", groupId = "inventory-group")
	    public void handleOrderEvent(OrderEvent event) {
	        // decrease stock for all products in the order
	        event.getProductIds().forEach(productId -> 
	            service.adjustStock(productId, -1, "DECREASE").subscribe()
	        );
	    }
}

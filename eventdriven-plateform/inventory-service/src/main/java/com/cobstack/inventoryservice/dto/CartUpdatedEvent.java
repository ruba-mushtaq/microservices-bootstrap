package com.cobstack.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdatedEvent {
    private Long cartId;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private String action; // ADDED or REMOVED
}
package com.cobstack.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEvent {
    private Long productId;
    private Integer quantityChanged;
    private String action; // DECREASE, INCREASE
}

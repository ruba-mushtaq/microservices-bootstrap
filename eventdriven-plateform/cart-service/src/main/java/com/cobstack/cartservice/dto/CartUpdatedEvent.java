package com.cobstack.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdatedEvent {
    private String userId;
    private Long productId;
    private int quantity;
    private String action; // ADDED, UPDATED, REMOVED
}
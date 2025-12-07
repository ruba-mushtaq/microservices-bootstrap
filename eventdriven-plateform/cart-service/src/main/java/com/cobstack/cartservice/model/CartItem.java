package com.cobstack.cartservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Table("cart_items")
@AllArgsConstructor
public class CartItem {

    @Id
    private Long id;
    private String userId;
    private Long productId;
    private int quantity;
}
package com.cobstack.inventoryservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class InventoryItem {

    @Id
    private Long id;
    private Long productId;
    private Integer quantity;

    public InventoryItem(Long productId,Integer quantity){
    	this.productId=productId;
    	this.quantity=quantity;
    }
    // Getters & Setters
}
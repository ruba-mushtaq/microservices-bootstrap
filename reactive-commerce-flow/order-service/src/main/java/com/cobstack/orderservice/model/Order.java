package com.cobstack.orderservice.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("orders")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Order {

    @Id
    private Long id;
    private String userId;
    private String status; // PENDING, PAID, FAILED
    private Double amount;

   
    // getters and setters
}

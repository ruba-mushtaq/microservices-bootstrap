package com.cobstack.orderservice.model;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<Long> productIds;
    private Double totalAmount;
    private String status; // PENDING, COMPLETED, FAILED
    private LocalDateTime createdAt;

  

    // Getters & Setters
}

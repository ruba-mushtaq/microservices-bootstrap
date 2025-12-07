package com.cobstack.paymentservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    private Long id;
    private Long orderId;
    private Double amount;
    private String status; // SUCCESS, FAILED

  
    // getters and setters
}

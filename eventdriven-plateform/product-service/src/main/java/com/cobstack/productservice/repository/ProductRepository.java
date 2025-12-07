package com.cobstack.productservice.repository;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.cobstack.productservice.model.Product;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}


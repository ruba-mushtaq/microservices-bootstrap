package com.cobstack.productservice.service;

import com.cobstack.productservice.model.Product;
import com.cobstack.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	private final ProductRepository repo;

	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}

	public Flux<Product> getAll() {
		return repo.findAll();
	}

	public Mono<Product> getById(Long id) {
		return repo.findById(id);
	}

	public Mono<Product> create(Product product) {
		return repo.save(product);
	}

	public Mono<Product> update(Long id, Product updated) {
		return repo.findById(id).flatMap(existing -> {
			existing.setName(updated.getName());
			existing.setDescription(updated.getDescription());
			existing.setPrice(updated.getPrice());
			existing.setStock(updated.getStock());
			return repo.save(existing);
		});
	}

	public Mono<Void> delete(Long id) {
		return repo.deleteById(id);
	}
}

package com.cobstack.productservice.controller;

import com.cobstack.productservice.model.Product;
import com.cobstack.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@GetMapping
	public Flux<Product> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public Mono<Product> getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PostMapping
	public Mono<Product> create(@RequestBody Product product) {
		return service.create(product);
	}

	@PutMapping("/{id}")
	public Mono<Product> update(@PathVariable Long id, @RequestBody Product product) {
		return service.update(id, product);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable Long id) {
		return service.delete(id);
	}
}
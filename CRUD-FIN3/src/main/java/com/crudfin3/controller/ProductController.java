package com.crudfin3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crudfin3.model.Product;
import com.crudfin3.service.ProductService;

/**
 * ProductController
 *
 * Version 1.0
 *
 * Date: 08-06-2021
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE               AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 08-06-2021         LONGTB4           Create
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService service;

	/**
	 * Get all products
	 * 
	 * @param productName
	 * @return get all product method
	 */
	@GetMapping("/products")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<List<Product>> getAllProduct(@RequestParam(required = false) String productName) {
		return service.getAllProduct(productName);
	}

	/**
	 * Get product by id
	 * 
	 * @param id
	 * @return get product by id method
	 */
	@GetMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
		return service.getProductById(id);
	}

	/**
	 * Create product
	 * 
	 * @param product
	 * @return create product method
	 */
	@PostMapping("/products")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return service.createProduct(product);
	}

	/**
	 * Update product
	 * 
	 * @param id
	 * @param product
	 * @return update product method
	 */
	@PutMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		return service.updateProduct(id, product);
	}

	/**
	 * Remove product
	 * 
	 * @param id
	 * @return remove product method
	 */
	@DeleteMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
		return service.deleteProduct(id);
	}

}

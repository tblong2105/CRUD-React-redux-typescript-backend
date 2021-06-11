package com.crudfin3.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crudfin3.exception.ConstantMessage;
import com.crudfin3.exception.ResourceNotFoundException;
import com.crudfin3.model.Product;
import com.crudfin3.repository.ProductRepository;

/**
 * ProductService
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
@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	/**
	 * get all products
	 * 
	 * @param productName
	 * @return list product
	 */
	public ResponseEntity<List<Product>> getAllProduct(String productName) {
		List<Product> products = new ArrayList<>();
		
		// List all product when search title null
		if (productName == null) {
			
			// List all
			repo.findAll().forEach(products::add);
		} else {
			
			// Find LIKE by productname
			repo.findByProductNameContaining(productName).forEach(products::add);
		}
		if (products.isEmpty()) {
			return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	/**
	 * get product by id
	 * 
	 * @param id
	 * @return product
	 */
	public ResponseEntity<Product> getProductById(long id) {
		
		// Check product is exist
		Product productData = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.Product_not_found));
		return new ResponseEntity<>(productData, HttpStatus.OK);
	}

	/**
	 * create new product
	 * 
	 * @param product
	 * @return product
	 */
	public ResponseEntity<Product> createProduct(Product product) {
		
		// Get product by product name
		Product productData = repo.getProductByName(product.getProductName());
				
		if (productData != null) {
			throw new ResourceNotFoundException(ConstantMessage.ProductName_already);
		}
		try {
			// Create new product
			Product newProduct = repo.save(new Product(product.getProductName(), product.getDescription(),
					product.getPrice(), new Date(), true, product.getBrand()));
			return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Product>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * update product
	 * 
	 * @param id
	 * @param product
	 * @return product
	 */
	public ResponseEntity<Product> updateProduct(long id, Product product) {
		
		// Check product is exist
		Product tutorialData = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.Product_not_found));
		
		// Get product by name
		Product productData = repo.getProductByName(product.getProductName());
		
		// Check product name is duplicate
		if (productData != null && productData.getId() != id) {
			throw new ResourceNotFoundException(ConstantMessage.ProductName_already);
		}
		tutorialData.setProductName(product.getProductName());
		tutorialData.setDescription(product.getDescription());
		tutorialData.setPrice(product.getPrice());
		tutorialData.setEnabled(true);
		tutorialData.setBrand(product.getBrand());
		return new ResponseEntity<>(repo.save(tutorialData), HttpStatus.OK);
	}

	/**
	 * remove product
	 * 
	 * @param id
	 * @return product
	 */
	public ResponseEntity<Product> deleteProduct(long id) {
		
		// Check product is exist
		Product tutorialData = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.Product_not_found));
		
		// If product is exist
		if (repo.findById(id).isPresent()) {
			
			// Delete product by delete logic
			repo.deleteLogic(id);
		}
		return new ResponseEntity<>(tutorialData, HttpStatus.NO_CONTENT);
	}

}

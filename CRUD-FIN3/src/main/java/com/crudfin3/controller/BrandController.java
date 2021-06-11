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
import org.springframework.web.bind.annotation.RestController;

import com.crudfin3.model.Brand;
import com.crudfin3.service.BrandService;

/**
 * BrandController
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
public class BrandController {

	@Autowired
	private BrandService service;

	/**
	 * Get all brands
	 * 
	 * @return get all brand method
	 */
	@GetMapping("/brands")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<List<Brand>> getAllBrands() {
		return service.getAllBrands();
	}

	/**
	 * Create brand
	 * 
	 * @param brand
	 * @return create brand method
	 */
	@PostMapping("/brands")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
		return service.createBrand(brand);
	}

	/**
	 * Get brand by id
	 * 
	 * @param id
	 * @return get brand by id method
	 */
	@GetMapping("/brands/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<Brand> getBrandById(@PathVariable("id") long id) {
		return service.getBrandById(id);
	}

	/**
	 * Update brand
	 * 
	 * @param id
	 * @param brand
	 * @return update brand method
	 */
	@PutMapping("/brands/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<Brand> updateBrand(@PathVariable("id") long id, @RequestBody Brand brand) {
		return service.updateBrand(id, brand);
	}
	/**
	 * Remove brand
	 * 
	 * @param id
	 * @return remove brand method
	 */
	@DeleteMapping("/brands/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<Brand> deleteBrand(@PathVariable("id") long id) {
		return service.deleteBrand(id);
	}

}

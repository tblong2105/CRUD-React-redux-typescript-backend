package com.crudfin3.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.crudfin3.exception.ConstantMessage;
import com.crudfin3.exception.ResourceNotFoundException;
import com.crudfin3.model.Brand;
import com.crudfin3.repository.BrandRepository;

/**
 * BrandService
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
public class BrandService {

	@Autowired
	private BrandRepository repo;

	/**
	 * get all brands
	 * 
	 * @return List brands
	 */
	public ResponseEntity<List<Brand>> getAllBrands() {
		List<Brand> brands = new ArrayList<>();
		
		// Get all brand
		repo.findAll().forEach(brands::add);
		if (brands.isEmpty()) {
			return new ResponseEntity<>(brands, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(brands, HttpStatus.OK);
	}

	/**
	 * create new brand
	 * 
	 * @param brand
	 * @return brand
	 */
	public ResponseEntity<Brand> createBrand(Brand brand) {
		
		// Get brand by brand name
		Brand brandData = repo.getBrandByName(brand.getBrandName());
		
		//check brand name duplicate
		if (brandData != null) {
			throw new ResourceNotFoundException(ConstantMessage.BrandName_already);
		}
		try {
			
			// Create new brand
			Brand newBrand = repo.save(new Brand(brand.getBrandName(), new Date(), true));
			return new ResponseEntity<>(newBrand, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Brand>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * get brand by id
	 * 
	 * @param id
	 * @return brand
	 */
	public ResponseEntity<Brand> getBrandById(long id) {
		
		//check brand is exist
		Brand productData = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.Brand_not_found));
		return new ResponseEntity<>(productData, HttpStatus.OK);
	}

	/**
	 * update brand
	 * 
	 * @param id
	 * @param brand
	 * @return brand
	 */
	public ResponseEntity<Brand> updateBrand(long id, @RequestBody Brand brand) {
		
		// Check brand is exist
		Brand tutorialData = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.Brand_not_found));
		
		// Get brand by brand name
		Brand brandData = repo.getBrandByName(brand.getBrandName());
		
		// Check brand name duplicate
		if (brandData != null && brandData.getId() != id) {
			throw new ResourceNotFoundException(ConstantMessage.BrandName_already);
		}
		tutorialData.setBrandName(brand.getBrandName());
		return new ResponseEntity<>(repo.save(tutorialData), HttpStatus.OK);
	}

	/**
	 * remove brand
	 * 
	 * @param id
	 * @return brand
	 */
	public ResponseEntity<Brand> deleteBrand(@PathVariable("id") long id) {
		
		// Check brand is exist
		Brand tutorialData = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.Brand_not_found));
		try {
			
			// If brand is exist then delete brand by delete logic
			if (repo.findById(id).isPresent()) {
				repo.deleteById(id);
			}
		} catch (Exception e) {
			throw new ResourceNotFoundException(ConstantMessage.Brand_foreign_key);
		}
		return new ResponseEntity<>(tutorialData, HttpStatus.NO_CONTENT);
	}
}

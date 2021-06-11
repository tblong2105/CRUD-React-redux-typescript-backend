package com.crudfin3.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.crudfin3.model.Brand;

/**
 * BrandRepository
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
public interface BrandRepository extends JpaRepository<Brand, Long> {
		
	/**
	 * Get all brand order by brandName asc
	 */
	@Query("SELECT u FROM Brand u ORDER BY u.brandName ASC")
	List<Brand> findAll();
	
	/**
	 * Delete logic brand by id 
	 * @param id
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Brand p SET p.enabled = false WHERE p.id = ?1")
	void deleteLogic(long id);
	
	/**
	 * get brand by name
	 * @param brandName
	 * @return
	 */
	@Query("SELECT u FROM Brand u WHERE u.brandName = ?1")
	Brand getBrandByName(String brandName);	

}

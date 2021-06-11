package com.crudfin3.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.crudfin3.model.Product;

/**
 * ProductRepository
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
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * find like product by product name
	 * @param name
	 * @return
	 */
	@Query("SELECT u FROM Product u WHERE u.productName LIKE %?1% AND u.enabled = 1 ORDER BY u.id DESC")
	List<Product> findByProductNameContaining(String name);

	/**
	 * get all product order by desc
	 */
	@Query("SELECT p FROM Product p WHERE p.enabled = 1 ORDER BY p.id DESC")
	List<Product> findAll();

	/**
	 * get all products
	 * @return List Product
	 */
	@Query("SELECT p FROM Product p")
	List<Product> listAll();

	/**
	 * find product by id
	 * @param id
	 * @return Optional Product 
	 */
	@Query("SELECT p FROM Product p WHERE p.id = ?1 AND p.enabled = 1")
	Optional<Product> findById(long id);

	/**
	 * delete logic product by id
	 * @param id
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.enabled = false, p.brand = null WHERE p.id = ?1")
	void deleteLogic(long id);
	
	/**
	 * get product by product name
	 * @param productName
	 * @return Product
	 */
	@Query("SELECT p FROM Product p WHERE p.enabled = 1 AND p.productName = ?1")
	Product getProductByName(String productName);

}

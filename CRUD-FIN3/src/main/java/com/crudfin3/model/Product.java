package com.crudfin3.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Product Model
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
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = true, length = 32, unique = true)
	private String productName;

	@Column(nullable = true, length = 4096)
	private String description;

	@Column(nullable = true)
	private float price;

	@Column(nullable = true)
	private Date createDate;

	@Column(name = "isDeleted", nullable = true)
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	public Product() {
	}

	/**
	 * 
	 * @param productName
	 * @param description
	 * @param price
	 * @param createDate
	 * @param enabled
	 * @param brand
	 */
	public Product(String productName, String description, float price, Date createDate, boolean enabled, Brand brand) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.createDate = createDate;
		this.enabled = enabled;
		this.brand = brand;
	}

	/**
	 * 
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 
	 * @param productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * 
	 * @return enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * 
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * 
	 * @return brand
	 */
	public Brand getBrand() {
		return brand;
	}

	/**
	 * 
	 * @param brand
	 */
	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	/**
	 * 
	 * @param id
	 */
	@JsonProperty("brand")
	private void unpackNested(long id) {
		this.brand = new Brand();
		brand.setId(id);
	}

}

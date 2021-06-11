package com.crudfin3.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Brand Model
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
@Table(name = "brands")
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = true, length = 32, unique = true)
	private String brandName;

	@Column(nullable = true)
	private Date createDate;

	@Column(name = "isDeleted", nullable = true)
	private boolean enabled;
	
	public Brand() {

	}

	/**
	 * 
	 * @param id
	 */
	public Brand(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @param brandName
	 * @param createDate
	 * @param enabled
	 */
	public Brand(String brandName, Date createDate, boolean enabled) {
		this.brandName = brandName;
		this.createDate = createDate;
		this.enabled = enabled;
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
	 * @return brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * 
	 * @param brandName
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
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

	@Override
	public String toString() {
		return brandName;
	}

}

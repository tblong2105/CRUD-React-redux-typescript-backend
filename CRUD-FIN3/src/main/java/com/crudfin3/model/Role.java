package com.crudfin3.model;

import javax.persistence.*;

/**
 * Role Model
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
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	
	public Role() {

	}

	/**
	 * 
	 * @param name
	 */
	public Role(ERole name) {
		this.name = name;
	}

	/**
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * @return name
	 */
	public ERole getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(ERole name) {
		this.name = name;
	}

}
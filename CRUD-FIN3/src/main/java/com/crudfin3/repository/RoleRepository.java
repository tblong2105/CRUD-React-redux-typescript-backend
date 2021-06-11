package com.crudfin3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudfin3.model.ERole;
import com.crudfin3.model.Role;

/**
 * RoleRepository
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
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	/**
	 * get role by role name
	 * @param name
	 * @return Optional Role
	 */
	Optional<Role> findByName(ERole name);
}
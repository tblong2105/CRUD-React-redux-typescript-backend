package com.crudfin3.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crudfin3.model.User;

/**
 * UserRepository
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
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * find user  by user name
	 * @param username
	 * @return user
	 */
	Optional<User> findByUsername(String username);

	/**
	 * check user name is exist
	 * @param username
	 * @return boolean
	 */
	Boolean existsByUsername(String username);

	/**
	 * check email is exist
	 * @param email
	 * @return boolean
	 */
	Boolean existsByEmail(String email);

	/**
	 * get all user order by desc
	 * @return list user
	 */
	@Query("SELECT p FROM User p WHERE p.isDeleted = 0 AND p.username != 'admin' ORDER BY p.id DESC")
	List<User> listAll();

	/**
	 * get all product valid
	 * @return list user
	 */
	@Query("SELECT p FROM User p WHERE p.isDeleted = 0")
	List<User> listValid();

	/**
	 * find user by username or fullname
	 * @param name
	 * @return list user
	 */
	@Query("SELECT u FROM User u WHERE CONCAT(u.username,'', u.fullName)  LIKE %?1% AND u.isDeleted = 0 AND u.username != 'admin' ORDER BY u.id DESC")
	List<User> findByUsernameContaining(String name);

	/**
	 * find user by id
	 * @param id
	 * @return user
	 */
	@Query("SELECT u FROM User u WHERE u.id = ?1 AND u.isDeleted = 0")
	Optional<User> findById(long id);

	/**
	 * delete logic user by id
	 * @param id
	 */
	@Transactional
	@Modifying
	@Query("UPDATE User p SET p.isDeleted = true WHERE p.id = ?1")
	void deleteLogic(long id);

	/**
	 * update user password
	 * @param id
	 * @param password
	 */
	@Query("UPDATE User u SET u.password = ?2 WHERE u.id = ?1")
	void changePassword(long id, String password);

	/**
	 * find user by email
	 * @param email
	 * @return user
	 */
	@Query("SELECT c FROM User c WHERE c.email = ?1")
	public User findByEmail(String email);
	
	/**
	 * find user by resetPasswordToken
	 * @param token
	 * @return user
	 */
	public User findByResetPasswordToken(String token);
	
	/**
	 * find user by user name
	 * @param userName
	 * @return user
	 */
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	public User getCurrentUser(String userName);
	
	/**
	 * find user by user name and not deleted yet
	 * @param userName
	 * @return user
	 */
	@Query("SELECT u FROM User u WHERE u.username = ?1 AND u.isDeleted = 0")
	public User GetUserByUserName(String userName);
	
	/**
	 * get user by email
	 * @param email
	 * @return
	 */
	@Query("SELECT u FROM User u WHERE u.email = ?1 AND u.isDeleted = 0")
	public User GetUserByEmail(String email);
	
	/**
	 * get user by toke_reset_password
	 * @param toke_reset_password
	 * @return user
	 */
	@Query("SELECT u FROM User u WHERE u.resetPasswordToken = ?1 AND u.isDeleted = 0")
	public User GetUserByTokenPassword (String toke_reset_password);
}
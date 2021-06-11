package com.crudfin3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.crudfin3.model.User;
import com.crudfin3.repository.UserRepository;

/**
 * Schedule
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
public class Schedule {
	
	@Autowired
	private UserRepository repo;

	/**
	 * remove account inactive every day
	 */
	@Scheduled(cron =  "59 59 23 ? * *")
    public void scheduleTaskWithFixedRate() {
		
		// Get all account available
        List<User> lsUser = repo.listValid();                 
        for (User user : lsUser) {   
        	
        	// Check account if dont activate greater than 60 day
			if(System.currentTimeMillis() - user.getActionTime().getTime() > 5184000000L ) {
				
				// Delete account by delete logic
				repo.deleteLogic(user.getId());
			}
		}
    }
}

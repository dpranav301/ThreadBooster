package com.thread.booster.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.thread.booster.entity.User;
import com.thread.booster.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import java.util.*;

@Service
@Slf4j
public class NonAsyncService {
	@Autowired
	UserRepository userRepo;

	public List<User> getUserByName(String name) {
        log.info("Getting User {} from the repository.", name);

        List<User> userList = userRepo.findByName(name);

        if (CollectionUtils.isEmpty(userList)) {
            log.info("No User found by name {}", name);
            return new ArrayList<User>();
        }
        log.info("found {} customers by name {}", userList.size(), name);
        return userList;
    }

    public User addUser(User user) {
        log.info("Adding User {} to database", user.getName());
        User customer = userRepo.save(user);
        log.info("Added user {} successfully", customer.getName());
        return customer;
    }
}

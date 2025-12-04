package com.thread.booster.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thread.booster.entity.FileData;
import com.thread.booster.entity.User;
import com.thread.booster.service.FileOperationService;
import com.thread.booster.service.NonAsyncService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/nonAsync")
@Slf4j
public class NonAsyncController {

	@Autowired
	private NonAsyncService nonAsyncService;

	private FileOperationService fileOperationService;

	@GetMapping("/users/{name}")
    public List<User> getUsersByName(@PathVariable String name) {
        log.info("Getting User by name {} ", name);
        List<User> userList = nonAsyncService.getUserByName(name);
        log.info("Received {} Users by name {}", userList.size(), name);
        return userList;
    }

    @PostMapping("/users/save")
    public User addUser(@RequestBody User user) {
        log.info("Adding user {} to the Database", user.getName());
        return nonAsyncService.addUser(user);
    }

    @GetMapping("/fileread")
    public String readFile() {
        log.info("reading file request");
        return fileOperationService.readFile();
    }

    @PostMapping("/filewrite")
    public boolean writeFile(@RequestBody FileData fileData) {
        log.info("Write data {} to File", fileData);
        return fileOperationService.writeFile(fileData);
    }
}

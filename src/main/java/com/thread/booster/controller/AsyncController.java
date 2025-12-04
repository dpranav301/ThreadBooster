package com.thread.booster.controller;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thread.booster.entity.FileData;
import com.thread.booster.entity.User;
import com.thread.booster.service.AsyncService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {

	@Autowired
	private AsyncService asyncService;

	@GetMapping("/users/{name}")
    public CompletableFuture<List<User>> getUserByName(@PathVariable String name) {
        log.info("Getting User by name {} ", name);
        CompletableFuture<List<User>> listCompletableFuture = asyncService.getUserByName(name);
        return listCompletableFuture;
    }

    @PostMapping("/users/save")
    public CompletableFuture<User> addUser(@RequestBody User User) {
        log.info("Adding user {} to the Database", User.getName());
        return asyncService.saveUser(User);
    }

    @GetMapping("/fileread")
    public CompletableFuture<String> readFile() {
        log.info("reading file request");
        return asyncService.readFile();
    }

    @PostMapping("/filewrite")
    public CompletableFuture<Boolean> writeFile(@RequestBody FileData fileData) {
        log.info("Write data {} to File", fileData);
        return asyncService.writeFile(fileData);
    }
}

package com.thread.booster.service;


import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.thread.booster.entity.FileData;
import com.thread.booster.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncService {

	@Autowired
	private NonAsyncService nonAsyncService;
	@Autowired
	private FileOperationService fileOperationService;

	@Async
    public CompletableFuture<List<User>> getUserByName(String name) {
        log.info("Getting customer {} using Asynch thread", name);
        List<User> customerList = nonAsyncService.getUserByName(name);
        return CompletableFuture.completedFuture(customerList);
    }

    @Async
    public CompletableFuture<User> saveUser(User user) {
        log.info("Saving customer {} using Asynch thread", user.getName());
        User newCustomer = nonAsyncService.addUser(user);
        return CompletableFuture.completedFuture(newCustomer);
    }

    @Async
    public CompletableFuture<String> readFile() {
        log.info("Reading the file using Asynch thread");
        String fileData = fileOperationService.readFile();
        return CompletableFuture.completedFuture(fileData);
    }

    @Async
    public CompletableFuture<Boolean> writeFile(FileData fileData) {
        log.info("Writing to file using Asynch thread");
        boolean result = fileOperationService.writeFile(fileData);
        return CompletableFuture.completedFuture(result);
    }
}

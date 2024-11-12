package com.project.CRUD.controller;

import com.project.CRUD.entity.Blogger;


import com.project.CRUD.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/blogger")
public class BloggerController {


    @Autowired
    private BloggerService bloggerService;


    @PostMapping("/save/blogger")
    public Blogger saveblogger(@RequestBody Blogger blogger) {
        return bloggerService.saveblogger(blogger);
    }


    @GetMapping("/get/blogger")
    public List<Blogger> getbloggers() {
        return bloggerService.getBloggers();
    }


    @GetMapping("/get/blogger/{UserID}")
    public Blogger getblogger(@PathVariable Integer UserID) {
        return bloggerService.getBloggers(UserID);
    }


    @DeleteMapping("/delete/blogger/{UserID}")
    public void deleteBlogger(@PathVariable Integer UserID) {
        bloggerService.deleteBlogger(UserID);
    }


    @PutMapping("/update/blogger")
    public Blogger updateBlogger(@RequestBody Blogger blogger) {
        return bloggerService.updateBlogger(blogger);
    }


    @GetMapping("/slow-operation")
    public String slowOperation() throws InterruptedException {
        Thread.sleep(5000);
        return "Slow operation completed";
    }


    @PostMapping("/upload/blogger/{userID}")
    public CompletableFuture<ResponseEntity<String>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable Integer userID) {
        try {
            List<byte[]> fileDataList = List.of(file.getBytes());

            List<String> fileNames = List.of((file.getOriginalFilename() != null) ? file.getOriginalFilename() : "default_filename");

            List<String> fileTypes = List.of((file.getContentType() != null) ? file.getContentType() : "application/octet-stream");

            return bloggerService.saveFiles(fileDataList, fileNames, fileTypes, userID)
                    .thenApply(ResponseEntity::ok);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(
                    ResponseEntity.badRequest().body("Error uploading file: " + e.getMessage()));
        }

    }


}

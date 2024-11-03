package com.project.CRUD.controller;

import com.project.CRUD.entity.Blogger;

import com.project.CRUD.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
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


//    @GetMapping("/slow-operation")
//    public String slowOperation() throws InterruptedException {
//        Thread.sleep(5000);
//        return "Slow operation completed";
//    }


    @PostMapping("/upload/blogger/{userID}")
    public CompletableFuture<ResponseEntity<String>> uploadFile(@RequestParam("file") MultipartFile file  ,@PathVariable Integer userID  ) {
        try {
            byte[] fileData = file.getBytes();
            String fileName = file.getOriginalFilename();
            return  bloggerService.saveFile(fileData, fileName,userID)
                    .thenApply(result -> ResponseEntity.ok("File uploaded successfully: " + fileName));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Error uploading file"));
        }
    }

    @GetMapping("/download/blogger/{userID}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer userID) {
        try {
            Blogger blogger = bloggerService.getBloggers(userID);
            byte[] fileData = blogger.getFileData();
            String fileName = blogger.getFileName();

            ByteArrayResource resource = new ByteArrayResource(fileData);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}

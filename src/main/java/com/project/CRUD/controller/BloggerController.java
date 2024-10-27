package com.project.CRUD.controller;

import com.project.CRUD.entity.Blogger;

import com.project.CRUD.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;

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


    @GetMapping("/slow-operation")
    public String slowOperation() throws InterruptedException {
        Thread.sleep(5000);
        return "Slow operation completed";
    }

    @PostMapping("/upload/{userID}")
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

//    @Value("${file.upload-dir}")
//    private String uploadDir;

    // Endpoint for file upload associated with a Blogger
//    @PostMapping("/upload/{userId}")
//    public ResponseEntity<String> uploadFile(@RequestParam("fileUpload") MultipartFile file, @PathVariable Integer userId) {
//        try {
//            String fileName = BloggerService.storeFile(file, userId);
//            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully: " + fileName);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed!");
//        }
//    }

//    @PostMapping("/{userId}")
//    public ResponseEntity<String> uploadFile(@RequestParam("fileUpload") MultipartFile file, @PathVariable Integer userId) {
//        try {
//            // Call async file upload
//            BloggerService.storeFileAsync(file, userId);
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body("File upload started. You will be notified once it's complete.");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed due to an error.");
//        }
//    }

}

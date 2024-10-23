package com.project.CRUD.controller;

import com.project.CRUD.entity.Blogger;

import com.project.CRUD.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
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

package com.project.CRUD.controller;

import org.springframework.ui.Model;
import com.project.CRUD.DTO.DTO;
import com.project.CRUD.entity.Blogger;
import com.project.CRUD.entity.FileData;
import com.project.CRUD.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/blogger")
public class BloggerController {


    @Autowired
    private BloggerService bloggerService;

    @GetMapping("user/registration")
    public String showRegistrationForm(WebRequest request, Model model){
        DTO dto = new DTO();
        model.addAttribute("user",dto);
        return "registration";

    }

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

    @GetMapping("/download/blogger/{userID}/{fileID}")
    public ResponseEntity<byte[]> downloadFile(
            @PathVariable Integer userID,
            @PathVariable Integer fileID) {
        try {
            // Retrieve file data from the service
            byte[] fileData = bloggerService.getFileData(fileID, userID);

            if (fileData == null) {
                return ResponseEntity.notFound().build();
            }

            // Set headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("downloaded-file")
                    .build());

            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @GetMapping("/preview/blogger/{userID}")
//    public ResponseEntity<List<Map<String, Object>>> previewMultipleFiles(@PathVariable Integer userID) {
//        try {
//            // Retrieve all files associated with the given user ID
//            List<FileData> files = bloggerService.getAllFilesForUser(userID);
//
//            // Check if the user has files to preview
//            if (files.isEmpty()) {
//                return ResponseEntity.notFound().build();
//            }
//
//            // Convert each file's data to a preview-friendly format (Base64 encoding for file content)
//            List<Map<String, Object>> fileList = files.stream().map(fileData -> {
//                Map<String, Object> fileMap = new HashMap<>();
//                fileMap.put("fileName", fileData.getFileName());
//                fileMap.put("fileType", fileData.getFileType());
//                fileMap.put("fileSize", fileData.getFileSize());
//                fileMap.put("fileContent", Base64.getEncoder().encodeToString(fileData.getFileData())); // Encode content to Base64
//                return fileMap;
//            }).collect(Collectors.toList());
//
//            // Return the list of file previews
//            return ResponseEntity.ok(fileList);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    } ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }

}

package com.project.CRUD.service;
import com.project.CRUD.dao.BloggerDao;
import com.project.CRUD.entity.Blogger;
import jakarta.persistence.criteria.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class BloggerService {
    @Autowired
    private BloggerDao bloggerDao;

//    private final Path root = Paths.get("uploads");

    public Blogger saveblogger(Blogger blogger) {
        return bloggerDao.save(blogger);
    }

    public List<Blogger> getBloggers() {
        List<Blogger> bloggers = new ArrayList<>();
        bloggerDao.findAll().forEach(bloggers::add);
        return bloggers;
    }

    public Blogger getBloggers(Integer userID) {
        return bloggerDao.findById(userID).orElseThrow();
    }

    public void deleteBlogger(Integer userID) {
        bloggerDao.deleteById(userID);
    }

    public Blogger updateBlogger(Blogger blogger) {
        bloggerDao.findById(blogger.getUserID()).orElseThrow();
        return bloggerDao.save(blogger);
    }




    @Async
    public CompletableFuture<String> saveFile(byte[] fileData, String fileName,Integer userID ) {
        try {
            File file = new File("uploads/" + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileData);
            fos.close();
            return CompletableFuture.completedFuture("File uploaded successfully: " + fileName);
        } catch (Exception e) {
            return CompletableFuture.completedFuture("Error uploading file: " + e.getMessage());
        }
    }
}



//    public String storeFile(MultipartFile file, Integer userId) throws IOException {
//        // Ensure the directory exists
//        if (!Files.exists(root)) {
//            Files.createDirectories(root);
//        }
//
//        // Get the file name
//        String fileName = file.getOriginalFilename();
//
//        // Check if fileName is null or empty
//        if (fileName == null || fileName.isEmpty()) {
//            throw new IOException("File name is invalid or empty.");
//        }
//
//        // Ensure uniqueness by appending a timestamp or userId to avoid overwriting
//        fileName = userId + "_" + System.currentTimeMillis() + "_" + fileName;
//
//        // Resolve the file path and copy the file
//        Path filePath = root.resolve(fileName);
//        Files.copy(file.getInputStream(), filePath);
//
//        // Update the blogger entity with the uploaded file name
//        Blogger blogger = getBloggers(userId);
//        blogger.setFileUpload(fileName);
//        saveblogger(blogger);
//
//        return fileName;
//    }



//
//    @Async
//    public void storeFileAsync(MultipartFile file, Integer userId) throws IOException {
//        // Ensure the directory exists
//        if (!Files.exists(root)) {
//            Files.createDirectories(root);
//        }
//
//        // Get the file name
//        String fileName = file.getOriginalFilename();
//
//        if (fileName == null || fileName.isEmpty()) {
//            throw new IOException("File name is invalid or empty.");
//        }
//
//        // Ensure a unique file name to avoid overwriting
//        fileName = userId + "_" + System.currentTimeMillis() + "_" + fileName;
//
//        // Save the file asynchronously
//        Path filePath = root.resolve(fileName);
//        Files.copy(file.getInputStream(), filePath);
//
//
//        System.out.println("File uploaded successfully: " + fileName);
//    }
//
//}

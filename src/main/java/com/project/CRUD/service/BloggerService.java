package com.project.CRUD.service;
import com.project.CRUD.dao.BloggerDao;
import com.project.CRUD.dao.FileDataRepository;
import com.project.CRUD.entity.Blogger;
//import com.project.CRUD.entity.FileData;
import com.project.CRUD.entity.FileData;
import jakarta.persistence.criteria.Path;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class BloggerService {
    @Autowired
    private BloggerDao bloggerDao;

    @Autowired
    private FileDataRepository fileDataRepository;

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
    public CompletableFuture<String> saveFiles(List<byte[]> fileDataList, List<String> fileNames, List<String> fileTypes, Integer userID) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Blogger blogger = bloggerDao.findById(userID)
                        .orElseThrow(() -> new RuntimeException("Blogger not found"));

                List<FileData> fileDataListToSave = new ArrayList<>();

                for (int i = 0; i < fileDataList.size(); i++) {
                    byte[] fileData = fileDataList.get(i);
                    String fileName = fileNames.get(i);
                    String fileType = fileTypes.get(i);

                    // Create a new FileData object for each file
                    FileData file = new FileData();
                    file.setFileData(fileData);
                    file.setFileName(fileName);
                    file.setFileType(fileType);
                    file.setBlogger(blogger);  // Associate the file with the blogger

                    // Add the file to the list
                    fileDataListToSave.add(file);
                }

                // Save all FileData objects to the database in one batch
                fileDataRepository.saveAll(fileDataListToSave);

                return "Files uploaded successfully!";
            } catch (Exception e) {
                throw new RuntimeException("File upload failed: " + e.getMessage());
            }
        });
    }
    public byte[] getFileData(Integer fileID, Integer userID) {
        // Retrieve file data from the database
        FileData fileData = fileDataRepository.findById(fileID)
                .orElseThrow(() -> new RuntimeException("File not found"));

        // Check if the file is associated with the correct blogger (userID)
        if (!fileData.getBlogger().getUserID().equals(userID)) {
            throw new RuntimeException("Unauthorized access to this file");
        }

        return fileData.getFileData();  // Return the file content as byte array
    }

    public List<FileData> getAllFilesForUser(Integer userID) {
        Blogger blogger = bloggerDao.findById(userID)
                .orElseThrow(() -> new RuntimeException("Blogger not found"));

        // Assuming `FileData` has a relationship to `Blogger`
        return fileDataRepository.findByBlogger(blogger); // Retrieves all files for the blogger
    }




}



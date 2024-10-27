package com.project.CRUD.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "blogger")
public class Blogger {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userID; // Ensure this field matches your JSON if `UserID` is being sent as `userID`.
    private String userName;
    private String title;
    private String blogPost;
    private String blogComment;
    private String blogLikes;
    private String blogDislike;



    private String fileName;
    private long fileSize;
    private String fileType;
    private LocalDateTime uploadTime;



    public Blogger() {
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(String blogPost) {
        this.blogPost = blogPost;
    }

    public String getBlogComment() {
        return blogComment;
    }

    public void setBlogComment(String blogComment) {
        this.blogComment = blogComment;
    }

    public String getBlogLikes() {
        return blogLikes;
    }

    public void setBlogLikes(String blogLikes) {
        this.blogLikes = blogLikes;
    }

    public String getBlogDislike() {
        return blogDislike;
    }

    public void setBlogDislike(String blogDislike) {
        this.blogDislike = blogDislike;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }



// Getters and Setters
}

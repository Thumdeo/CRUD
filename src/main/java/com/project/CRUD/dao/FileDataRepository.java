package com.project.CRUD.dao;

import com.project.CRUD.entity.Blogger;
import com.project.CRUD.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Integer> {
    List<FileData> findByBlogger(Blogger blogger);
}
package com.project.CRUD.dao;


import com.project.CRUD.entity.Blogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerDao extends CrudRepository<Blogger, Integer> {

}

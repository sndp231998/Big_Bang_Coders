package com.big.bang.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.big.bang.entities.Category;
import com.big.bang.entities.User;


public interface CategoryRepo extends JpaRepository <Category, Integer> {
//    List<Category> findByCategoryType(String categoryType);
    
    
    
   
}
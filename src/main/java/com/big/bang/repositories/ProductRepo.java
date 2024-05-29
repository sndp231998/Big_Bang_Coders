package com.big.bang.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.big.bang.entities.Category;
import com.big.bang.entities.Product;
import com.big.bang.entities.User;

public interface ProductRepo extends JpaRepository<Product ,Integer> {
	List<Product> findByUser(User user);
	List<Product> findByCategory(Category category);	
	
	@Query("select p from Waste p where p.name like :key")
	List<Product> searchByName(@Param("key") String name);
	
}

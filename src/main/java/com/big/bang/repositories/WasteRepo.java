package com.big.bang.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.big.bang.entities.Category;
import com.big.bang.entities.User;
import com.big.bang.entities.Waste;



public interface WasteRepo extends JpaRepository<Waste, Integer>{

	List<Waste> findByUser(User user);
	List<Waste> findByCategory(Category category);	
	
	@Query("select w from Waste w where w.name like :key")
	List<Waste> searchByName(@Param("key") String name);
}

package com.big.bang.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.big.bang.entities.Collector;
import com.big.bang.entities.User;
import com.big.bang.entities.Waste;


public interface CollectorRepo extends JpaRepository<Collector, Integer> {
	
    List<Collector> findByWaste(Waste waste);
    
    List<Collector> findByUser(User user);
}
package com.big.bang.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.big.bang.entities.Salesp;

public interface SalesPRepo extends JpaRepository<Salesp, Integer> {
    List<Salesp> findByProductProductId(Integer productId);
    List<Salesp> findByUserUserId(Integer userId);
}
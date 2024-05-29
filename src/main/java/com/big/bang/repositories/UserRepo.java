package com.big.bang.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.big.bang.entities.User;


public interface UserRepo  extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
}

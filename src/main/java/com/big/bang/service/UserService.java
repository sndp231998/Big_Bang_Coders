package com.big.bang.service;

import java.util.List;

import com.big.bang.playloads.UserDto;


public interface UserService {
	
UserDto registerNewUser(UserDto user);
	
	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);
}

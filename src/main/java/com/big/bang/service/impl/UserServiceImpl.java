package com.big.bang.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.big.bang.entities.User;
import com.big.bang.exception.ResourceNotFoundException;
import com.big.bang.playloads.UserDto;
import com.big.bang.repositories.UserRepo;
import com.big.bang.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public User dtoToUser(UserDto userDto) {
        return this.modelMapper.map(userDto, User.class);
    }

    public UserDto userToDto(User user) {
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User newUser = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(newUser);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User newUser = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(newUser);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User existingUser = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
            existingUser.setName(userDto.getName());
            existingUser.setEmail(userDto.getEmail());
            existingUser.setPassword(userDto.getPassword());
            existingUser.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(existingUser);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User foundUser = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.userToDto(foundUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User foundUser = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(foundUser);
    }
}

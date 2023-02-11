package com.hackathon.services.impl;
import com.hackathon.exceptions.*;
import com.hackathon.entities.User;
import com.hackathon.payloads.UserDto;
import com.hackathon.repositories.UserRepo;
import com.hackathon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user->this.usertodto(user)).collect(Collectors.toList());
        return userDtos;
    }
    @Override
    public UserDto createUser(UserDto userDto){
        User user = this.dtotouser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.usertodto(savedUser);
    }
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId){
        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setDepartment(userDto.getDepartment());
        user.setDesignation(userDto.getDesignation());
        user.setPassword(userDto.getPassword());
        user.setEmp_code(userDto.getEmp_code());
        user.setEmail(userDto.getEmail());
        User updatedUser = this.userRepo.save(user);
        return this.usertodto(updatedUser);
    }
    @Override
    public UserDto getUserById(Integer userId){
        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return this.usertodto(user);
    }

    @Override
    public void deleteUser(Integer userId){
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);

    }


    private User dtotouser (UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setDepartment(userDto.getDepartment());
        user.setDesignation(userDto.getDesignation());
        user.setPassword(userDto.getPassword());
        user.setEmp_code(userDto.getEmp_code());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public UserDto usertodto(User user){
         UserDto userDto = new UserDto();
         userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setDepartment(user.getDepartment());
        userDto.setDesignation(user.getDesignation());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setEmp_code(user.getEmp_code());
        return userDto;
    }
}

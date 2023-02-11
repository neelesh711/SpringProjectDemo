package com.hackathon.services;
import com.hackathon.entities.User;
import com.hackathon.payloads.UserDto;
import java.util.List;
public interface UserService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);

}

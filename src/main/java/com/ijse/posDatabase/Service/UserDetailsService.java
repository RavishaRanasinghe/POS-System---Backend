package com.ijse.posDatabase.Service;

import java.util.List;

import com.ijse.posDatabase.Entity.User;
import com.ijse.posDatabase.dto.UserDTO;

public interface UserDetailsService {
    User findByUsername(UserDTO userDTO);
    User getUserById(Long id); //read user by user_id
    List<User> getAllUsers();  //read all users

}

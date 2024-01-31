package com.ijse.posDatabase.Service;

import com.ijse.posDatabase.Entity.User;
import com.ijse.posDatabase.dto.UserDTO;

public interface UserDetailsService {
    User findByUsername(UserDTO userDTO);
}

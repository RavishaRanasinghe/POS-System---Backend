package com.ijse.posDatabase.Service;

import java.util.HashMap;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ijse.posDatabase.Entity.User;
import com.ijse.posDatabase.Repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = UserRepository.findByUsername(username); // Get user
    // Add additional data for JWT claims (e.g., user ID)
    Map<String, String> claims = new HashMap<>();
    claims.put("userId", user.getId().toString());

    return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.getAuthorities(),
            claims
    );
}

}


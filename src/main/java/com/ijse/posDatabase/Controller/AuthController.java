package com.ijse.posDatabase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.posDatabase.dto.LoginDTO;
import com.ijse.posDatabase.Entity.User;
import com.ijse.posDatabase.Repository.UserRepository;
import com.ijse.posDatabase.Security.jwt.JwtUtils;
import com.ijse.posDatabase.Service.UserDetailsService;


@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    UserDetailsService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
    
    @PostMapping("/auth/login") 
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        
        if(userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already in use");
        }

        if(userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("This email is being used");
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return ResponseEntity.ok(userService.createUser(newUser));
    }
}

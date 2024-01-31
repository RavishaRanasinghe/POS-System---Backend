package com.ijse.posDatabase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijse.posDatabase.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    static User findByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }
}

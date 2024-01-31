package com.ijse.posDatabase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijse.posDatabase.Entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
    
}

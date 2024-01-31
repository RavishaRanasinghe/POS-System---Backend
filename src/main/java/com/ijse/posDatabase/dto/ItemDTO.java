package com.ijse.posDatabase.dto;

import com.ijse.posDatabase.Entity.ItemCategory;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    
    private Long id;
    private String name;

    private Double price;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private ItemCategory category;
}

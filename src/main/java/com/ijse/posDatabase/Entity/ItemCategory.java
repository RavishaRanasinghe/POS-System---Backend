package com.ijse.posDatabase.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class ItemCategory {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;

@ManyToOne
private ItemCategory parentCategory;

}

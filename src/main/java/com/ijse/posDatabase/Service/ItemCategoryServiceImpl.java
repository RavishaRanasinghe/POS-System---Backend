package com.ijse.posDatabase.Service;

import com.ijse.posDatabase.Entity.ItemCategory;
import com.ijse.posDatabase.Repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public ItemCategory saveCategory(ItemCategory category) {
        return itemCategoryRepository.save(category);
    }

    @Override
    public List<ItemCategory> getAllCategories() {
        return itemCategoryRepository.findAll();
    }

    // ... other methods for retrieving by ID, updating, and deleting categories
}


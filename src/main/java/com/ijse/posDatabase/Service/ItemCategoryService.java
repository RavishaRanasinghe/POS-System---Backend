package com.ijse.posDatabase.Service;

import com.ijse.posDatabase.Entity.ItemCategory;

import java.util.List;
import java.util.Locale.Category;

public interface ItemCategoryService {

    ItemCategory saveCategory(ItemCategory category);

    List<ItemCategory> getAllCategories();

    static Category getCategoryById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategoryById'");
    }

    // ... other methods for retrieving by ID, updating, and deleting categories
}


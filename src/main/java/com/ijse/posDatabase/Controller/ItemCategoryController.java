package com.ijse.posDatabase.Controller;

import com.ijse.posDatabase.Entity.ItemCategory;
import com.ijse.posDatabase.Service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale.Category;

@RestController
@RequestMapping("/api/categories")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @PostMapping("/categories")
    public ResponseEntity<ItemCategory> createCategory(@RequestBody ItemCategory category) {
        ItemCategory savedCategory = itemCategoryService.saveCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ItemCategory>> getAllCategories() {
        List<ItemCategory> categories = itemCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // ... other methods for retrieving by ID, updating, and deleting categories
    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return ItemCategoryService.getCategoryById(id);
    }
}

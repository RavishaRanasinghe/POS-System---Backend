package com.ijse.posDatabase.Controller;

import com.ijse.posDatabase.Entity.Item;
import com.ijse.posDatabase.Entity.ItemCategory;
import com.ijse.posDatabase.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item savedItem = itemService.saveItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    // ... other methods for retrieving by ID, updating, and deleting items
    @GetMapping("/item/{id}")  //status codes returning
    public ResponseEntity<Item> getItemById(@PathVariable Long id){
        Item item = itemService.getItemById(id);
        if(item != null){
            return ResponseEntity.status(200).body(item);
            //return product with 200 OK code
        }else{
            return ResponseEntity.status(404).body(null);
            //return 404 Error
        } 
}

@GetMapping("/category/{id}/item")
public List<Item> findByCategory(@PathVariable Long id){
    return itemService.findByCategory(id);
}
@PutMapping("/items/{itemId}/category")
public ResponseEntity<Item> updateItemCategory(@PathVariable Long itemId, @RequestBody ItemCategory category) {
    Item item = itemService.updateItemCategory(itemId, category);
    return ResponseEntity.ok(item);
}


}


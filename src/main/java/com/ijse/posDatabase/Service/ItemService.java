package com.ijse.posDatabase.Service;

import com.ijse.posDatabase.Entity.Item;
import com.ijse.posDatabase.Entity.ItemCategory;

import java.util.List;

public interface ItemService {

    Item saveItem(Item item);

    List<Item> getAllItems();

    Item getItemById(Long id);

    List<Item> findByCategory(Long id);

    Item updateItemCategory(Long itemId, ItemCategory category);

    Item findById(Long itemId);

    // ... other methods for retrieving by ID, updating, and deleting items
}

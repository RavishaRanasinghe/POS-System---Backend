package com.ijse.posDatabase.Controller;

import com.ijse.posDatabase.Entity.StockEntry;
import com.ijse.posDatabase.Repository.StockEntryRepository;
import com.ijse.posDatabase.Service.StockEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.transform.Source;

@RestController
@RequestMapping("/api/stock-entries")
public class StockEntryController {

    @Autowired
    private StockEntryService stockEntryService;

    @PostMapping
    public ResponseEntity<StockEntry> createStockEntry(@RequestBody StockEntry stockEntry) {
        StockEntry savedEntry = stockEntryService.saveStockEntry(stockEntry);
        return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockEntry> updateStockEntry(@PathVariable Long id, @RequestBody StockEntry updatedEntry) throws Exception {
        StockEntry existingEntry = stockEntryService.findById(id);
        if (existingEntry == null) {
            throw new Exception("Stock entry not found");
        }
    
        // Validation to prevent negative stock (if applicable)
        if (existingEntry.getQuantity() + updatedEntry.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity adjustment would result in negative stock");
        }
    
        updatedEntry.setId(id); // Set ID for update
        StockEntry savedEntry = stockEntryService.saveStockEntry(updatedEntry);
        return new ResponseEntity<>(savedEntry, HttpStatus.OK);
    }
    
    @GetMapping
public ResponseEntity<List<StockEntry>> getStockEntries(
        @RequestParam(required = false) Long itemId,
        @RequestParam(required = false) LocalDateTime startDate,
        @RequestParam(required = false) LocalDateTime endDate,
        @RequestParam(required = false) Source source) {
    
    List<StockEntry> filteredEntries = StockEntryRepository.findByCriteria(itemId, startDate, endDate, source);
    return new ResponseEntity<>(filteredEntries, HttpStatus.OK);
}

@GetMapping("/levels/{itemId}")
public ResponseEntity<Integer> getStockLevel(@PathVariable Long itemId) {
    Integer stockLevel = stockEntryService.calculateStockLevel(itemId);
    return new ResponseEntity<>(stockLevel, HttpStatus.OK);
}

}


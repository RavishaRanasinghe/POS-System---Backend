package com.ijse.posDatabase.Repository;

import com.ijse.posDatabase.Entity.StockEntry;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.transform.Source;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockEntryRepository extends JpaRepository<StockEntry, Long> {

    static List<StockEntry> findByCriteria(Long itemId, LocalDateTime startDate, LocalDateTime endDate, Source source) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCriteria'");
    }

    // Optional custom queries for filtering and reporting
}


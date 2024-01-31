package com.ijse.posDatabase.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.xml.transform.Source;

@Entity
@Getter
@Setter
public class StockEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Item item;

    private Integer quantity;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private Source source; // Optional enum for purchase, return, etc.

    // Getters, setters, constructors
}


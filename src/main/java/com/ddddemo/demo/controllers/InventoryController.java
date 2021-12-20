package com.ddddemo.demo.controllers;


import java.net.URI;
import java.net.URISyntaxException;

import com.ddddemo.demo.services.InventoryService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class InventoryController {
    private static final Logger logger=LogManager.getLogger(InventoryController.class);
    private final InventoryService inventoryService;

    public InventoryController(InventoryService service){
        this.inventoryService=service;
    }

    @GetMapping("inventory/{id}")
    public ResponseEntity<?> getInventoryRecord(@PathVariable Long id){
        return this.inventoryService.getInventoryRecord(id)
            .map(record->{
                try {
                    return ResponseEntity
                    .ok()
                    .location(new URI("/api/inventory/"+record.getId()))
                    .body(record);
                } catch (URISyntaxException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    
                }
            })
            .orElse(ResponseEntity.notFound().build());
    }

        
}

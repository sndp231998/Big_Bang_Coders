package com.big.bang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.big.bang.playloads.SalespDto;
import com.big.bang.service.SalesService;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    // Create a new sale
    @PostMapping("/products/{productId}/users/{userId}/wastes/{wasteId}")
    public ResponseEntity<SalespDto> createSale(@RequestBody SalespDto salesDto, 
                                                @PathVariable Integer productId, 
                                                @PathVariable Integer userId,
                                                @PathVariable Integer wasteId)
                                                {
        SalespDto createdSale = salesService.createSales(salesDto, productId, userId,wasteId);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    // Get sale by ID
    @GetMapping("/{salesId}")
    public ResponseEntity<SalespDto> getSaleById(@PathVariable Integer salesId) {
        SalespDto sale = salesService.getSalesById(salesId);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    // Get all sales
    @GetMapping
    public ResponseEntity<List<SalespDto>> getAllSales() {
        List<SalespDto> sales = salesService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    // Get sales by product
    @GetMapping("/products/{productId}")
    public ResponseEntity<List<SalespDto>> getSalesByProduct(@PathVariable Integer productId) {
        List<SalespDto> sales = salesService.getSalesByProduct(productId);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    // Get sales by user
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<SalespDto>> getSalesByUser(@PathVariable Integer userId) {
        List<SalespDto> sales = salesService.getSalesByUser(userId);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
}

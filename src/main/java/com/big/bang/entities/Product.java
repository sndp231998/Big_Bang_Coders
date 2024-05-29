package com.big.bang.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data

public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String name;
   private String description;
    private Double price;
    //private Integer stock;
    private Date addedDate;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    // getters and setters
}


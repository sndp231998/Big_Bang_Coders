package com.big.bang.entities;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Salesp {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesId;
    
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    
    
    @ManyToOne
    @JoinColumn(name = "waste_id") // Assuming waste_id is the foreign key column in the sales table
    private Waste waste;
    
    private Integer quantity;
    
    private Date addedDate; 
	 
	 
}

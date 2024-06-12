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
public class Waste {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wasteId;
	
	private String name;
	private String type;
	private String location;
	private String unit;
	private Integer quantity;
	
	private String imageName;

	private Date addedDate;
	
	@ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    
 
    
    
    
//--------------------
 

	
}

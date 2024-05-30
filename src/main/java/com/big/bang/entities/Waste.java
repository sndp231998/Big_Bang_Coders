package com.big.bang.entities;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Waste {

	private int wasteId;
	
	private String name;
	private String type;
	private String location;
	private String unit;
	private String qty;
	
	private String imageName;

	private Date addedDate;
	
	@ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    
    
//--------------------
 

	
}

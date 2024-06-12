package com.big.bang.playloads;

import java.util.Date;

import com.big.bang.entities.User;
import com.big.bang.entities.Waste;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CollectorDto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int collectorId;
	
	 private Integer wasteId;
	    private String wasteName;
	    private String location;
	    private Integer userId;
	    private String userName;
	   
	    private Date addedDate;
	    private Date collectDate; 
	    private Integer quantity;
	   
	    private String userEmail;
	    
	   
	    
	    
  //private UserDto user;
	    
  //private WasteDto waste;
}

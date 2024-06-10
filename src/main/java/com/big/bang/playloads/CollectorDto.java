package com.big.bang.playloads;

import java.util.Date;

import com.big.bang.entities.User;
import com.big.bang.entities.Waste;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CollectorDto {

	private int collectorId;
	
	 private Integer wasteId;
	    private String wasteName;
	    private String location;
	    private Integer userId;
	    private String userName;
	   
	    private Date addedDate;
	    private Date collectDate; 
	    private String quantity;
	   
	    private String userEmail;
	    
	   
	    
	    
  //private UserDto user;
	    
  //private WasteDto waste;
}

package com.big.bang.playloads;

import java.util.Date;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class WasteDto {

	private int wasteId;
	
	private String name;
	private String type;
	private String location;
	private String unit;
	private String qty;
	
private String imageName;
	
	private Date addedDate;	
	
	
	private CategoryDto category;

	private UserDto user;
	
	
	
	
}


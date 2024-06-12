package com.big.bang.playloads;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class WasteDto {
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
	
	
	private CategoryDto category;

	private UserDto user;
	
	
	
	
}


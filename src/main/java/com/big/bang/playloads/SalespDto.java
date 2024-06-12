package com.big.bang.playloads;



import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class SalespDto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer salesId;
	
    private Integer productId;
    
    private Integer wasteId;
    
    private Integer coins;
    
    private String productName;
    private Double productPrice;
    private Integer userId;
    private String userName;
    private String userEmail;
    private Date addedDate;
    private Integer quantity;
}
package com.big.bang.playloads;



import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class SalespDto {

    private Integer salesId;
    private Integer productId;
    private String productName;
    private Double productPrice;
    private Integer userId;
    private String userName;
    private String userEmail;
    private Date addedDate;
}
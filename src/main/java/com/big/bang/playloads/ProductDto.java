package com.big.bang.playloads;

import java.util.Date;

import com.big.bang.entities.Category;
import com.big.bang.entities.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ProductDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Date addedDate;
//    @ManyToOne
//    @JoinColumn(name = "categoryId")
    private Category category;
    
//    @ManyToOne
//    @JoinColumn(name = "userId")
    private User user;
}

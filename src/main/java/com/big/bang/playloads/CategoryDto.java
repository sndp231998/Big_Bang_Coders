package com.big.bang.playloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    private String name;
    private String description;
    private String categoryType; // "PRODUCT" or "WASTE"
}

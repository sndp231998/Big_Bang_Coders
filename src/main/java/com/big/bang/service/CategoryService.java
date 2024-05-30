package com.big.bang.service;

import java.util.List;


import com.big.bang.playloads.CategoryDto;




public interface CategoryService {
	
	
    List<CategoryDto> getCategoriesByType(String categoryType);
//    
// // create
    CategoryDto createCategory(CategoryDto categoryDto);
    
    
    
    //Get All
 // get All

 	List<CategoryDto> getCategories();
    
    
	// get by id
	CategoryDto getCategory(Integer categoryId);
    
    
    
//
// 	// update
// 	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
//
// 	// delete
// 	void deleteCategory(Integer categoryId);
//
 
//


}


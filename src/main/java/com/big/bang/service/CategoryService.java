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
    
    
    
    
    
    
//
// 	// update
// 	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
//
// 	// delete
// 	void deleteCategory(Integer categoryId);
//
// 	// get
// 	CategoryDto getCategory(Integer categoryId);
//
// 	// get All
//
// 	List<CategoryDto> getCategories();

}


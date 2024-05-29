package com.big.bang.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.big.bang.entities.Category;
import com.big.bang.playloads.CategoryDto;
import com.big.bang.repositories.CategoryRepo;
import com.big.bang.service.CategoryService;

public class CategoryServiceImpl  implements CategoryService {

	 @Autowired
	    private CategoryRepo categoryRepo;

	 @Autowired
		private ModelMapper modelMapper;

		@Override
		public CategoryDto createCategory(CategoryDto categoryDto) {
			Category cat = this.modelMapper.map(categoryDto, Category.class);
			Category addedCat = this.categoryRepo.save(cat);
			return this.modelMapper.map(addedCat, CategoryDto.class);
		}
	 
	 
	 
	 
		@Override
	    public List<CategoryDto> getCategoriesByType(String categoryType) {
	        List<Category> categories = this.categoryRepo.findByCategoryType(categoryType);
	        return categories.stream()
	                .map(cat -> this.modelMapper.map(cat, CategoryDto.class))
	                .collect(Collectors.toList());
	    }

	
	@Override
	public List<CategoryDto> getCategories() {

		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());

		return catDtos;
	
	
	}
   
}
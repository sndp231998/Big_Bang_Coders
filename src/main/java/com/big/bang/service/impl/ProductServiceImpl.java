package com.big.bang.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.big.bang.entities.Category;
import com.big.bang.entities.Product;
import com.big.bang.entities.User;
import com.big.bang.entities.Waste;
import com.big.bang.exception.ResourceNotFoundException;
import com.big.bang.playloads.ProductDto;
import com.big.bang.playloads.WasteDto;
import com.big.bang.repositories.CategoryRepo;
import com.big.bang.repositories.ProductRepo;
import com.big.bang.repositories.UserRepo;

import com.big.bang.service.ProductService;

public class ProductServiceImpl implements ProductService {

	 @Autowired
	    private ProductRepo productRepo;

	    @Autowired
	    private ModelMapper modelMapper;

	    @Autowired
	    private UserRepo userRepo;

	    @Autowired
	    private CategoryRepo categoryRepo;
	
	@Override
	public ProductDto createProduct(ProductDto productDto, Integer userId, Integer categoryId) {
		 User user = this.userRepo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

	        Category category = this.categoryRepo.findById(categoryId)
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryId));

	        Product product = this.modelMapper.map(productDto, Product.class);
	       // product.setImageName("default.png");
	        product.setAddedDate(new Date());
	        product.setUser(user);
	        product.setCategory(category);
	        

	        Product newWaste = this.productRepo.save(product);

	        return this.modelMapper.map(newWaste, WasteDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(Integer productId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductDto> getProductsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> getProductsByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> searchProducts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}

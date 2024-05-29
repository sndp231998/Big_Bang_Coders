package com.big.bang.service;

import java.util.List;

import com.big.bang.playloads.ProductDto;


public interface ProductService {
ProductDto createProduct(ProductDto productDto, Integer userId ,Integer categoryId);
	

	ProductDto updateProduct(ProductDto productDto, Integer productId);
	

	ProductDto getProductById(Integer productId);

	List<ProductDto> getAllProducts();

	void deleteProduct(Integer productId);
	
	

	List<ProductDto> getProductsByCategory(Integer categoryId);
	
	//get all products by user
	List<ProductDto> getProductsByUser(Integer userId);
	
	//search products
	List<ProductDto> searchProducts(String keyword);
}

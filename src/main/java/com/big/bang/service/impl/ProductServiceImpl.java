package com.big.bang.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.big.bang.entities.Category;
import com.big.bang.entities.Product;
import com.big.bang.entities.User;

import com.big.bang.exception.ResourceNotFoundException;
import com.big.bang.playloads.ProductDto;

import com.big.bang.repositories.CategoryRepo;
import com.big.bang.repositories.ProductRepo;
import com.big.bang.repositories.UserRepo;

import com.big.bang.service.ProductService;
@Service
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
	        

	        Product newProduct = this.productRepo.save(product);

	        return this.modelMapper.map(newProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer productId) {
		  Product product = this.productRepo.findById(productId)
	                .orElseThrow(() -> new ResourceNotFoundException("Product ", "product id", productId));

	        Category category = this.categoryRepo.findById(productDto.getCategory().getCategoryId()).get();

	        product.setName(productDto.getName());
	        product.setDescription(productDto.getDescription());
	        //product.setCategory(productDto.getCategory());
	        product.setPrice(productDto.getPrice());
	        product.setQuantity(productDto.getQuantity());
	 
	       // product.setImageName(productDto.getImageName());
	              product.setCategory(category);


	        Product updatedProduct = this.productRepo.save(product);
	        return this.modelMapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		Product product = this.productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "product id", productId));
        return this.modelMapper.map(product, ProductDto.class);	
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = this.productRepo.findAll();
        return products.stream()
                .map(product -> this.modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
	}

	@Override
	public void deleteProduct(Integer productId) {
		Product product = this.productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product ", "product id", productId));

        this.productRepo.delete(product);
		
	}

	@Override
	public List<ProductDto> getProductsByCategory(Integer categoryId) {
		 Category cat = this.categoryRepo.findById(categoryId)
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
	        List<Product> products = this.productRepo.findByCategory(cat);

	        List<ProductDto> productDtos = products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class))
	                .collect(Collectors.toList());

	        return productDtos;
	}

	@Override
	public List<ProductDto> getProductsByUser(Integer userId) {
		 User user = this.userRepo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userId));
	        List<Product> products = this.productRepo.findByUser(user);

	        List<ProductDto> productDtos = products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class))
	                .collect(Collectors.toList());

	        return productDtos;
	}

	@Override
	public List<ProductDto> searchProducts(String keyword) {
		List<Product> products = this.productRepo.searchByName("%" + keyword + "%");
        List<ProductDto> productDtos = products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return productDtos;
	}

}

package com.big.bang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.big.bang.playloads.ApiResponse;
import com.big.bang.playloads.ProductDto;
import com.big.bang.playloads.WasteDto;
import com.big.bang.service.ProductService;


@RestController
@RequestMapping("/api/v1/")
public class ProductController {

	@Autowired
	private ProductService productService;

	
//	create product
	@PostMapping("/user/{userId}/category/{categoryId}/products")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		ProductDto createProduct = this.productService.createProduct(productDto, userId, categoryId);
		return new ResponseEntity<ProductDto>(createProduct, HttpStatus.CREATED);
	}
	
	// get by user
		@GetMapping("/user/{userId}/products")
		public ResponseEntity<List<ProductDto>> getProductsByUser(@PathVariable Integer userId) {

			List<ProductDto> products = this.productService.getProductsByUser(userId);
			return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);

		}
		
		// get by category
				@GetMapping("/category/{categoryId}/products")
				public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Integer categoryId) {

					List<ProductDto> products = this.productService.getProductsByCategory(categoryId);
					return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);

				}
				// get product details by id

				@GetMapping("/products/{productId}")
				public ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId) {

					ProductDto productDto = this.productService.getProductById(productId);
					return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);

				}
				
				
				// delete product
				@DeleteMapping("/products/{productId}")
				public ApiResponse deleteProduct(@PathVariable Integer productId) {
					this.productService.deleteProduct(productId);
					return new ApiResponse("product is successfully deleted !!", true);
				}
				
				// update product

				@PutMapping("/products/{productId}")
				public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer productId) {

					ProductDto updateProduct = this.productService.updateProduct(productDto, productId);
					return new ResponseEntity<ProductDto>(updateProduct, HttpStatus.OK);

				}

				// search product
				@GetMapping("/products/search/{keywords}")
				public ResponseEntity<List<ProductDto>> searchProductByName(@PathVariable("keywords") String keywords) {
					List<ProductDto> result = this.productService.searchProducts(keywords);
					return new ResponseEntity<List<ProductDto>>(result, HttpStatus.OK);
				}
				
				   //getall Product
				 
				 		@GetMapping("/products/")
				 		public ResponseEntity<List<ProductDto>> getAllProducts() {
				 			return ResponseEntity.ok(this.productService.getAllProducts());
				 		}
}

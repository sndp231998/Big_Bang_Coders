package com.big.bang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.big.bang.playloads.WasteDto;
import com.big.bang.service.FileService;
import com.big.bang.service.ProductService;
import com.big.bang.service.WasteService;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {

	@Autowired
	private ProductService productService;

	
//	create product
	@PostMapping("/user/{userId}/category/{categoryId}/wastes")
	public ResponseEntity<WasteDto> createWaste(@RequestBody WasteDto wasteDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		WasteDto createWaste = this.wasteService.createWaste(wasteDto, userId, categoryId);
		return new ResponseEntity<WasteDto>(createWaste, HttpStatus.CREATED);
	}
	
	// get by user
		@GetMapping("/user/{userId}/wastes")
		public ResponseEntity<List<WasteDto>> getWastesByUser(@PathVariable Integer userId) {

			List<WasteDto> wastes = this.wasteService.getWastesByUser(userId);
			return new ResponseEntity<List<WasteDto>>(wastes, HttpStatus.OK);

		}
}

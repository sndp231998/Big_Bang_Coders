package com.big.bang.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.big.bang.playloads.ApiResponse;
import com.big.bang.playloads.WasteDto;
import com.big.bang.service.FileService;
import com.big.bang.service.WasteService;

import jakarta.servlet.http.HttpServletResponse;






@RestController
@RequestMapping("/api/v1/")
public class WasteController {

	
	@Autowired
	private WasteService wasteService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;
	
	
//	create
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
		
		// get by category
		@GetMapping("/category/{categoryId}/wastes")
		public ResponseEntity<List<WasteDto>> getWastesByCategory(@PathVariable Integer categoryId) {

			List<WasteDto> wastes = this.wasteService.getWastesByCategory(categoryId);
			return new ResponseEntity<List<WasteDto>>(wastes, HttpStatus.OK);

		}
		
		// get waste details by id

		@GetMapping("/wastes/{wasteId}")
		public ResponseEntity<WasteDto> getWasteById(@PathVariable Integer wasteId) {

			WasteDto wasteDto = this.wasteService.getWasteById(wasteId);
			return new ResponseEntity<WasteDto>(wasteDto, HttpStatus.OK);

		}

		// delete waste
		@DeleteMapping("/wastes/{wasteId}")
		public ApiResponse deleteWaste(@PathVariable Integer wasteId) {
			this.wasteService.deleteWaste(wasteId);
			return new ApiResponse("waste is successfully deleted !!", true);
		}
		
		// update waste

		@PutMapping("/wastes/{wasteId}")
		public ResponseEntity<WasteDto> updateWaste(@RequestBody WasteDto wasteDto, @PathVariable Integer wasteId) {

			WasteDto updateWaste = this.wasteService.updateWaste(wasteDto, wasteId);
			return new ResponseEntity<WasteDto>(updateWaste, HttpStatus.OK);

		}

		// search waste
		@GetMapping("/wastes/search/{keywords}")
		public ResponseEntity<List<WasteDto>> searchWasteByName(@PathVariable("keywords") String keywords) {
			List<WasteDto> result = this.wasteService.searchWastes(keywords);
			return new ResponseEntity<List<WasteDto>>(result, HttpStatus.OK);
		}

		// waste image upload

		@PostMapping("/waste/image/upload/{wasteId}")
		public ResponseEntity<WasteDto> uploadWasteImage(@RequestParam("image") MultipartFile image,
				@PathVariable Integer wasteId) throws IOException {

			WasteDto wasteDto = this.wasteService.getWasteById(wasteId);
			
			String fileName = this.fileService.uploadImage(path, image);
			wasteDto.setImageName(fileName);
			WasteDto updateWaste = this.wasteService.updateWaste(wasteDto, wasteId);
			return new ResponseEntity<WasteDto>(updateWaste, HttpStatus.OK);

		}
		
		 //method to serve files
	    @GetMapping(value = "/waste/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	    public void downloadImage(
	            @PathVariable("imageName") String imageName,
	            HttpServletResponse response
	    ) throws IOException {

	        InputStream resource = this.fileService.getResource(path, imageName);
	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        StreamUtils.copy(resource,response.getOutputStream())   ;

	    }
	    
	    //getall waste
	 // GET - 
	 		@GetMapping("/wastes/")
	 		public ResponseEntity<List<WasteDto>> getAllWastes() {
	 			return ResponseEntity.ok(this.wasteService.getAllWastes());
	 		}
		
		
}
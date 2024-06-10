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

import com.big.bang.playloads.CollectorDto;
import com.big.bang.playloads.ProductDto;
import com.big.bang.playloads.SalespDto;
import com.big.bang.service.CollectorService;


@RestController
@RequestMapping("/api/v1/collector")
public class CollectorController {

	
	 @Autowired
	    private CollectorService collectorService;

	 
	 
	    // Create a new collector
	    @PostMapping("/wastes/{wasteId}/users/{userId}")
	    public ResponseEntity<CollectorDto> createCollector(@RequestBody CollectorDto collectorDto, 
	                                                @PathVariable Integer wasteId, 
	                                                @PathVariable Integer userId) {
	        CollectorDto createdCollector = collectorService.createCollector(collectorDto, wasteId, userId);
	        return new ResponseEntity<>(createdCollector, HttpStatus.CREATED);
	    }
//	    
//	    // Get collector by ID
	    @GetMapping("/{collectorId}")
	    public ResponseEntity<CollectorDto> getCollectorById(@PathVariable Integer collectorId) {
	        CollectorDto collector = collectorService.getCollectorById(collectorId);
	        return new ResponseEntity<>(collector, HttpStatus.OK);
	    }
//	    
//	    // Get all Collector
	    @GetMapping
	    public ResponseEntity<List<CollectorDto>> getAllCollector() {
	        List<CollectorDto> collect = collectorService.getAllCollectors();
	        return new ResponseEntity<>(collect, HttpStatus.OK);
	    }
	    
	    
	    
	 // Get collecteds by user
//	    @GetMapping("/users/{userId}")
//	    public ResponseEntity<List<CollectorDto>> getCollectorByUser(@PathVariable Integer userId) {
//	        List<CollectorDto> collected = collectorService.getCollectorByUser(userId);
//	        return new ResponseEntity<>(collected, HttpStatus.OK);
//	    }
	    @GetMapping("/user/{userId}/collectors")
		public ResponseEntity<List<CollectorDto>> getCollectorsByUser(@PathVariable Integer userId) {

			List<CollectorDto> collectors = this.collectorService.getCollectorsByUser(userId);
			return new ResponseEntity<List<CollectorDto>>(collectors, HttpStatus.OK);

	    }
	    
	 // Get collected  by waste
          @GetMapping("/wastes/{wasteId}")
	    public ResponseEntity<List<CollectorDto>> getCollectorByWaste(@PathVariable Integer wasteId) {
          List<CollectorDto> collectors = this.collectorService.getCollectorsByWaste(wasteId);
	        return new ResponseEntity<List<CollectorDto>>(collectors,HttpStatus.OK);
//	    }
//	    
		
          }}

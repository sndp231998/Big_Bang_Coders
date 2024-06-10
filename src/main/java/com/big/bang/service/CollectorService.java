package com.big.bang.service;

import java.util.List;

import com.big.bang.playloads.CollectorDto;
import com.big.bang.playloads.ProductDto;
import com.big.bang.playloads.SalespDto;

public interface CollectorService {
	
	
	
	
	 CollectorDto createCollector(CollectorDto collectorDto, Integer wasteId, Integer userId);
	    CollectorDto getCollectorById(Integer collectorId);
	    List<CollectorDto> getAllCollectors();
	    
	   List<CollectorDto> getCollectorsByWaste(Integer wasteId);
	    
	    List<CollectorDto> getCollectorsByUser(Integer userId);
	   

    
}

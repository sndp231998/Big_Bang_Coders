package com.big.bang.service;

import java.util.List;


import com.big.bang.playloads.WasteDto;


public interface WasteService {

	
	WasteDto createWaste(WasteDto wasteDto, Integer userId ,Integer categoryId);
	

	WasteDto updateWaste(WasteDto wasteDto, Integer wasteId);
	

	WasteDto getWasteById(Integer wasteId);

	List<WasteDto> getAllWastes();

	void deleteWaste(Integer wasteId);
	
	

	List<WasteDto> getWastesByCategory(Integer categoryId);
	
	//get all wastes by user
	List<WasteDto> getWastesByUser(Integer userId);
	
	//search wastes
	List<WasteDto> searchWastes(String keyword);

}

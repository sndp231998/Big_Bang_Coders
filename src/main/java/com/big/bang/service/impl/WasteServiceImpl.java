package com.big.bang.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.big.bang.entities.Category;
import com.big.bang.entities.User;
import com.big.bang.entities.Waste;
import com.big.bang.exception.ResourceNotFoundException;
import com.big.bang.playloads.WasteDto;
import com.big.bang.repositories.CategoryRepo;
import com.big.bang.repositories.UserRepo;
import com.big.bang.repositories.WasteRepo;
import com.big.bang.service.WasteService;

@Service
public class WasteServiceImpl implements WasteService{
    @Autowired
    private WasteRepo wasteRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

	@Override
	public WasteDto createWaste(WasteDto wasteDto, Integer userId, Integer categoryId) {
		  User user = this.userRepo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

	        Category category = this.categoryRepo.findById(categoryId)
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryId));

	        Waste waste = this.modelMapper.map(wasteDto, Waste.class);
	        waste.setImageName("default.png");
	        waste.setAddedDate(new Date());
	        waste.setUser(user);
	        waste.setCategory(category);

	        Waste newWaste = this.wasteRepo.save(waste);

	        return this.modelMapper.map(newWaste, WasteDto.class);
	}

	@Override
	public WasteDto updateWaste(WasteDto wasteDto, Integer wasteId) {
		  Waste waste = this.wasteRepo.findById(wasteId)
	                .orElseThrow(() -> new ResourceNotFoundException("Waste ", "waste id", wasteId));

	        Category category = this.categoryRepo.findById(wasteDto.getCategory().getCategoryId()).get();

	        waste.setLocation(wasteDto.getLocation());
	        waste.setName(wasteDto.getName());
	        
	        waste.setQty(wasteDto.getQty());
	        waste.setType(waste.getType());
	        waste.setUnit(waste.getUnit());
	        
	        waste.setImageName(wasteDto.getImageName());
	        waste.setCategory(category);


	        Waste updatedWaste = this.wasteRepo.save(waste);
	        return this.modelMapper.map(updatedWaste, WasteDto.class);
	}

	@Override
	public WasteDto getWasteById(Integer wasteId) {
		  Waste waste = this.wasteRepo.findById(wasteId)
	                .orElseThrow(() -> new ResourceNotFoundException("Waste", "waste id", wasteId));
	        return this.modelMapper.map(waste, WasteDto.class);	}

	@Override
	public List<WasteDto> getAllWastes() {
		 List<Waste> wastes = this.wasteRepo.findAll();
	        return wastes.stream()
	                .map(waste -> this.modelMapper.map(waste, WasteDto.class))
	                .collect(Collectors.toList());
	}

	@Override
	public void deleteWaste(Integer wasteId) {
		 Waste waste = this.wasteRepo.findById(wasteId)
	                .orElseThrow(() -> new ResourceNotFoundException("Waste ", "waste id", wasteId));

	        this.wasteRepo.delete(waste);

		
	}

	
	@Override
	public List<WasteDto> getWastesByCategory(Integer categoryId) {
		  Category cat = this.categoryRepo.findById(categoryId)
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
	        List<Waste> wastes = this.wasteRepo.findByCategory(cat);

	        List<WasteDto> wasteDtos = wastes.stream().map((waste) -> this.modelMapper.map(waste, WasteDto.class))
	                .collect(Collectors.toList());

	        return wasteDtos;
	}

	@Override
	public List<WasteDto> getWastesByUser(Integer userId) {
		  User user = this.userRepo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userId));
	        List<Waste> wastes = this.wasteRepo.findByUser(user);

	        List<WasteDto> wasteDtos = wastes.stream().map((waste) -> this.modelMapper.map(waste, WasteDto.class))
	                .collect(Collectors.toList());

	        return wasteDtos;
	}

	@Override
	public List<WasteDto> searchWastes(String keyword) {
		  List<Waste> wastes = this.wasteRepo.searchByName("%" + keyword + "%");
	        List<WasteDto> wasteDtos = wastes.stream().map((waste) -> this.modelMapper.map(waste, WasteDto.class)).collect(Collectors.toList());
	        return wasteDtos;
	}

}

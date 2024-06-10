package com.big.bang.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.big.bang.entities.Collector;
import com.big.bang.entities.User;
import com.big.bang.entities.Waste;
import com.big.bang.exception.ResourceNotFoundException;
import com.big.bang.playloads.CollectorDto;
import com.big.bang.repositories.CollectorRepo;
import com.big.bang.repositories.UserRepo;
import com.big.bang.repositories.WasteRepo;
import com.big.bang.service.CollectorService;

@Service
public class CollectorServiceImpl implements CollectorService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CollectorRepo collectorRepo;

    @Autowired
    private WasteRepo wasteRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public CollectorDto createCollector(CollectorDto collectorDto, Integer wasteId, Integer userId) {
        Waste waste = this.wasteRepo.findById(wasteId)
                .orElseThrow(() -> new ResourceNotFoundException("Waste", "Waste id", wasteId));
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));

        Collector collector = this.modelMapper.map(collectorDto, Collector.class);
        collector.setUser(user);
        collector.setWaste(waste);
        collector.setCollectDate(new Date());
        // Ensure addedDate is set if needed

        Collector newCollect = this.collectorRepo.save(collector);
        CollectorDto newCollectorDto = this.modelMapper.map(newCollect, CollectorDto.class);
        mapAdditionalDetails(newCollect, newCollectorDto);

        return newCollectorDto;
    }

    @Override
    public CollectorDto getCollectorById(Integer collectorId) {
        Collector collectedWaste = collectorRepo.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector", "collector id", collectorId));
        CollectorDto collectorDto = modelMapper.map(collectedWaste, CollectorDto.class);
        mapAdditionalDetails(collectedWaste, collectorDto);

        return collectorDto;
    }

    @Override
    public List<CollectorDto> getAllCollectors() {
        List<Collector> collectedWaste = collectorRepo.findAll();
        return collectedWaste.stream()
                .map(collector -> {
                    CollectorDto collectorDto = this.modelMapper.map(collector, CollectorDto.class);
                    mapAdditionalDetails(collector, collectorDto);
                    return collectorDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CollectorDto> getCollectorsByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        List<Collector> collectors = this.collectorRepo.findByUser(user);

        return collectors.stream()
                .map(collector -> {
                    CollectorDto collectorDto = this.modelMapper.map(collector, CollectorDto.class);
                    mapAdditionalDetails(collector, collectorDto);
                    return collectorDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CollectorDto> getCollectorsByWaste(Integer wasteId) {
        Waste waste = this.wasteRepo.findById(wasteId)
                .orElseThrow(() -> new ResourceNotFoundException("Waste", "wasteId", wasteId));
        List<Collector> collectors = this.collectorRepo.findByWaste(waste);

        return collectors.stream()
                .map(collector -> {
                    CollectorDto collectorDto = this.modelMapper.map(collector, CollectorDto.class);
                    mapAdditionalDetails(collector, collectorDto);
                    return collectorDto;
                })
                .collect(Collectors.toList());
    }

    // Utility methods for mapping additional details
    private void mapAdditionalDetails(Collector collector, CollectorDto collectorDto) {
        Waste waste = collector.getWaste();
        User user = collector.getUser();

        collectorDto.setWasteId(waste.getWasteId());
        //collectorDto.setWasteName(waste.getWasteName());
        collectorDto.setLocation(waste.getLocation());
        collectorDto.setUserId(user.getUserId());
        collectorDto.setQuantity(waste.getQuantity());
       // collectorDto.setUserName(user.getUserName());
        collectorDto.setUserEmail(user.getEmail());
        collectorDto.setAddedDate(waste.getAddedDate());
    }

    // Utility methods for mapping
    public Collector dtoToCollector(CollectorDto collectorDto) {
        return modelMapper.map(collectorDto, Collector.class);
    }

    public CollectorDto collectorToDto(Collector collector) {
        return modelMapper.map(collector, CollectorDto.class);
    }
}

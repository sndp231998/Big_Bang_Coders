package com.big.bang.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.big.bang.entities.Product;
import com.big.bang.entities.Salesp;
import com.big.bang.entities.User;
import com.big.bang.entities.Waste;
import com.big.bang.exception.InsufficientCoinsException;
import com.big.bang.exception.ResourceNotFoundException;
import com.big.bang.playloads.SalespDto;
import com.big.bang.repositories.ProductRepo;
import com.big.bang.repositories.SalesPRepo;
import com.big.bang.repositories.UserRepo;
import com.big.bang.repositories.WasteRepo;
import com.big.bang.service.SalesService;
@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SalesPRepo salesPRepo;
    
    @Autowired
    private WasteRepo wasteRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public SalespDto createSales(SalespDto salesDto, Integer productId, Integer userId, Integer wasteId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Product id", productId));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
        
        Waste waste = this.wasteRepo.findById(wasteId)
                .orElseThrow(() -> new ResourceNotFoundException("Waste", "Waste id", wasteId));

        User wasteOwner = waste.getUser();
        if (wasteOwner == null) {
            throw new ResourceNotFoundException("User", "User id", wasteId);
        }

        // Check if the product quantity is sufficient
        int salesQuantity = salesDto.getQuantity();
        if (product.getQuantity() < salesQuantity) {
            throw new RuntimeException("Insufficient product quantity");
        }

        Double productPriceInRupees = product.getPrice();
        Double totalCostInCoins = productPriceInRupees * 5 * salesQuantity;

        // Check if the user has enough coins
        if (wasteOwner.getCoins() < totalCostInCoins) {
        	throw new InsufficientCoinsException("Insufficient coins to purchase the product");
        }

        // Decrease the product quantity
        product.setQuantity(product.getQuantity() - salesQuantity);
        productRepo.save(product);

        // Decrease the user's coins
        wasteOwner.setCoins(wasteOwner.getCoins() - totalCostInCoins);
        userRepo.save(wasteOwner); // Corrected from user to wasteOwner

        Salesp salesProduct = new Salesp();
        salesProduct.setProduct(product);
        salesProduct.setUser(user);
        salesProduct.setWaste(waste); // Ensure waste is set here
        salesProduct.setQuantity(salesQuantity);
        salesProduct.setAddedDate(new Date()); // Ensure date is set

        Salesp newSales = salesPRepo.save(salesProduct);

        return modelMapper.map(newSales, SalespDto.class);
    }

    @Override
    public SalespDto getSalesById(Integer salesId) {
        Salesp salesProduct = salesPRepo.findById(salesId)
                .orElseThrow(() -> new ResourceNotFoundException("Sales", "sale id", salesId));
        return modelMapper.map(salesProduct, SalespDto.class);
    }

    @Override
    public List<SalespDto> getAllSales() {
        List<Salesp> salesProducts = salesPRepo.findAll();
        return salesProducts.stream()
                .map(this::salesToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalespDto> getSalesByProduct(Integer productId) {
        List<Salesp> salesProducts = salesPRepo.findByProductProductId(productId);
        return salesProducts.stream()
                .map(this::salesToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalespDto> getSalesByUser(Integer userId) {
        List<Salesp> salesProducts = salesPRepo.findByUserUserId(userId);
        return salesProducts.stream()
                .map(this::salesToDto)
                .collect(Collectors.toList());
    }

    public Salesp dtoToSales(SalespDto salespDto) {
        return modelMapper.map(salespDto, Salesp.class);
    }

    public SalespDto salesToDto(Salesp salesp) {
        return modelMapper.map(salesp, SalespDto.class);
    }
}

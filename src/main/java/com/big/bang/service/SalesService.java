package com.big.bang.service;

import java.util.List;


import com.big.bang.playloads.SalespDto;



public interface SalesService {

	SalespDto createSales(SalespDto salesDto, Integer productId, Integer userId, Integer wasteId);

    SalespDto getSalesById(Integer salesId);

    List<SalespDto> getAllSales();

    List<SalespDto> getSalesByProduct(Integer productId);

    List<SalespDto> getSalesByUser(Integer userId);
    
}
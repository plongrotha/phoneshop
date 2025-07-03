package com.phoneshop.service.impl;

import com.phoneshop.dto.ProductSoldDTO;
import com.phoneshop.dto.SaleDTO;
import com.phoneshop.service.ProductService;
import com.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final ProductService productService;


    @Override
    public void sell(SaleDTO dto) {

    }


    private void validation(SaleDTO dto) {
        dto.getProduct().stream().map(ProductSoldDTO::getProductId).forEach(
                productId -> productService.getProductById(Long.valueOf(productId))
        );
    }
}

package com.phoneshop.service.impl;

import com.phoneshop.exception.NotFoundException;
import com.phoneshop.model.entity.Product;
import com.phoneshop.repository.ProductRepository;
import com.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
    }
}

package com.phoneshop.service;


import com.phoneshop.model.entity.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product getProductById(Long productId);

}

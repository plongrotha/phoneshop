package com.phoneshop.service;


import com.phoneshop.dto.ProductImportDTO;
import com.phoneshop.model.entity.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long productId);

    void importProduct(ProductImportDTO productImportDTO);

}

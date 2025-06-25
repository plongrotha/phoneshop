package com.phoneshop.service.impl;

import com.phoneshop.dto.ProductImportDTO;
import com.phoneshop.exception.NotFoundException;
import com.phoneshop.mapper.ProductMapper;
import com.phoneshop.model.entity.Color;
import com.phoneshop.model.entity.Model;
import com.phoneshop.model.entity.Product;
import com.phoneshop.model.entity.ProductImportHistory;
import com.phoneshop.repository.ProductImportHistoryRepository;
import com.phoneshop.repository.ProductRepository;
import com.phoneshop.service.ColorService;
import com.phoneshop.service.ModelService;
import com.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ModelService modelService;
    private final ColorService colorService;
    private final ProductMapper productMapper;

    @Override
    public Product saveProduct(Product product) {

        Long modelId = product.getModel().getModelId();
        Model model = modelService.getModelById(modelId);

        Long colorId = product.getColor().getColorId();
        Color color = colorService.getColorById(colorId);


        product.setModel(model);
        product.setColor(color);

        // this for get model's name , color's name to combine it together
        String name = "%s %s".formatted(model.getModelName(), color.getColorName());
        product.setProductName(name);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new NotFoundException("No products found.");
        }
        return products;
    }

    @Cacheable(value = "myCache", key = "#productId")
    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException(" product " + productId + " not found."));
    }

    @Override
    public void importProduct(ProductImportDTO productImportDTO) {
        ProductImportHistory product = productMapper.toProduct(productImportDTO);
        productImportHistoryRepository.save(product);
    }
}

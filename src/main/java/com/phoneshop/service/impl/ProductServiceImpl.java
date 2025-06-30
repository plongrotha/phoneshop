package com.phoneshop.service.impl;

import com.phoneshop.dto.PriceDTO;
import com.phoneshop.dto.ProductImportDTO;
import com.phoneshop.exception.EntityExistException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
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

        if (product.getProductName() == name) {
            throw new EntityExistException("Product already exists");
        } else {
            product.setProductName(name);
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        log.info("getAllProducts called : " + products.size());

        if (products.isEmpty()) {
            throw new NotFoundException("No products found.");
        }
        return products;
    }

    //    @Cacheable(value = "myCache", key = "#productId")
    @Override
    public Product getProductById(Long productId) {
        log.info("getProductById called : " + productId);
        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException(" product " + productId + " not found."));
    }

    @Override
    public void importProduct(ProductImportDTO productImportDTO) {

        Product product = getProductById(productImportDTO.getProductId());

        int currentUnit = 0;
        if (product.getAvailableUnit() != null) {
            currentUnit = product.getAvailableUnit();
        }
        product.setAvailableUnit(currentUnit + productImportDTO.getImportUnit());

//        // update price
//        BigDecimal currentPrice = BigDecimal.valueOf(0);
//        if (product.getSalePrice() != null) {
//            currentPrice = product.getSalePrice();
//        }
//
//        product.setSalePrice(currentPrice.add(productImportDTO.getImportPrice()));

        productRepository.save(product);

        // save product import history
        ProductImportHistory importHistory = productMapper.toProductImportHistory(productImportDTO, product);
        productImportHistoryRepository.save(importHistory);


    }

    @Override
    public void setProductPrice(Long productId, BigDecimal price) {

        Product product = getProductById(productId);
        product.setSalePrice(price);
        productRepository.save(product);
    }
}

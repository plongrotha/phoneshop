package com.phoneshop.org.service.impl;


import com.phoneshop.org.model.Brand;
import com.phoneshop.org.repository.BrandRepository;
import com.phoneshop.org.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;


    @Override
    public Brand createbrand(Brand brand) {
        return null;
    }
}

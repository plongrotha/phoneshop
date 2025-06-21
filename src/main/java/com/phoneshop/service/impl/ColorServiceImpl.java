package com.phoneshop.service.impl;

import com.phoneshop.model.entity.Color;
import com.phoneshop.repository.ColorRepository;
import com.phoneshop.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;


    @Override
    public Color createColor(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color getColorById(Long colorId) {
        return colorRepository.findById(colorId).get();
    }
}

package com.phoneshop.service.impl;

import com.phoneshop.exception.EntityExistException;
import com.phoneshop.exception.NotFoundException;
import com.phoneshop.model.entity.Color;
import com.phoneshop.repository.ColorRepository;
import com.phoneshop.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public Color createColor(Color color) {

        Optional<Color> exist = colorRepository.findByColorNameIgnoreCase(color.getColorName());

        if (exist.isPresent()) {
            throw new EntityExistException("Color with name " + color.getColorName() + " already exists");
        }
        return colorRepository.save(color);
    }

//    @Cacheable(value = "myCache", key = "#colorId")
    @Override
    public Color getColorById(Long colorId) {
        return colorRepository.findById(colorId).orElseThrow(() -> new NotFoundException("Color id " + colorId + " not found"));
    }

//    @CacheEvict(value = "myCache", key = "#colorId")
    @Override
    public void deleteColor(Long colorId) {
        colorRepository.deleteById(colorId);
    }

//    @Cacheable("myCache")
    @Override
    public List<Color> getAllColors() {

        List<Color> colors = colorRepository.findAll();
        if (colors.isEmpty()) {
            throw new NotFoundException("No colors found.");
        }
        return colors;
    }


}

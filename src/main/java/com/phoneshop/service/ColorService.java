package com.phoneshop.service;

import com.phoneshop.model.entity.Color;
import com.phoneshop.service.impl.ColorServiceImpl;

import java.util.List;

public interface ColorService {

    Color createColor(Color color);

    Color getColorById(Long colorId);

    void deleteColor(Long colorId);

    List<Color> getAllColors();

}

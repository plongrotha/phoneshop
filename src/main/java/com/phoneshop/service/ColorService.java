package com.phoneshop.service;

import com.phoneshop.model.entity.Color;

import java.util.List;

public interface ColorService {

    Color createColor(Color color);

    Color getColorById(Long colorId);

    List<Color> getAllColors();

}

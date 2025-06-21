package com.phoneshop.service;

import com.phoneshop.model.entity.Color;

public interface ColorService {

    Color createColor(Color color);

    Color getColorById(Long colorId);

}

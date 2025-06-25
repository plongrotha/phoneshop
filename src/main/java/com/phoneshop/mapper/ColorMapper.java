package com.phoneshop.mapper;

import com.phoneshop.dto.ColorDTO;
import com.phoneshop.model.entity.Color;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    Color toColor(ColorDTO colorDTO);

    ColorDTO ToColorDTO(Color color);

}

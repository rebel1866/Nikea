package com.nikea.productservice.service.mapper;

import com.nikea.productservice.dao.model.Furniture;
import com.nikea.productservice.dao.model.FurnitureSize;
import com.nikea.productservice.service.dto.FurnitureDto;
import com.nikea.productservice.service.dto.FurnitureSizeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Furniture toEntity(FurnitureDto dto) {
        Furniture furniture = new Furniture();
        furniture.setId(dto.getId());
        furniture.setName(dto.getName());
        furniture.setPrice(dto.getPrice());
        furniture.setFurnitureType(dto.getType());
        furniture.setColor(dto.getColor());
        furniture.setAmount(dto.getAmount());
        furniture.setAvailableSizes(dto.getAvailableSizes().stream().map(size ->
                        new FurnitureSize(size.getId(), size.getWidth(), size.getLength(), size.getHeight())).
                collect(Collectors.toList()));
        return furniture;
    }

    public List<Furniture> toEntity(List<FurnitureDto> furnitureDtoList) {
        return furnitureDtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public FurnitureDto toDto(Furniture entity) {
        if (entity == null) return null;
        FurnitureDto dto = new FurnitureDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setType(entity.getFurnitureType());
        dto.setColor(entity.getColor());
        dto.setAmount(entity.getAmount());
        dto.setAvailableSizes(entity.getAvailableSizes().stream().map(size ->
                        new FurnitureSizeDto(size.getId(), size.getWidth(), size.getLength(), size.getHeight())).
                collect(Collectors.toList()));
        return dto;
    }

    public List<FurnitureDto> toDto(List<Furniture> furnitureList) {
        return furnitureList.stream().map(this::toDto).collect(Collectors.toList());
    }
}

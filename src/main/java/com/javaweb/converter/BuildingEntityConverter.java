package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class BuildingEntityConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RentAreaConverter rentAreaConverter;

    public BuildingEntity convertToBuildingEntity(BuildingDTO buildingDTO) {

        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setRentpriceDes(buildingDTO.getRentPriceDescription());
        List<String> typeCodes = buildingDTO.getTypeCode();
        if (typeCodes != null && !typeCodes.isEmpty()) {
            String typeCode = String.join(",", typeCodes);
            buildingEntity.setTypeCode(typeCode);
        }
        buildingEntity.setRentAreas(rentAreaConverter.toRentArea(buildingDTO, buildingEntity));
        return buildingEntity;
    }
}

package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BuildingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO convertToBuildingDTO(BuildingEntity buildingEntity) {
        BuildingDTO dto = modelMapper.map(buildingEntity, BuildingDTO.class);
        List<RentAreaEntity> areaEntities = buildingEntity.getRentAreas();
        String rentArea = areaEntities.stream().map(item -> item.getValue().toString()).collect(Collectors.joining(","));
        dto.setRentArea(rentArea);
        String district = buildingEntity.getDistrict();
        if (district != null) {
            districtCode districtEnum = Stream.of(districtCode.values())
                    .filter(code -> code.name().equals(district))
                    .findFirst()
                    .orElse(null);
            if (districtEnum != null) {
                String districtName = districtEnum.getDistrictName();
                dto.setDistrict(districtName);
            }
        }

        return dto;
    }

    public BuildingEntity convertToBuildingEntity(BuildingDTO dto) {
        BuildingEntity buildingEntity = modelMapper.map(dto, BuildingEntity.class);

        return buildingEntity;
    }
}

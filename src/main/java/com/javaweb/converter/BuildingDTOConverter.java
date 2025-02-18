package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.utils.DistrictCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
        Map<String, String> districtType = DistrictCode.type();
        if (district != null && district != "") {
            String districtName = districtType.get(district);
            if (districtName != null && districtName != "") {
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

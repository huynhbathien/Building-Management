package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentAreaConverter {
    public List<RentAreaEntity> toRentArea(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        List<RentAreaEntity> rentArea = new ArrayList<>();
        String[] values = buildingDTO.getRentArea().split(",");
        for (String value : values) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(Long.valueOf(value));
            rentAreaEntity.setBuilding(buildingEntity);
            rentArea.add(rentAreaEntity);
        }
        return rentArea;
    }
}

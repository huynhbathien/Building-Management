package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.stereotype.Component;

@Component
public class RentAreaConverter {
    public RentAreaEntity toRentArea(BuildingEntity buildingEntity, String value) {
        RentAreaEntity rentArea = new RentAreaEntity();
        Long buildingId = buildingEntity.getId();
        rentArea.setBuilding(buildingEntity);
        rentArea.setValue(Long.parseLong(value));
        return rentArea;
    }
}

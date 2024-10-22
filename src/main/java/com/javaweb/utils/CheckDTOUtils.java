package com.javaweb.utils;

import com.javaweb.model.dto.BuildingDTO;

import java.util.List;

public class CheckDTOUtils {
    public static Boolean checkDTO(BuildingDTO buildingDTO) {
        if (buildingDTO.getName() == null || buildingDTO.getName().isEmpty()) return false;
        if (buildingDTO.getStreet() == null || buildingDTO.getStreet().isEmpty()) return false;
        if (buildingDTO.getWard() == null || buildingDTO.getWard().isEmpty()) return false;
        if (buildingDTO.getDistrict() == null || buildingDTO.getDistrict().isEmpty()) return false;
        if (buildingDTO.getNumberOfBasement() == null) return false;
        if (buildingDTO.getFloorArea() == null) return false;
        if (buildingDTO.getRentPrice() == null) return false;
        if (buildingDTO.getRentPriceDescription() == null || buildingDTO.getRentPriceDescription().isEmpty())
            return false;
        if (buildingDTO.getTypeCode() == null || buildingDTO.getTypeCode().isEmpty()) return false;
        if (buildingDTO.getManagerName() == null || buildingDTO.getManagerName().isEmpty()) return false;
        if (buildingDTO.getManagerPhone() == null || buildingDTO.getManagerPhone().isEmpty()) return false;
        return true;
    }
}

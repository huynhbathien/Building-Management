package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface BuildingService {
    ResponseDTO listStaff(Long buildingId);

    void inserOrUpdatetBuilding(BuildingDTO buildingDTO);

    List<BuildingDTO> findAll(BuildingSearchRequest buildingSearchRequest);

    BuildingDTO findByBuildingId(Long id);

    void deleteBuildingById(List<Long> ids);

}

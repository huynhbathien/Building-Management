package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingService {
    ResponseDTO listStaff(Long buildingId);

    BuildingEntity insertOrUpdatetBuilding(BuildingDTO buildingDTO);

    List<BuildingDTO> findAll(BuildingSearchRequest buildingSearchRequest);

    BuildingDTO findByBuildingId(Long id);

    void deleteBuildingById(List<Long> ids);

    void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
}

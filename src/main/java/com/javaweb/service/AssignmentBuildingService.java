package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;

import java.util.List;

public interface AssignmentBuildingService {
    void deleteAssignmentsByBuildingId(List<Long> buildingIds);

    void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);

}

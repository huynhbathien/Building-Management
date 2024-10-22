package com.javaweb.api.admin;

import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @PostMapping
    public String addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        buildingService.inserOrUpdatetBuilding(buildingDTO);
        return "oke";
    }

    @DeleteMapping("/{ids}")
    public void DeleteBuilding(@PathVariable List<Long> ids) {
        buildingService.deleteBuildingById(ids);
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO getAllBuildings(@PathVariable Long id) {
        ResponseDTO result = buildingService.listStaff(id);
        return result;
    }

    @PostMapping("/assignments")
    public void assignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        assignmentBuildingService.updateAssignmentBuilding(assignmentBuildingDTO);
    }
}

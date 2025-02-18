package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.ErrorDTO;
import com.javaweb.model.dto.SuccessDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.BuildingService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;


    @PostMapping
    public ResponseEntity<?> addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
                return ResponseEntity.badRequest().body(new ErrorDTO(errors));
            }
            buildingService.insertOrUpdatetBuilding(buildingDTO);
            return ResponseEntity.ok(new SuccessDTO("Building added successfully", buildingDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(Collections.singletonList(e.getMessage())));
        }
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<Object> deleteBuilding(@PathVariable List<Long> ids) {
        try {
            if (!ids.isEmpty()) {
                buildingService.deleteBuildingById(ids);
                return ResponseEntity.ok().body(Collections.singletonMap("message", "Delete Success"));
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No IDs provided for deletion");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid IDs provided: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting buildings: " + e.getMessage());
        }
    }


    @GetMapping("/{id}/staffs")
    public ResponseDTO getAllBuildings(@PathVariable Long id) {
        ResponseDTO result = buildingService.listStaff(id);
        return result;
    }

    @PostMapping("/assignments")
    public ResponseEntity<String> assignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        try {
            buildingService.updateAssignmentBuilding(assignmentBuildingDTO);
            return ResponseEntity.ok("Assignment Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update assignment");
        }
    }
}

package com.javaweb.service.impl;

import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional

@Service
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteAssignmentsByBuildingId(List<Long> buildingIds) {
        for (Long id : buildingIds) {
            assignmentBuildingRepository.deleteByBuildingId(id);
        }
    }

    @Override
    public void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        assignmentBuildingRepository.deleteByBuildingId(assignmentBuildingDTO.getBuildingId());
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        List<Long> staffs = assignmentBuildingDTO.getStaffs();
        for (Long value : staffs) {
            UserEntity userEntity = userRepository.findById(value).get();

            AssignBuildingEntity assignBuildingEntity = new AssignBuildingEntity();
            assignBuildingEntity.setBuilding(buildingEntity);
            assignBuildingEntity.setStaff(userEntity);

            assignmentBuildingRepository.save(assignBuildingEntity);
        }
    }
}
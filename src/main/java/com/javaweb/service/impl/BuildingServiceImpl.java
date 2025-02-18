package com.javaweb.service.impl;

import com.javaweb.buider.BuildingSearchBuider;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingEntityConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Autowired
    private BuildingDTOConverter buildingDTOConverter;

    @Autowired
    private BuildingEntityConverter buildingEntityConverter;

    //getstaff manytomany
    @Override
    public ResponseDTO listStaff(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignment = building.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity item : staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(item.getFullName());
            staffResponseDTO.setStaffId(item.getId());
            if (staffAssignment.contains(item)) {
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }


    @Override
    public BuildingEntity insertOrUpdatetBuilding(BuildingDTO buildingDTO) {
        Field[] fields = buildingDTO.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(buildingDTO);
                if (fieldValue instanceof String && ((String) fieldValue).isEmpty()) {
                    field.set(buildingDTO, null);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        BuildingEntity buildingEntity = buildingEntityConverter.convertToBuildingEntity(buildingDTO);

        // Kiểm tra nếu tên tòa nhà đã tồn tại (chỉ kiểm tra khi là thêm mới, không phải khi cập nhật)
        if (buildingDTO.getId() == null && buildingRepository.existsByName(buildingDTO.getName())) {
            throw new RuntimeException("Tên Tòa Nhà Đã Tồn Tại");
        }

        buildingRepository.save(buildingEntity);
        return buildingEntity;
    }


    @Override
    public List<BuildingDTO> findAll(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuider buider = buildingSearchBuilderConverter.toBuildingSearchBuider(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buider);
        List<BuildingDTO> buildingDTOS = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingDTO buildingDTO = buildingDTOConverter.convertToBuildingDTO(item);
            buildingDTOS.add(buildingDTO);
        }
        return buildingDTOS;
    }

    @Override
    public BuildingDTO findByBuildingId(Long id) {
        BuildingEntity building = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = buildingDTOConverter.convertToBuildingDTO(building);
        return buildingDTO;
    }

    @Override
    public void deleteBuildingById(List<Long> ids) {
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity building = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        List<UserEntity> staffs = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
        building.setUserEntities(staffs);
        buildingRepository.save(building);
    }
}

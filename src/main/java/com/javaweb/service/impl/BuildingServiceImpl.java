package com.javaweb.service.impl;

import com.javaweb.buider.BuildingSearchBuider;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingEntityConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentAreaService;
import com.javaweb.utils.CheckDTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.javaweb.utils.CheckDTOUtils.checkDTO;

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

    @Autowired
    private RentAreaService rentAreaService;

    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    //getstaff manytomany
    //    @Override
//    public ResponseDTO listStaff(Long buildingId) {
//        BuildingEntity building = buildingRepository.findById(buildingId).get();
//        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
//        List<UserEntity> staffAssignment = building.getUserEntities();
//        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
//        ResponseDTO responseDTO = new ResponseDTO();
//        for (UserEntity item : staffs) {
//            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
//            staffResponseDTO.setFullName(item.getFullName());
//            staffResponseDTO.setStaffId(item.getId());
//            if (staffAssignment.contains(item)) {
//                staffResponseDTO.setChecked("checked");
//            } else {
//                staffResponseDTO.setChecked("");
//            }
//            staffResponseDTOS.add(staffResponseDTO);
//        }
//        responseDTO.setData(staffResponseDTOS);
//        responseDTO.setMessage("success");
//        return responseDTO;
//    }
    @Override
    public ResponseDTO listStaff(Long buildingId) {
        // Lấy building từ buildingId
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        // Lấy danh sách AssignBuildingEntity của tòa nhà đó
        List<AssignBuildingEntity> staffAssignmentEntities = building.getAssignBuildingEntities();

        // Tạo một danh sách chứa ID của nhân viên đã được gán cho tòa nhà
        List<Long> assignedStaffIds = staffAssignmentEntities.stream()
                .map(assignBuildingEntity -> assignBuildingEntity.getStaff().getId())
                .collect(Collectors.toList());

        // Tạo danh sách StaffResponseDTO
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();

        // Duyệt qua danh sách tất cả nhân viên và kiểm tra xem nhân viên đó có trong danh sách đã gán không
        for (UserEntity item : staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(item.getFullName());
            staffResponseDTO.setStaffId(item.getId());

            // Nếu nhân viên đã được gán, đánh dấu là "checked"
            if (assignedStaffIds.contains(item.getId())) {
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }

            staffResponseDTOS.add(staffResponseDTO);
        }

        // Đặt dữ liệu và thông điệp phản hồi
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");

        return responseDTO;
    }

    @Transactional
    @Override
    public void inserOrUpdatetBuilding(BuildingDTO buildingDTO) {
        // if (checkDTO(buildingDTO)) return null;
        BuildingEntity buildingEntity = buildingEntityConverter.convertToBuildingEntity(buildingDTO);
        try {
            Field[] dtoFields = buildingDTO.getClass().getDeclaredFields();
            for (Field item : dtoFields) {
                item.setAccessible(true);
                Object value = item.get(buildingDTO);
                if (value == null) {
                    Field entityField = buildingEntity.getClass().getDeclaredField(item.getName());
                    entityField.setAccessible(true);
                    entityField.set(buildingEntity, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        buildingRepository.save(buildingEntity);
        String rentArea = buildingDTO.getRentArea();
        if (rentArea != null) {
            rentAreaService.saveOrUpdateRentArea(rentArea, buildingEntity);
        }
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

    @Transactional
    @Override
    public void deleteBuildingById(List<Long> ids) {
        assignmentBuildingService.deleteAssignmentsByBuildingId(ids);
        rentAreaService.deleteRentAreaByBuildingId(ids);
        for (Long item : ids) {
            buildingRepository.deleteById(item);
        }
    }


}

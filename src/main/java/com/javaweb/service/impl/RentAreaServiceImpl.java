package com.javaweb.service.impl;

import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.RentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RentAreaServiceImpl implements RentAreaService {
    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private RentAreaConverter rentAreaConverter;

    @Autowired
    private BuildingRepository buildingRepository;

    @Transactional
    @Override
    public void saveOrUpdateRentArea(String renAreas, BuildingEntity buildingEntity) {
        Long id = buildingEntity.getId();
        rentAreaRepository.deleteByBuildingId(id);
        String[] rentAreaStr = renAreas.trim().split(",");
        for (String item : rentAreaStr) {
            RentAreaEntity rentAreaEntity = rentAreaConverter.toRentArea(buildingEntity, item);
            rentAreaRepository.save(rentAreaEntity);
        }
    }

    @Transactional
    @Override
    public void deleteRentAreaByBuildingId(List<Long> ids) {
        for (Long id : ids) {
            rentAreaRepository.deleteByBuildingId(id);
        }
    }
}

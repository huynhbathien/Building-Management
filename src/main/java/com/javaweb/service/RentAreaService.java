package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;

import java.util.List;

public interface RentAreaService {

    void deleteRentAreaByBuildingId(List<Long> ids);

}

package com.javaweb.repository.custom;

import com.javaweb.buider.BuildingSearchBuider;
import com.javaweb.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingSearchBuider buider);

}

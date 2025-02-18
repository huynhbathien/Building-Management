package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
    Optional<BuildingEntity> findById(Long id);

    void deleteByIdIn(List<Long> ids);

    void findByIdIn(List<Long> ids);

    boolean existsByName(String buildingName);
}

package com.javaweb.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "assignmentbuilding")
public class AssignBuildingEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity staff;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity buildingEntity) {
        this.building = buildingEntity;
    }

    public UserEntity getStaff() {
        return staff;
    }

    public void setStaff(UserEntity userEntity) {
        this.staff = userEntity;
    }
}

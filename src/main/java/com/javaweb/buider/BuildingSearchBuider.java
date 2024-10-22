package com.javaweb.buider;

import java.util.List;

public class BuildingSearchBuider {
    private String name;
    private Long floorArea;
    private String district;
    private String ward;
    private String street;
    private Long numberOfBasement;
    private String direction;
    private Long level;
    private Long areaFrom;
    private Long areaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private String managerName;
    private String managerPhone;
    private Long staffId;
    private List<String> typeCode;

    public BuildingSearchBuider(Buider buider) {
        this.areaFrom = buider.areaFrom;
        this.areaTo = buider.areaTo;
        this.direction = buider.direction;
        this.district = buider.district;
        this.floorArea = buider.floorArea;
        this.level = buider.level;
        this.managerName = buider.managerName;
        this.managerPhone = buider.managerPhone;
        this.name = buider.name;
        this.numberOfBasement = buider.numberOfBasement;
        this.rentPriceFrom = buider.rentPriceFrom;
        this.rentPriceTo = buider.rentPriceTo;
        this.staffId = buider.staffId;
        this.street = buider.street;
        this.typeCode = buider.typeCode;
        this.ward = buider.ward;
    }

    public Long getAreaFrom() {
        return areaFrom;
    }

    public Long getAreaTo() {
        return areaTo;
    }

    public String getDirection() {
        return direction;
    }

    public String getDistrict() {
        return district;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public Long getLevel() {
        return level;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public String getName() {
        return name;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public Long getRentPriceFrom() {
        return rentPriceFrom;
    }

    public Long getRentPriceTo() {
        return rentPriceTo;
    }

    public Long getStaffId() {
        return staffId;
    }

    public String getStreet() {
        return street;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public String getWard() {
        return ward;
    }

    public static class Buider {
        private String name;
        private Long floorArea;
        private String district;
        private String ward;
        private String street;
        private Long numberOfBasement;
        private String direction;
        private Long level;
        private Long areaFrom;
        private Long areaTo;
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private String managerName;
        private String managerPhone;
        private Long staffId;
        private List<String> typeCode;

        public Buider setAreaFrom(Long areaFrom) {
            this.areaFrom = areaFrom;
            return this;
        }

        public Buider setAreaTo(Long areaTo) {
            this.areaTo = areaTo;
            return this;
        }

        public Buider setDirection(String direction) {
            this.direction = direction;
            return this;

        }

        public Buider setDistrict(String district) {
            this.district = district;
            return this;

        }

        public Buider setFloorArea(Long floorArea) {
            this.floorArea = floorArea;
            return this;

        }

        public Buider setLevel(Long level) {
            this.level = level;
            return this;
        }

        public Buider setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Buider setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }

        public Buider setName(String name) {
            this.name = name;
            return this;
        }

        public Buider setNumberOfBasement(Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Buider setRentPriceFrom(Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }

        public Buider setRentPriceTo(Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }

        public Buider setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public Buider setStreet(String street) {
            this.street = street;
            return this;
        }

        public Buider setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        public Buider setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public BuildingSearchBuider build(){
            return new BuildingSearchBuider(this);
        }
    }
}

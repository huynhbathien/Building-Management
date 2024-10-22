package com.javaweb.converter;

import com.javaweb.buider.BuildingSearchBuider;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuider toBuildingSearchBuider(BuildingSearchRequest item) {
        BuildingSearchBuider buider = new BuildingSearchBuider.Buider()
                .setName(MapUtils.getObject(item.getName(), String.class))
                .setFloorArea(MapUtils.getObject(item.getFloorArea(), Long.class))
                .setDistrict(MapUtils.getObject(item.getDistrict(), String.class))
                .setWard(MapUtils.getObject(item.getWard(), String.class))
                .setStreet(MapUtils.getObject(item.getStreet(), String.class))
                .setNumberOfBasement(MapUtils.getObject(item.getNumberOfBasement(), Long.class))
                .setDirection(MapUtils.getObject(item.getDirection(), String.class))
                .setLevel(MapUtils.getObject(item.getLevel(), Long.class))
                .setAreaFrom(MapUtils.getObject(item.getAreaFrom(), Long.class))
                .setAreaTo(MapUtils.getObject(item.getAreaTo(), Long.class))
                .setRentPriceFrom(MapUtils.getObject(item.getRentPriceFrom(), Long.class))
                .setRentPriceTo(MapUtils.getObject(item.getRentPriceTo(), Long.class))
                .setManagerName(MapUtils.getObject(item.getManagerName(), String.class))
                .setManagerPhone(MapUtils.getObject(item.getManagerPhone(), String.class))
                .setStaffId(MapUtils.getObject(item.getStaffId(), Long.class))
                .setTypeCode(item.getTypeCode())
                .build();
        return buider;
    }
}

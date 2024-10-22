package com.javaweb.controller.admin;


import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private UserService userService;
    @Autowired
    private BuildingService buildingService;

    @GetMapping("/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
        //modelAndView không nhận 3 cấp vd admin/building/list
        ModelAndView mav = new ModelAndView("admin/building/list");
        List<BuildingDTO> buildingDTOList = buildingService.findAll(buildingSearchRequest);
        mav.addObject("buildingDTO", buildingDTOList);
        mav.addObject("buildingSearch", buildingSearchRequest);
        mav.addObject("listStaffs", userService.getStaffs());
        //enum
        mav.addObject("districts", districtCode.type());
        mav.addObject("buildingType", buildingType.type());
        return mav;
    }

    @GetMapping("/admin/building-edit")
    public ModelAndView editBuilding(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO dto = new BuildingDTO();
        mav.addObject("buildingEdit", dto);
        mav.addObject("districts", districtCode.type());
        mav.addObject("buildingType", buildingType.type());
        return mav;
    }

    @GetMapping("/admin/building-edit-{id}")
    public ModelAndView editBuilding(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO dto = buildingService.findByBuildingId(id);
        mav.addObject("buildingEdit", dto);
        mav.addObject("districts", districtCode.type());
        mav.addObject("buildingType", buildingType.type());

        return mav;
    }
}

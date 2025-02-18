package com.javaweb.controller.admin;


import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private UserService userService;
    @Autowired
    private BuildingService buildingService;

    @GetMapping("/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute(SystemConstant.MODEL) BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
        //modelAndView không nhận 3 cấp vd admin/building/list
        ModelAndView mav = new ModelAndView("admin/building/list");
        if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(staffId);
        }

        List<BuildingDTO> buildingDTOS = buildingService.findAll(buildingSearchRequest);

        mav.addObject("buildingDTO", buildingDTOS);
        mav.addObject("buildingSearch", buildingSearchRequest);
        mav.addObject("listStaffs", userService.getStaffs());

        mav.addObject("districts", districtCode.type());
        mav.addObject("buildingType", buildingType.type());

        // Thêm thông tin về authorities
        mav.addObject("authorities", SecurityUtils.getAuthorities());

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

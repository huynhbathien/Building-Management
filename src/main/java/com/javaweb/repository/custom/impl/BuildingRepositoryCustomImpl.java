package com.javaweb.repository.custom.impl;

import com.javaweb.buider.BuildingSearchBuider;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public static void joinTable(StringBuilder query, BuildingSearchBuider buider) {
        Long staffId = buider.getStaffId();
        if (staffId != null) {
            query.append(" INNER JOIN assignmentbuilding b ON a.id = b.buildingid ");
        }
    }

    public static void queryNomal(BuildingSearchBuider buider, StringBuilder where) {
        try {
            Field[] fields = buider.getClass().getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                Object fieldValue = item.get(buider);
                if (fieldValue != null && fieldValue != "") {
                    if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
                        if (item.getType() == Long.class || item.getType() == Integer.class) {
                            where.append(" AND a." + fieldName.toLowerCase() + " = " + fieldValue + " ");
                        } else if (item.getType() == String.class) {
                            where.append(" AND a." + fieldName.toLowerCase() + " LIKE'%" + fieldValue + "%' ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void querySpecial(BuildingSearchBuider buider, StringBuilder where) {
        Long staffId = buider.getStaffId();
        if (staffId != null) {
            where.append(" AND b.staffid =" + staffId);
        }

        Long areaFrom = buider.getAreaFrom();
        Long areaTo = buider.getAreaTo();
        if (areaFrom != null || areaTo != null) {
            where.append(" AND EXISTS( SELECT * FROM rentarea ra WHERE ra.buildingid=a.id ");
            if (areaFrom != null) {
                where.append(" AND ra.value>=" + areaFrom + " ");
            }
            if (areaTo != null) {
                where.append(" AND ra.value<=" + areaTo + " ");
            }
            where.append(" ) ");

        }

        Long rentPriceTo = buider.getRentPriceTo();
        Long rentPriceFrom = buider.getRentPriceFrom();
        if (rentPriceFrom != null) {
            where.append(" AND c.value >=" + +rentPriceFrom + " ");
        }
        if (rentPriceTo != null) {
            where.append(" AND c.value <=" + +rentPriceTo + " ");
        }
        List<String> typeCode = buider.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            where.append(" AND (");
            String sql = typeCode.stream().map(item -> "a.type LIKE '%" + item + "%' ").collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(")");
        }
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuider buider) {
        StringBuilder sql = new StringBuilder("select * from building a");
        StringBuilder where = new StringBuilder(" where 1=1");
        joinTable(sql, buider);
        queryNomal(buider, where);
        querySpecial(buider, where);
        where.append(" group by a.id ");
        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }
}

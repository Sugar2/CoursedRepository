package com.coursed.service;

import com.coursed.dto.EducationPlanForm;
import com.coursed.model.EducationPlan;

import java.util.List;

/**
 * Created by Hexray on 11.12.2016.
 */
public interface EducationPlanService {
    List<EducationPlan> findAll();
    EducationPlan create(EducationPlanForm planForm);
    EducationPlan findOne(Long educationPlanId);
}

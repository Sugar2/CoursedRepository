package com.coursed.service;

import com.coursed.dto.SpecialityForm;
import com.coursed.model.Speciality;

import java.util.List;

/**
 * Created by Hexray on 26.11.2016.
 */
public interface SpecialityService {
    Speciality create(SpecialityForm specialityForm);
    List<Speciality> findAll();
    Speciality findOne(Long id);
}

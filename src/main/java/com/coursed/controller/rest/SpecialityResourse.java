package com.coursed.controller.rest;

import com.coursed.dto.SpecialityForm;
import com.coursed.model.Speciality;
import com.coursed.model.Year;
import com.coursed.service.SpecialityService;
import com.coursed.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Hexray on 01.12.2016.
 */
@RestController
@RequestMapping("/api/specialities")
public class SpecialityResourse {
    @Autowired
    private SpecialityService specialityService;

    @GetMapping("/getAll")
    private Collection<Speciality> getSpecialities() {
        return specialityService.findAll();
    }

    @PostMapping("/create")
    private Speciality createSpeciality(@RequestBody SpecialityForm specialityForm) {
        return specialityService.create(specialityForm);
    }
}
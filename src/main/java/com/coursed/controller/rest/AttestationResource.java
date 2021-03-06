package com.coursed.controller.rest;

import com.coursed.dto.AttestationDTO;
import com.coursed.dto.AttestationDTOList;
import com.coursed.model.AttestationGrade;
import com.coursed.service.AttestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Hexray on 19.12.2016.
 */
@RestController
@RequestMapping("api/attestations")
public class AttestationResource {
    @Autowired
    private AttestationService attestationService;

    @GetMapping("/getAll")
    private Collection<AttestationGrade> getAll(){
        return null;
    }

    @PostMapping("/createManyFirst")
    private void createSet(@RequestBody AttestationDTOList attestationGrades){
        List<AttestationDTO> list = new ArrayList<>(attestationGrades);
        attestationService.createManyFirst(list);
    }
}

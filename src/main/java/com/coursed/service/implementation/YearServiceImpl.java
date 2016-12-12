package com.coursed.service.implementation;

import com.coursed.dto.YearForm;
import com.coursed.model.Semester;
import com.coursed.model.Year;
import com.coursed.model.enums.SemesterNumber;
import com.coursed.repository.SemesterRepository;
import com.coursed.repository.YearRepository;
import com.coursed.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Hexray on 16.11.2016.
 */
@Service
public class YearServiceImpl implements YearService {

    @Autowired
    private YearRepository yearRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public List<Year> findAll() {
        return yearRepository.findAll();
    }

    @Override
    public Year findOne(Long id) {
        return yearRepository.findOne(id);
    }

    @Override
    public void create(YearForm yearForm) {

        Year year = new Year();
        year.setBeginYear(yearForm.getBeginYear());
        year.setEndYear(yearForm.getEndYear());

        Semester firstSemester = new Semester(SemesterNumber.FIRST);
        Semester secondSemester = new Semester(SemesterNumber.SECOND);

        List<Semester> semesters = new ArrayList<>();
        semesters.add(firstSemester);
        semesters.add(secondSemester);
        year.setSemesters(semesters);

        firstSemester.setYear(year);
        secondSemester.setYear(year);

        yearRepository.save(year);
    }

}

package com.coursed.controller.mvc;

import com.coursed.model.Semester;
import com.coursed.model.Year;
import com.coursed.model.auth.Role;
import com.coursed.model.auth.User;
import com.coursed.model.enums.SemesterNumber;
import com.coursed.repository.SemesterRepository;
import com.coursed.repository.YearRepository;
import com.coursed.service.RoleService;
import com.coursed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hexray on 31.10.2016.
 */
@Controller
public class BaseInitController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private YearRepository yearRepository;

    @RequestMapping("/valve")
    public String index() {
        Role studentRole = new Role();
        studentRole.setName("ROLE_STUDENT");

        Role adminRole = new Role();
        adminRole.setName("ROLE_TEACHER");

        roleService.create(studentRole);
        roleService.create(adminRole);

        Set<Role> studentRoles = new HashSet<>();
        studentRoles.add(studentRole);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);


        User student = new User();
        student.setEmail("student@mail.u");
        student.setPassword("test");
        student.setRoles(studentRoles);

        User admin = new User();
        admin.setEmail("admin@mail.u");
        admin.setPassword("testadmin");
        admin.setRoles(adminRoles);


        userService.register(student);
        userService.register(admin);
        System.out.println("==**===============Base configuration has been loaded");


        Year year = new Year();
        year.setBeginYear(2015);

        Semester sem1 = new Semester();
        sem1.setSemesterNumber(SemesterNumber.FIRST);

        Semester sem2 = new Semester();
        sem2.setSemesterNumber(SemesterNumber.SECOND);

        yearRepository.save(year);

        sem1.setYear(year);

        sem2.setYear(year);

        semesterRepository.save(sem1);
        semesterRepository.save(sem2);

        Year year3 = yearRepository.findOne(1L);

        return "redirect:/login";
    }
}

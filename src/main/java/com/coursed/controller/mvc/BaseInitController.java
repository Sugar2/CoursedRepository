package com.coursed.controller.mvc;

import com.coursed.model.Role;
import com.coursed.model.User;
import com.coursed.repository.RoleRepository;
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
    private RoleRepository roleRepository;

    @RequestMapping("/steam")
    public String index() {


        Role studentRole = new Role();
        studentRole.setName("ROLE_STUDENT");

        Role adminRole = new Role();
        adminRole.setName("ROLE_TEACHER");

        roleRepository.save(studentRole);
        roleRepository.save(adminRole);

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


        userService.tempSave(student);
        userService.tempSave(admin);
        System.out.println("==**===============Base configuration has been loaded");
        return "index";
    }
}

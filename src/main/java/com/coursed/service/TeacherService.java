package com.coursed.service;

import com.coursed.model.Teacher;

import java.util.List;

/**
 * Created by Hexray on 06.12.2016.
 */
public interface TeacherService {
    void create(Teacher teacher);
    List<Teacher> findAll();
    Teacher findOne(Long id);
    void setAsCurator(Long teacherId, Long groupId);
}

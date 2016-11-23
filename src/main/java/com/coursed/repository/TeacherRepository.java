package com.coursed.repository;

import com.coursed.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Hexray on 19.11.2016.
 */
@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}

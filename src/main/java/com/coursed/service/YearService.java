package com.coursed.service;

import com.coursed.model.Year;

import java.util.List;

/**
 * Created by Hexray on 13.11.2016.
 */
public interface YearService {
    List<Year> findAll();
    Year findOne(Long id);
    void create(Year year);
}

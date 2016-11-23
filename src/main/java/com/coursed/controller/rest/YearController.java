package com.coursed.controller.rest;

import com.coursed.model.Year;
import com.coursed.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Hexray on 16.11.2016.
 */
@RestController
public class YearController {
    @Autowired
    private YearService yearService;

    @RequestMapping(value = "/api/years", method = RequestMethod.GET)
    public @ResponseBody Set<Year> getYears()
    {
        Set<Year> years = yearService.findAll();

        int a = 5;
        return years;
    }


}

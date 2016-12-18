package com.coursed.service;

import com.coursed.model.PlannedEvent;
import com.coursed.model.Semester;
import com.coursed.model.enums.PlannedEventType;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by Trach on 12/11/2016.
 */
public interface PlannedEventService {
    PlannedEvent findOne(Long eventId);
    void create(String beginDate, String expirationDate, PlannedEventType plannedEventType, Semester semester) throws DateTimeParseException;
    void create(PlannedEvent event);
    List<PlannedEvent> findAll();
    List<PlannedEvent> findAllByBeginDate(String date);
    List<PlannedEvent> findAllByExpirationDate(String date);
    List<PlannedEvent> findAllByEventType(PlannedEventType plannedEventType);
    List<PlannedEvent> findAllBySemester(Semester semester);
    List<PlannedEvent> findAllFromCurrentYear();
}

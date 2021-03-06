package com.coursed.controller.rest;

import com.coursed.dto.PlannedEventDTO;
import com.coursed.model.PlannedEvent;
import com.coursed.service.PlannedEventService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;

/**
 * Created by Trach on 12/11/2016.
 */
@RestController
@RequestMapping("/api/events")
public class PlannedEventResource {

    @Autowired
    private PlannedEventService plannedEventService;

    @GetMapping("/getEvent")
    public PlannedEvent getEvent(@RequestParam(name = "eventId") Long eventId) {
        return plannedEventService.findOne(eventId);
    }

    @GetMapping("/getAll")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public Collection<PlannedEvent> getAll() {
        return plannedEventService.findAll();
    }

    @GetMapping("/getAllFromCurrentYear")
    public Collection<PlannedEvent> getAllFromCurrentYear(){
        return plannedEventService.findAllFromCurrentYear();
    }

    @PostMapping("/create")
    private PlannedEvent create(@RequestBody PlannedEventDTO plannedEventDTO){
        return plannedEventService.create(plannedEventDTO);
    }

    @GetMapping("/getOne")
    private PlannedEvent getOne(@RequestParam(name = "plannedEventId")Long plannedEventId) {
        return plannedEventService.findOne(plannedEventId);
    }
}

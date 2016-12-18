package com.coursed.dto;

import com.coursed.model.enums.PlannedEventType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

/**
 * Created by Hexray on 18.12.2016.
 */
public class PlannedEventDTO {
    private LocalDateTime beginDate;
    private LocalDateTime expirationDate;
    private PlannedEventType eventType;
    private Long semesterId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public PlannedEventType getEventType() {
        return eventType;
    }

    public void setEventType(PlannedEventType eventType) {
        this.eventType = eventType;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }
}

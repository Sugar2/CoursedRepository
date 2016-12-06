package com.coursed.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Hexray on 14.11.2016.
 */
@Entity
public class Speciality {
    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    private String groupsName;

    @JsonManagedReference("speciality-groups")
    @OneToMany(mappedBy = "speciality")
    private List<Group> groups;

    @OneToMany(mappedBy = "speciality")
    private List<Discipline> disciplines;

    public Speciality() {
    }

    public Speciality(String fullName, String groupsName) {
        this.fullName = fullName;
        this.groupsName = groupsName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGroupsName() {
        return groupsName;
    }

    public void setGroupsName(String groupsName) {
        this.groupsName = groupsName;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
}
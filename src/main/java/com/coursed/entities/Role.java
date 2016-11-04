package com.coursed.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Hexray on 04.11.2016.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String Name;

    @ManyToMany(mappedBy = "roles")
    private Set<Account> accounts;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

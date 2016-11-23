package com.litereaction.model;

import javax.persistence.*;

@Entity
@Table(name = "Round")
public class Round {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String name;
    private String description;
    private boolean active;

    public Round() {

    }

    public Round(String name, String description, boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

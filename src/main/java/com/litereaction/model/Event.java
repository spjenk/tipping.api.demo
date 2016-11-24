package com.litereaction.model;

import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    @ManyToOne
    private Round round;

    public Event() {

    }

    public Event(String name, String description, Round round) {
        this.name = name;
        this.description = description;
        this.round = round;
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

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }
}

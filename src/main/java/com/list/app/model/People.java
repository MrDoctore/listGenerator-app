package com.list.app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class People implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String classroom;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public People() {
    }

    public People(Integer id, String name, String email, String classroom, Event event) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.classroom = classroom;
        this.event = event;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}

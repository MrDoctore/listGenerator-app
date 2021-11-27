package com.list.app.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.list.app.model.Event;

import javax.validation.constraints.NotEmpty;

public class PeoplePostRequestBody {


    @NotEmpty(message = "The person name cannot be empty")
    private String name;
    private String email;
    private String classroom;
    private Event event;

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

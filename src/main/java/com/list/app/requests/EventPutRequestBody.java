package com.list.app.requests;

import javax.validation.constraints.NotEmpty;

public class EventPutRequestBody {

    private Integer id;
    @NotEmpty(message = "The event name cannot be empty")
    private String name;
    private String subtitle;

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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}

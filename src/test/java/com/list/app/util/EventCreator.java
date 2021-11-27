package com.list.app.util;

import com.list.app.model.Event;

public class EventCreator {

    public static Event createEventToBeSaved() {
        Event event = new Event();
        event.setName("Not Saved Name");
        event.setSubtitle("Not Saved Subtitle");
        return event;
    }

    public static Event createValidEvent() {
        Event event = new Event();
        event.setName("Saved Name");
        event.setSubtitle("Saved Subtitle");
        return event;
    }

    public static Event createUpdatedEvent() {
        Event event = new Event();
        event.setName("Updated Name");
        event.setSubtitle("Updated Subtitle");
        return event;
    }
}

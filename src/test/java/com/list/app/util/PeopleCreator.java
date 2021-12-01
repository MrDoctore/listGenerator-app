package com.list.app.util;

import com.list.app.model.People;

public class PeopleCreator {

    public static People createPeopleToBeSaved() {
        People people = new People();
        people.setName("Not Saved Name");
        people.setEmail("Not Saved Email");
        people.setClassroom("Not Saved Classroom");
        return people;
    }

    public static People createValidPeople() {
        People people = new People();
        people.setName("Saved Name");
        people.setEmail("Saved Email");
        people.setClassroom("Saved Classroom");
        return people;
    }

    public static People createUpdatedPeople() {
        People people = new People();
        people.setName("Updated Name");
        people.setEmail("Updated Email");
        people.setClassroom("Updated Classroom");
        return people;
    }
}

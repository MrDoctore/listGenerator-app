package com.list.app.repository;

import com.list.app.model.Event;
import com.list.app.model.People;
import com.list.app.util.EventCreator;
import com.list.app.util.PeopleCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@DisplayName("Tests for people repository")
class PeopleRepositoryTest {

    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    @DisplayName("save persist people when successful")
    void save_PersistPeople_WhenSuccessful() {
        Event event = EventCreator.createValidEvent();
        People people = PeopleCreator.createPeopleToBeSaved();
        people.setEvent(event);
        People savedPeople = this.peopleRepository.save(people);
        Assertions.assertThat(savedPeople).isNotNull();
        Assertions.assertThat(savedPeople.getId()).isNotNull();
        Assertions.assertThat(savedPeople.getName()).isEqualTo(people.getName());
        Assertions.assertThat(savedPeople.getEmail()).isEqualTo(people.getEmail());
        Assertions.assertThat(savedPeople.getEvent()).isEqualTo(event);
    }

    @Test
    @DisplayName("save throw constraint validation exception when people name is empty")
    void save_ThrowsConstraintValidationException_WhenPeopleNameIsEmpty() {
        People people = new People();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.peopleRepository.save(people))
                .withMessageContaining("The person name cannot be empty");
    }

    @Test
    @DisplayName("save update people when successful")
    void save_UpdatePeople_WhenSuccessful() {
        People people = PeopleCreator.createValidPeople();
        people.setName("Updated Name");
        People updatedPeople = this.peopleRepository.save(people);
        Assertions.assertThat(updatedPeople).isNotNull();
        Assertions.assertThat(updatedPeople.getId()).isNotNull();
        Assertions.assertThat(updatedPeople.getName()).isEqualTo(people.getName());
        Assertions.assertThat(updatedPeople.getEmail()).isEqualTo(people.getEmail());
        Assertions.assertThat(updatedPeople.getClassroom()).isEqualTo(people.getClassroom());
    }

    @Test
    @DisplayName("delete removes people when successful")
    void delete_RemovesPeople_WhenSuccessful() {
        People people = PeopleCreator.createPeopleToBeSaved();
        People savedPeople = this.peopleRepository.save(people);
        this.peopleRepository.delete(savedPeople);
        Optional<People> optionalPeople = this.peopleRepository.findById(savedPeople.getId());
        Assertions.assertThat(optionalPeople).isNotPresent();
    }

    @Test
    @DisplayName("Find and return a list of people when successful")
    void find_ReturnListOfPeople_WhenSuccessful() {
        People people1 = PeopleCreator.createPeopleToBeSaved();
        People people2 = PeopleCreator.createPeopleToBeSaved();
        People savedPeople1 = this.peopleRepository.save(people1);
        People savedPeople2 = this.peopleRepository.save(people2);
        List<People> peopleList = (List<People>) this.peopleRepository.findAll();
        Assertions.assertThat(peopleList).isNotEmpty();
        Assertions.assertThat(peopleList).contains(savedPeople1);
        Assertions.assertThat(peopleList).contains(savedPeople2);
    }

    @Test
    @DisplayName("Find and return an empty list of people when no data is found")
    void find_ReturnEmptyListOfEvent_WhenNoEventsAreFound() {
        List<People> peopleList = (List<People>) this.peopleRepository.findAll();
        Assertions.assertThat(peopleList).isEmpty();
    }

}
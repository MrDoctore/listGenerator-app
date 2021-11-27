package com.list.app.repository;

import com.list.app.model.Event;
import com.list.app.util.EventCreator;
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
@DisplayName("Tests for event repository")
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    @DisplayName("save persist event when successful")
    void save_PersistEvent_WhenSuccessful() {
        Event event = EventCreator.createEventToBeSaved();
        Event savedEvent = this.eventRepository.save(event);
        Assertions.assertThat(savedEvent).isNotNull();
        Assertions.assertThat(savedEvent.getId()).isNotNull();
        Assertions.assertThat(savedEvent.getName()).isEqualTo(event.getName());
        Assertions.assertThat(savedEvent.getSubtitle()).isEqualTo(event.getSubtitle());
    }

    @Test
    @DisplayName("save throw constraint validation exception when event name is empty")
    void save_ThrowsConstraintValidationException_WhenEventNameIsEmpty() {
        Event event = new Event();
        System.out.println(event.getName());
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()-> this.eventRepository.save(event))
                .withMessageContaining("The event name cannot be empty");
    }

    @Test
    @DisplayName("save update event when successful")
    void save_UpdateEvent_WhenSuccessful() {
        Event event = EventCreator.createEventToBeSaved();
        Event savedEvent = this.eventRepository.save(event);
        savedEvent.setName("Updated Name");
        Event updatedEvent = this.eventRepository.save(savedEvent);
        Assertions.assertThat(updatedEvent).isNotNull();
        Assertions.assertThat(updatedEvent.getId()).isNotNull();
        Assertions.assertThat(updatedEvent.getName()).isEqualTo(savedEvent.getName());
        Assertions.assertThat(updatedEvent.getSubtitle()).isEqualTo(savedEvent.getSubtitle());
    }

    @Test
    @DisplayName("delete removes event when successful")
    void delete_RemovesEvent_WhenSuccessful() {
        Event event = EventCreator.createEventToBeSaved();
        Event savedEvent = this.eventRepository.save(event);
        this.eventRepository.delete(savedEvent);
        Optional<Event> optionalEvent = this.eventRepository.findById(savedEvent.getId());
        Assertions.assertThat(optionalEvent).isNotPresent();
    }

    @Test
    @DisplayName("Find and return a list of all events when successful")
    void find_ReturnListOfEvent_WhenSuccessful() {
        Event event1 = EventCreator.createEventToBeSaved();
        Event event2 = EventCreator.createEventToBeSaved();
        Event savedEvent1 = this.eventRepository.save(event1);
        Event savedEvent2 = this.eventRepository.save(event2);
        List<Event> events = (List<Event>) this.eventRepository.findAll();
        Assertions.assertThat(events).isNotEmpty();
        Assertions.assertThat(events).contains(savedEvent1);
        Assertions.assertThat(events).contains(savedEvent2);
    }

    @Test
    @DisplayName("Find and return an empty list of events when no events are found")
    void find_ReturnEmptyListOfEvent_WhenNoEventsAreFound() {
        List<Event> events = (List<Event>) this.eventRepository.findAll();
        Assertions.assertThat(events).isEmpty();
    }


}
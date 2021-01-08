package com.list.app.service;

import com.list.app.exceptions.BadRequestException;
import com.list.app.model.Event;
import com.list.app.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> listAll() {
        return (List<Event>) eventRepository.findAll();
    }

    public Event findById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Event not found"));
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event update(Event event) {
        return eventRepository.save(event);
    }

    public void delete(Integer id) {
        eventRepository.deleteById(id);
    }
}

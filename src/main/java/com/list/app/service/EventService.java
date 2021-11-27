package com.list.app.service;

import com.list.app.exceptions.BadRequestException;
import com.list.app.model.Event;
import com.list.app.repository.EventRepository;
import com.list.app.requests.EventPostRequestBody;
import com.list.app.requests.EventPutRequestBody;
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

    public Event findByIdOrThrowBadRequestException(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Event not found"));
    }

    public Event save(EventPostRequestBody eventPostRequestBody) {
        Event event = new Event();
        event.setName(eventPostRequestBody.getName());
        event.setSubtitle(eventPostRequestBody.getSubtitle());
        return eventRepository.save(event);
    }

    public Event update(EventPutRequestBody eventPutRequestBody) {
        findByIdOrThrowBadRequestException(eventPutRequestBody.getId());
        Event event = new Event();
        event.setId(eventPutRequestBody.getId());
        event.setName(eventPutRequestBody.getName());
        event.setSubtitle(eventPutRequestBody.getSubtitle());
        return eventRepository.save(event);
    }

    public void delete(Integer id) {
        eventRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}

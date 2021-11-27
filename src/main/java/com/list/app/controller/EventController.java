package com.list.app.controller;

import com.list.app.model.Event;
import com.list.app.requests.EventPostRequestBody;
import com.list.app.requests.EventPutRequestBody;
import com.list.app.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/eventos")
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> listAll() {
        return new ResponseEntity<>(eventService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Event> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(eventService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> save(@RequestBody @Valid EventPostRequestBody eventPostRequestBody) {
        return new ResponseEntity<>(eventService.save(eventPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Event> update(@RequestBody @Valid  EventPutRequestBody eventPutRequestBody) {
        return new ResponseEntity<>(eventService.update(eventPutRequestBody), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.list.app.repository;

import com.list.app.model.Event;
import com.list.app.model.People;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PeopleRepository extends CrudRepository<People, Integer> {

    Iterable<People> findByEvent(Optional<Event> event);
}

package com.hackerrank.github.repository;

import com.hackerrank.github.model.Event;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findByActor(Long actoreID);
}

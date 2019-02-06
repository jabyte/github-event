package com.hackerrank.github.controller;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubApiRestController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ActorRepository actorRepository;

    @DeleteMapping(path = "/erase")
    public ResponseEntity<Event> eraseAllEvents() {
        eventRepository.deleteAll();
        return ResponseEntity.ok(null);
    }

    @PostMapping(path = "/events")
    public ResponseEntity<Event> addEvent(@RequestBody Event e) {
        Event event = eventRepository.findOne(e.getId());

        if (event == null) {
            return ResponseEntity.status(201).body(eventRepository.save(event));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping(path = "/events")
    public ResponseEntity getAllEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @GetMapping(path = "/events/actors/{actorID}")
    public ResponseEntity getEventsByActor(@PathVariable Long actorID) {
        Actor actor = actorRepository.findOne(actorID);

        if (actor == null) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(eventRepository.findByActor(actorID));
    }

}

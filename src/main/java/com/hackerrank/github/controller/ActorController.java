package com.hackerrank.github.controller;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.repository.ActorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jabyte
 */
@RestController("/actors")
public class ActorController {

    @Autowired
    ActorRepository actorRepository;

    @PutMapping
    public ResponseEntity<Actor> updateAvatar(@RequestBody Actor a) {
        Actor actor = actorRepository.findOne(a.getId());

        if (actor == null) {
            return ResponseEntity.status(404).build();
        } else if (actor.getLogin().equals(a.getLogin())) {
            actor = actorRepository.save(a);
            return ResponseEntity.ok(actor);
        }

        return ResponseEntity.status(400).build();
    }

    @GetMapping
    public ResponseEntity getActorByEventsCount() {
        List<Actor> actorsByEventCount;

        actorsByEventCount = (List<Actor>) actorRepository.findAll();

        return ResponseEntity.ok(actorsByEventCount);
    }

    @GetMapping(path = "/actors/streak")
    public ResponseEntity getActorByMaximumStreakCount() {
        List<Actor> actorsByMaximumStreak;

        actorsByMaximumStreak = (List<Actor>) actorRepository.findAll();

        return ResponseEntity.ok(actorsByMaximumStreak);
    }
}

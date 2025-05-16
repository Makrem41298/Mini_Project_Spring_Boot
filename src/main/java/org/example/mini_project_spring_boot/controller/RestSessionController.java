package org.example.mini_project_spring_boot.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.example.mini_project_spring_boot.entities.Session;
import org.example.mini_project_spring_boot.entities.SubscriptionPlan;
import org.example.mini_project_spring_boot.service.IServiceSession;
import org.example.mini_project_spring_boot.service.IServiceSubscriptionPlan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sessions")
@AllArgsConstructor
public class RestSessionController {

    private final IServiceSession sessionService;
    private IServiceSubscriptionPlan subscriptionPlanService;

    @PostMapping

    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        Optional<SubscriptionPlan> subPlanId= subscriptionPlanService.getSubscriptionPlanrById(session.getSubscriptionPlan().getId());
        session.setSubscriptionPlan(subPlanId.get());
       Session created = sessionService.createSession(session);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        Session session = sessionService.getSessionById(id);
        if (session != null) {
            return new ResponseEntity<>(session, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {
        List<Session> sessions = sessionService.getAllSessions();
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable Long id, @RequestBody Session session) {
        Session existing = sessionService.getSessionById(id);
        if (existing != null) {
            session.setId(id); // Ensure the ID is set for update
            Session updated = sessionService.updateSession(id, session);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        Session session = sessionService.getSessionById(id);
        if (session != null) {
            sessionService.deleteSession(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

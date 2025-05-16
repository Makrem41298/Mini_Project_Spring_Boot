package org.example.mini_project_spring_boot.service;

import lombok.AllArgsConstructor;
import org.example.mini_project_spring_boot.entities.Session;
import org.example.mini_project_spring_boot.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ServiceSession  implements IServiceSession {
    SessionRepository sessionRepository;
    @Override
    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session getSessionById(Long id) {
        return sessionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Session updateSession(Long id, Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);

    }
}

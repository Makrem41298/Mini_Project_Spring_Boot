package org.example.mini_project_spring_boot.service;

import org.example.mini_project_spring_boot.entities.Session;

import java.util.List;

public interface IServiceSession {
    Session createSession(Session session);
    Session getSessionById(Long id);
    List<Session> getAllSessions();
    Session updateSession(Long id, Session session);
    void deleteSession(Long id);
}

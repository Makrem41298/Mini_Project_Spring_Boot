package org.example.mini_project_spring_boot.repository;

import org.example.mini_project_spring_boot.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
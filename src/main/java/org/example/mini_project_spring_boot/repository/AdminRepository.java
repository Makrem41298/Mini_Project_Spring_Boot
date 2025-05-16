package org.example.mini_project_spring_boot.repository;

import org.example.mini_project_spring_boot.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
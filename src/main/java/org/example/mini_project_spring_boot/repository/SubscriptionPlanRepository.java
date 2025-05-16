package org.example.mini_project_spring_boot.repository;

import org.example.mini_project_spring_boot.entities.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {
}
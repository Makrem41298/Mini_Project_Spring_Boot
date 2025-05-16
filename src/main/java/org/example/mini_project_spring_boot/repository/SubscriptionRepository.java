package org.example.mini_project_spring_boot.repository;

import org.example.mini_project_spring_boot.entities.Subscription;
import org.example.mini_project_spring_boot.entities.SubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {
}
package org.example.mini_project_spring_boot.service;

import org.example.mini_project_spring_boot.entities.Member;
import org.example.mini_project_spring_boot.entities.SubscriptionPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IServiceSubscriptionPlan {
    SubscriptionPlan save(SubscriptionPlan subscriptionPlan);
    List<SubscriptionPlan> getAllSubscriptionPlan();
    Optional<SubscriptionPlan> getSubscriptionPlanrById(Long id);
    SubscriptionPlan upadetSubscriptionPlan(Long id, SubscriptionPlan subscriptionPlan);

    void deleteSubscriptionPlan(Long id);
}

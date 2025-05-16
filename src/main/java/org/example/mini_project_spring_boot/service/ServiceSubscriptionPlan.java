package org.example.mini_project_spring_boot.service;

import lombok.AllArgsConstructor;
import org.example.mini_project_spring_boot.entities.SubscriptionPlan;
import org.example.mini_project_spring_boot.repository.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class ServiceSubscriptionPlan implements IServiceSubscriptionPlan {
    SubscriptionPlanRepository subscriptionPlanRepository;
    @Override
    public SubscriptionPlan save(SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    @Override
    public List<SubscriptionPlan> getAllSubscriptionPlan() {
        return subscriptionPlanRepository.findAll();
    }

    @Override
    public Optional<SubscriptionPlan> getSubscriptionPlanrById(Long id) {
        return subscriptionPlanRepository.findById(id);
    }

    @Override
    public SubscriptionPlan upadetSubscriptionPlan(Long id, SubscriptionPlan subscriptionPlan) {
        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    @Override
    public void deleteSubscriptionPlan(Long id) {
        subscriptionPlanRepository.deleteById(id);

    }
}

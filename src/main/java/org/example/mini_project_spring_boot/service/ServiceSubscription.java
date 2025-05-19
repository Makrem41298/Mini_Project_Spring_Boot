package org.example.mini_project_spring_boot.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.mini_project_spring_boot.entities.Subscription;
import org.example.mini_project_spring_boot.entities.SubscriptionId;
import org.example.mini_project_spring_boot.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceSubscription implements IServiceSubscription {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription create(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getById(SubscriptionId id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    @Override
    public Subscription update(SubscriptionId id, Subscription subscription) {
        subscription.setId(id);
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void delete(SubscriptionId id) {
        subscriptionRepository.deleteById(id);
    }
}

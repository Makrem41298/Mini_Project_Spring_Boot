package org.example.mini_project_spring_boot.service;

import org.example.mini_project_spring_boot.entities.Subscription;
import org.example.mini_project_spring_boot.entities.SubscriptionId;

public interface IServiceSubscription {
    Subscription create(Subscription subscription);
    Subscription getById(SubscriptionId id);
    Subscription update(SubscriptionId id, Subscription subscription);
    void delete(SubscriptionId id);
}

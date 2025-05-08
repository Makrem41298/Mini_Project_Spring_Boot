package org.example.mini_project_spring_boot.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @EmbeddedId
    private SubscriptionId id;

    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private Membre membre;

    @ManyToOne
    @MapsId("subscriptionPlanId")
    @JoinColumn(name = "subscription_plan_id")
    private SubscriptionPlan subscriptionPlan;

    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfMonths;
    private double totalPayment;
}

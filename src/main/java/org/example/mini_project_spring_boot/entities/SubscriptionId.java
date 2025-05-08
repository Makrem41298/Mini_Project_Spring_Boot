package org.example.mini_project_spring_boot.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SubscriptionId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long memberId;
    private Long subscriptionPlanId;
}

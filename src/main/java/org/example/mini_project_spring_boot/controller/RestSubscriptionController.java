package org.example.mini_project_spring_boot.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.mini_project_spring_boot.entities.Member;
import org.example.mini_project_spring_boot.entities.Subscription;
import org.example.mini_project_spring_boot.entities.SubscriptionId;
import org.example.mini_project_spring_boot.entities.SubscriptionPlan;
import org.example.mini_project_spring_boot.service.IServiceMember;
import org.example.mini_project_spring_boot.service.IServiceSubscription;
import org.example.mini_project_spring_boot.service.IServiceSubscriptionPlan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/subscription")
@AllArgsConstructor
public class RestSubscriptionController {

    IServiceSubscription subscriptionService;
    IServiceMember memberService;
    IServiceSubscriptionPlan subscriptionPlanService;

    @PostMapping
    public ResponseEntity<Subscription> create(@RequestBody Subscription subscription) {


        Member member = memberService.getMemberById(subscription.getId().getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        SubscriptionPlan plan = subscriptionPlanService.getSubscriptionPlanrById(subscription.getId().getSubscriptionPlanId())
                .orElseThrow(() -> new RuntimeException("Subscription Plan not found"));

        SubscriptionId subId = new SubscriptionId(member.getId(), plan.getId());

        subscription.setId(subId);
        subscription.setMember(member);
        subscription.setSubscriptionPlan(plan);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(subscription.getNumberOfMonths()));
        subscription.setTotalPayment(plan.getMonthlyFee() * subscription.getNumberOfMonths());

        Subscription saved = subscriptionService.create(subscription);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @GetMapping("/{memberId}/{planId}")
    public Subscription getById(@PathVariable Long memberId, @PathVariable Long planId) {
        SubscriptionId id = new SubscriptionId(memberId, planId);

        return subscriptionService.getById(id);
    }

    @PutMapping("/{memberId}/{planId}")
    public ResponseEntity<Subscription> update(@PathVariable Long memberId,
                                               @PathVariable Long planId,
                                               @RequestBody Subscription subscription) {
        SubscriptionId id = new SubscriptionId(memberId, planId);
        return new ResponseEntity<>(subscriptionService.update(id, subscription), HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}/{planId}")
    public ResponseEntity<Void> delete(@PathVariable Long memberId, @PathVariable Long planId) {
        SubscriptionId id = new SubscriptionId(memberId, planId);
        subscriptionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

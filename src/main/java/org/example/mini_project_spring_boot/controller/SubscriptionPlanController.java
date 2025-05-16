package org.example.mini_project_spring_boot.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.mini_project_spring_boot.entities.SubscriptionPlan;
import org.example.mini_project_spring_boot.service.IServiceSubscriptionPlan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/subscription-plans")
public class SubscriptionPlanController {

    private final IServiceSubscriptionPlan serviceSubscriptionPlan;

    @GetMapping
    public String listPlans(Model model) {
        model.addAttribute("plans", serviceSubscriptionPlan.getAllSubscriptionPlan());
        return "subscriptionplans/list";
    }

    // Show form to create a new plan
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("subscriptionPlan", new SubscriptionPlan());
        return "subscriptionplans/form";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@Valid @ModelAttribute("subscriptionPlan") SubscriptionPlan plan,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "subscriptionplans/form";
        }
        serviceSubscriptionPlan.save(plan);
        return "redirect:/subscription-plans";
    }

    // Show form to edit an existing plan
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        serviceSubscriptionPlan.getSubscriptionPlanrById(id)
                .ifPresent(plan -> model.addAttribute("subscriptionPlan", plan));
        return "subscriptionplans/form";
    }

    // Delete a plan
    @GetMapping("/delete/{id}")
    public String deletePlan(@PathVariable Long id) {
        serviceSubscriptionPlan.deleteSubscriptionPlan(id);
        return "redirect:/subscription-plans";
    }

    // View plan details
    @GetMapping("/view/{id}")
    public String viewDetails(@PathVariable Long id, Model model) {
        serviceSubscriptionPlan.getSubscriptionPlanrById(id)
                .ifPresent(plan -> model.addAttribute("plan", plan));
        return "subscriptionplans/view";
    }
}

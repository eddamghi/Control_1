package com.eddamghi.Application.web.controllers;

import com.eddamghi.Application.DAO.entities.Client;
import com.eddamghi.Application.DAO.entities.Subscription;
import com.eddamghi.Application.DAO.repositories.ClientRepository;
import com.eddamghi.Application.DAO.repositories.SubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class SubscriptionController {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    private ClientRepository clientRepository;

    @GetMapping("/admin/clients/{clientId}/subscriptions")
    public String subscriptions(
            Model model, @PathVariable Long clientId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        var subscriptions = subscriptionRepository
                .findByClientId(clientId, PageRequest.of(page, size));
        model.addAttribute("listSubscriptions", subscriptions.getContent());
        model.addAttribute("clientId", clientId);
        model.addAttribute("pages", new int[subscriptions.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "subscriptions";
    }
    @DeleteMapping("/admin/clients/{clientId}/subscriptions/{subscriptionId}")
    public String deleteSubscription(@PathVariable Long clientId, @PathVariable Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
        return "redirect:/admin/clients/" + clientId + "/subscriptions";
    }
    @PostMapping("/admin/clients/{clientId}/subscriptions/{subscriptionId}")
    public String updateSubscription(@PathVariable Long clientId, @PathVariable Subscription subscription) {
        subscriptionRepository.save(subscription);
        return "redirect:/admin/clients/" + clientId + "/subscriptions";
    }
}

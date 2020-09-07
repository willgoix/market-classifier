package com.github.willgoix.marketclassifier.controllers;

import com.github.willgoix.marketclassifier.models.Collaborator;
import com.github.willgoix.marketclassifier.models.Market;
import com.github.willgoix.marketclassifier.repositories.MarketRepository;
import com.github.willgoix.marketclassifier.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Willian Gois (github/willgoix) - 03/09/2020
 */
@Controller
public class MarketsController {

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/markets", method = RequestMethod.GET)
    public String getMarkets(Model model) {
        Authentication authentication = this.authenticationService.findSessionAuthentication();

        model.addAttribute("collaborator", (Collaborator) authentication.getPrincipal());
        model.addAttribute("markets", this.marketRepository.findAll());

        return "markets";
    }

    @RequestMapping(value = "/markets", method = RequestMethod.POST)
    public String getMarkets(Model model, String search) {
        Authentication authentication = this.authenticationService.findSessionAuthentication();
        model.addAttribute("collaborator", (Collaborator) authentication.getPrincipal());

        List<Market> markets = search == null ? this.marketRepository.findAll() : this.marketRepository.findAllByNameContainingIgnoreCase(search);
        model.addAttribute("markets", markets);

        return "markets";
    }

}
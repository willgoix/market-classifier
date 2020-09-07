package com.github.willgoix.marketclassifier.controllers;

import com.github.willgoix.marketclassifier.models.Collaborator;
import com.github.willgoix.marketclassifier.models.Market;
import com.github.willgoix.marketclassifier.models.enums.EnumCritera;
import com.github.willgoix.marketclassifier.repositories.CollaboratorRepository;
import com.github.willgoix.marketclassifier.repositories.MarketRepository;
import com.github.willgoix.marketclassifier.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Willian Gois (github/willgoix) - 03/09/2020
 */
@Controller
public class ClassifyController {

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/markets/{marketId}", method = RequestMethod.POST)
    public String getClassify(Model model, @PathVariable Long marketId) {
        Authentication authentication = this.authenticationService.findSessionAuthentication();
        model.addAttribute("collaborator", (Collaborator) authentication.getPrincipal());

        Market market = this.marketRepository.getOne(marketId);

        model.addAttribute("market", market);
        model.addAttribute("ratingForm", new RatingForm());

        return "classify";
    }

    @RequestMapping(value = "/markets/classify/{marketId}", method = RequestMethod.POST)
    public String onClassify(Model model, RatingForm ratingForm, @PathVariable Long marketId) {
        Authentication authentication = this.authenticationService.findSessionAuthentication();
        Collaborator collaborator = (Collaborator) authentication.getPrincipal();
        collaborator.setLastVote(Date.from(Instant.now()));

        model.addAttribute("collaborator", collaborator);

        Market market = this.marketRepository.getOne(marketId);

        ratingForm.getRatings().forEach((critera, value) -> {
            market.getRating(critera).incrementVote(value == null ? 0 : value);
        });

        this.marketRepository.save(market);
        this.collaboratorRepository.save(collaborator);

        return "redirect:/markets";
    }

    public static class RatingForm {

        private Map<EnumCritera, Integer> ratings = new HashMap<>();

        public RatingForm() {
            Stream.of(EnumCritera.values()).forEach(critera -> this.ratings.put(critera, null));
        }

        public void setRatings(Map<EnumCritera, Integer> rantings) {
            this.ratings = rantings;
        }

        public Map<EnumCritera, Integer> getRatings() {
            return this.ratings;
        }
    }
}

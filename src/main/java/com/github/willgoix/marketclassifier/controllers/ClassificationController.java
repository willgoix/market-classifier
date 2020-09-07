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

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author Willian Gois (github/willgoix) - 03/09/2020
 */
@Controller
public class ClassificationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private MarketRepository marketRepository;

    private Market bestMarket;

    @RequestMapping(value = "/classification", method = RequestMethod.GET)
    public String getClassification(Model model) {
        Authentication authentication = this.authenticationService.findSessionAuthentication();

        model.addAttribute("collaborator", (Collaborator) authentication.getPrincipal());
        model.addAttribute("market", getBestMarket());
        model.addAttribute("nextMarketDate", getNextFriday(LocalDate.now()));

        return "classification";
    }

    /**
     * Define o melhor mercado através de uma média ponderada baseada na nota/quantidade de avaliações.
     *
     * @return O melhor mercado da semana.
     */
    private Market getBestMarket() {
        if (this.bestMarket == null) {
            this.bestMarket = this.marketRepository.findAll().stream()
                    .reduce((partial, market) -> this.canBeTheBest(market) && market.getWeightedAverage() > partial.getWeightedAverage() ? market : partial)
                    .orElse(null);

            this.bestMarket.setLastTimeWasBetter(Date.from(Instant.now()));
            this.marketRepository.saveAndFlush(this.bestMarket);
        }

        return this.bestMarket;
    }

    /**
     * Retorna se um mercado pode ser escolhido como o melhor mercado da semana, verificando
     * se o último dia do mês que foi escolhido já se passou. Ou seja, se a semana que foi escolhido
     * não é do mês atual.
     *
     * @param market O mercado a ser avaliado.
     * @return Se o mercado informado pode ser escolhido como melhor mercado.
     */
    private boolean canBeTheBest(Market market) {
        return market.getLastTimeWasBetter() == null || market.getLastTimeWasBetter().toInstant().with(TemporalAdjusters.lastDayOfMonth()).isBefore(Instant.now());
    }

    private LocalDate getNextFriday(LocalDate date) {
        return date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
    }
}

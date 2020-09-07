package com.github.willgoix.marketclassifier;

import com.github.willgoix.marketclassifier.models.Market;
import com.github.willgoix.marketclassifier.models.enums.EnumCritera;
import com.github.willgoix.marketclassifier.repositories.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Willian Gois (github/willgoix) - 02/09/2020
 */
@SpringBootApplication
public class Application {

    @Autowired
    private MarketRepository marketRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // MERCADOS
            List<Market> markets = new ArrayList<>();

            // Supermercado Andreazza
            Market andreazza = new Market();
            andreazza.setName("Supermercado Andreazza");
            andreazza.setImageUrl("https://i.imgur.com/NfWkPey_d.webp?maxwidth=728&fidelity=grand");
            andreazza.getRating(EnumCritera.PRECOS).incrementVote(5);
            andreazza.getRating(EnumCritera.ATENDIMENTO).incrementVote(3);
            andreazza.getRating(EnumCritera.FILAS).incrementVote(3);
            andreazza.getRating(EnumCritera.HIGIENE).incrementVote(3);
            andreazza.getRating(EnumCritera.ESTACIONAMENTO).incrementVote(4);
            andreazza.getRating(EnumCritera.LOCALIZACAO).incrementVote(4);
            markets.add(andreazza);

            // Imec
            Market imec = new Market();
            imec.setName("Supermercado Imec");
            imec.setImageUrl("https://i.imgur.com/4XP6mz2.jpg");
            markets.add(imec);

            // Ortafrutti
            Market ortafrutti = new Market();
            ortafrutti.setName("Ortafrutti");
            ortafrutti.setImageUrl("https://i.imgur.com/ZT99M0x.jpg");
            ortafrutti.getRating(EnumCritera.ESTACIONAMENTO).incrementVote(2);
            markets.add(ortafrutti);

            // Rede Polo
            Market redePolo = new Market();
            redePolo.setName("Rede Polo");
            redePolo.setImageUrl("https://i.imgur.com/31NmJG8.jpg");
            markets.add(redePolo);

            this.marketRepository.saveAll(markets);
            this.marketRepository.flush();
        };
    }
}

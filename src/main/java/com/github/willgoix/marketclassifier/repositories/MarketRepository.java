package com.github.willgoix.marketclassifier.repositories;

import com.github.willgoix.marketclassifier.models.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Willian Gois (github/willgoix) - 02/09/2020
 */
public interface MarketRepository extends JpaRepository<Market, Long> {

    Market findByName(String name);

    List<Market> findAllByNameContainingIgnoreCase(String containing);
}

package com.github.willgoix.marketclassifier.repositories;

import com.github.willgoix.marketclassifier.models.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Willian Gois (github/willgoix) - 02/09/2020
 */
public interface CollaboratorRepository extends JpaRepository<Collaborator, String> {
}

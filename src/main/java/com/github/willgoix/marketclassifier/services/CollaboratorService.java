package com.github.willgoix.marketclassifier.services;

import com.github.willgoix.marketclassifier.models.Collaborator;
import com.github.willgoix.marketclassifier.repositories.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Willian Gois (github/willgoix) - 02/09/2020
 */
@Service
public class CollaboratorService {

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(Collaborator collaborator) {
        String encodedPassword = this.bCryptPasswordEncoder.encode(collaborator.getPassword());
        collaborator.setPassword(encodedPassword);

        this.collaboratorRepository.save(collaborator);
    }

    public Optional<Collaborator> findByEmail(String email) {
        return this.collaboratorRepository.findById(email);
    }
}

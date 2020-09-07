package com.github.willgoix.marketclassifier.services;

import com.github.willgoix.marketclassifier.models.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Willian Gois (github/willgoix) - 03/09/2020
 */
@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public Authentication findSessionAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void autoLogin(Collaborator collaborator, String rawPassword) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                collaborator,
                rawPassword,
                Collections.emptyList()
        );

        this.authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
}

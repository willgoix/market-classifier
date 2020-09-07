package com.github.willgoix.marketclassifier.controllers;

import com.github.willgoix.marketclassifier.models.Collaborator;
import com.github.willgoix.marketclassifier.services.AuthenticationService;
import com.github.willgoix.marketclassifier.services.CollaboratorService;
import com.github.willgoix.marketclassifier.validator.CollaboratorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Willian Gois (github/willgoix) - 02/09/2020
 */
@Controller
public class RegisterController {

    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CollaboratorValidator collaboratorValidator;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model) {
        model.addAttribute("collaborator", new Collaborator());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String onRegister(Model model, Collaborator collaborator, BindingResult bindingResult) {
        this.collaboratorValidator.validate(collaborator, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("registerError", bindingResult.getFieldError().getCode());
            return "register";
        }

        String rawPassword = collaborator.getPassword();

        this.collaboratorService.save(collaborator);

        this.authenticationService.autoLogin(collaborator, rawPassword);

        return "redirect:/classification";
    }
}

package com.github.willgoix.marketclassifier.controllers;

import com.github.willgoix.marketclassifier.services.AuthenticationService;
import com.github.willgoix.marketclassifier.services.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Willian Gois (github/willgoix) - 02/09/2020
 */
@Controller
public class AuthController {

    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String getAuth() {
        return "auth";
    }

    @RequestMapping(value = "/auth-error", method = RequestMethod.GET)
    public String getAuthError(Model model) {
        model.addAttribute("authError", true);

        return "redirect:/auth";
    }

    @RequestMapping(value = "logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }
}

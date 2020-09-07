package com.github.willgoix.marketclassifier.validator;

import com.github.willgoix.marketclassifier.models.Collaborator;
import com.github.willgoix.marketclassifier.services.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * @author Willian Gois (github/willgoix) - 05/09/2020
 */
@Component
public class CollaboratorValidator implements Validator {

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$");

    @Autowired
    private CollaboratorService collaboratorService;

    @Override
    public void validate(Object o, Errors errors) {
        Collaborator collaborator = (Collaborator) o;

        // email not empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

        // email valid
        if (!EMAIL_REGEX.matcher(collaborator.getEmail()).matches()) {
            errors.rejectValue("email", "Utilize um email válido.");
        }

        // username already exists
        if (this.collaboratorService.findByEmail(collaborator.getEmail()).isPresent()) {
            errors.rejectValue("email", "Este email já está sendo utilizado.");
        }

        // password not empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

        // password size
        if (collaborator.getPassword().length() < 4 || collaborator.getPassword().length() > 32) {
            errors.rejectValue("password", "A senha deve conter de 4 à 32 caracteres.");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Collaborator.class.equals(aClass);
    }
}
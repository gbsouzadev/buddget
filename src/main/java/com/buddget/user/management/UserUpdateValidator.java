package com.buddget.user.management;

import com.buddget.controllers.exceptions.FieldMessage;
import com.buddget.entities.User;
import com.buddget.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateRequest> {

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserUpdateRequest payload, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        User user = userRepository.findByEmail(payload.email());
        User verifier = userRepository.findByEmail(payload.newEmail());

        if(verifier != null && verifier.getEmail() != user.getEmail()) {
            list.add(new FieldMessage("email", "The email address is already registered."));
        }


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
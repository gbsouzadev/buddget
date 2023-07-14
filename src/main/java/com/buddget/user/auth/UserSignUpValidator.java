package com.buddget.user.auth;

import com.buddget.controllers.exceptions.FieldMessage;
import com.buddget.entities.User;
import com.buddget.user.UserRepository;
import com.buddget.user.auth.UserAuthDTO;
import com.buddget.user.auth.UserSignUpValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class UserSignUpValidator implements ConstraintValidator<UserSignUpValid, UserAuthDTO.Request.SignUp> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserSignUpValid ann) {
    }

    @Override
    public boolean isValid(UserAuthDTO.Request.SignUp signUp, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        User user = userRepository.findByEmail(signUp.email());
        if (user != null) {
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
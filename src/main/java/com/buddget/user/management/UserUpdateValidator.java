package com.buddget.user.management;

import com.buddget.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateRequest> {

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserUpdateRequest dto, ConstraintValidatorContext context) {

//        @SuppressWarnings("unchecked")
//        var uriVars = (Map<String, String>) servletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//        long userId = Long.parseLong(uriVars.get("id"));
//
//        List<FieldMessage> list = new ArrayList<>();
//
//        User user = userRepository.findByEmail(dto.getEmail());
//        if(user != null && userId != user.getId()) {
//            list.add(new FieldMessage("email", "The email address is already registered."));
//        }
//
//
//        for (FieldMessage e : list) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
//                    .addConstraintViolation();
//        }
//        return list.isEmpty();
        return false;
    }
}
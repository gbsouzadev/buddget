package com.buddget.dto;

import com.buddget.services.validation.UserUpdateValid;

import java.io.Serializable;

@UserUpdateValid
public class UserUpdateDTO extends UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
}

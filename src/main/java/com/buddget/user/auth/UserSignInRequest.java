package com.buddget.user.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserSignInRequest(@Email String email,
                                @NotBlank(message = "This field is required.") String password) {
}





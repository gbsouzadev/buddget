package com.buddget.user.auth;

import jakarta.validation.constraints.NotBlank;

public record UserSignInResponse(@NotBlank String token) {
}





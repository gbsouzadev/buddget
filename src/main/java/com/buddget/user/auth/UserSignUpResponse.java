package com.buddget.user.auth;

import jakarta.validation.constraints.Positive;

public record UserSignUpResponse(@Positive Long Id) {
}





package com.buddget.user.auth;

import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record UserSignUpResponse(@Positive UUID id) {
}





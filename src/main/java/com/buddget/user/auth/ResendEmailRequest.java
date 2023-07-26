package com.buddget.user.auth;

import jakarta.validation.constraints.Email;

public record ResendEmailRequest(@Email String email) {
}

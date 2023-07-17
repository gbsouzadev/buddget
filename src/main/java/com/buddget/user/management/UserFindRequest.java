package com.buddget.user.management;

import jakarta.validation.constraints.Email;

public record UserFindRequest(@Email String email) {
}

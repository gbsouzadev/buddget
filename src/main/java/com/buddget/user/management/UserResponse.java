package com.buddget.user.management;

import com.buddget.entities.Role;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record UserResponse(UUID id, String firstName, String lastName, String email, Instant dateCreated, Instant lastLogin,
                           Set<Role> roles) {
}

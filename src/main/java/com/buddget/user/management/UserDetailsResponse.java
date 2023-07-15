package com.buddget.user.management;

import com.buddget.entities.Role;
import jakarta.validation.constraints.*;

import java.time.Instant;
import java.util.Set;

public record UserDetailsResponse(@NotBlank(message = "This field is required.") String firstName,
                                  @NotBlank(message = "This field is required.") String lastName,
                                  @Email(message = "Please enter a valid email address.") String email,
                                  Instant dateCreated,
                                  Instant lastLogin,
                                  Set<Role> roles) {
}

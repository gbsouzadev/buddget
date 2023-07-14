package com.buddget.user.auth;

import jakarta.validation.constraints.*;
import lombok.Value;

/*private Long id;
@NotBlank(message = "This field is required.")
private String firstName;
@NotBlank(message = "This field is required.")
private String lastName;
@Email(message = "Please enter a valid email address.")
private String email;
private Instant dateCreated;
private Instant lastLogin;
private Set<RoleDTO> roles = new HashSet<>();
*/


public enum UserAuthDTO {
    ;

    public enum Request {
        ;

        public record SignUp(
                @NotBlank(message = "This field is required.") String firstName,
                @NotBlank(message = "This field is required.") String lastName,
                @Email(message = "Please enter a valid email address.") String email,
                @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[a-zA-Z\\d!@#$%^&*]{8,}$",
                        message = "Your password should be a minimum of 8 characters long and include at least 1 lowercase letter, 1 uppercase letter, 1 number, and 1 special character.") String password) {

        }

        public record SignIn(@Email String email,
                             @NotBlank(message = "This field is required.") String password) {

        }
    }

    public enum Response {
        ;

        public record SignUp(@Positive Long Id) {
        }

        public record SignIn(@NotBlank String token) {
        }
    }

}

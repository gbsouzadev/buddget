package com.buddget.dto;

import com.buddget.services.validation.UserInsertValid;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@UserInsertValid
public class UserInsertDTO extends UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[a-zA-Z\\d!@#$%^&*]{8,}$",
            message = "Your password should be a minimum of 8 characters long and include at least 1 lowercase letter, 1 uppercase letter, 1 number, and 1 special character.")
    private String password;

    public UserInsertDTO() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

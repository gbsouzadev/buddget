package com.buddget.dto;

import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public class UserInsertDTO extends UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[a-zA-Z\\d!@#$%^&*]{8,}$",
            message = "Your password should be a minimum of 8 characters long and include at least 1 lowercase letter, 1 uppercase letter, 1 number, and 1 special character.")
    private String password;
            /*
            Pelo menos 8 caracteres de comprimento
            Pelo menos uma letra maiúscula
            Pelo menos uma letra minúscula
            Pelo menos um número
            Pelo menos um caractere especiail
            */

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

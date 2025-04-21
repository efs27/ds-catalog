package com.efsaplicativos.dscatalog.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class NewPasswordDto {

    @NotBlank(message = "Campo token é obrigatório")
    private String token;
    @NotBlank(message = "Campo token é obrigatório")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String password;

    public NewPasswordDto() {
    }

    public NewPasswordDto(String token, String password) {
        this.token = token;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

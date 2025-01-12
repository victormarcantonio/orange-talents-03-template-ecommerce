package com.orange.mercadolivre.cadastroUsuario;

import com.orange.mercadolivre.validator.CampoUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {
    @Email
    @NotBlank
    @CampoUnico(domainClass = Usuario.class, fieldName = "login")
    private String login;
    @NotBlank
    @Size(min = 6)
    private String senha;


    public UsuarioRequest(@Email @NotBlank String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public Usuario converter(){
        return new Usuario(login, new SenhaLimpa(senha));
    }
}

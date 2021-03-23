package com.orange.mercadolivre.cadastroUsuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @PastOrPresent
    private LocalDateTime instante = LocalDateTime.now();
    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Size(min = 6)
    private String senha;

    //Utilizado apenas pelo Hibernate
    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", instante=" + instante +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}

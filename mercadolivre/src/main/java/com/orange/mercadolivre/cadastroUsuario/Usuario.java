package com.orange.mercadolivre.cadastroUsuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;


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

    public Usuario(@NotBlank @Email String login, @NotBlank @Size(min = 6) @NotNull SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(login), "Não pode estar vazio");
        Assert.notNull(senhaLimpa, "Senha limpa não pode ser nula");
        this.login = login;
        this.senha = senhaLimpa.hash();
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

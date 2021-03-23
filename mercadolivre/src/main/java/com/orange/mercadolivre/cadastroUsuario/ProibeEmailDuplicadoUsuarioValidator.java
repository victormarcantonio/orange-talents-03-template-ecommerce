package com.orange.mercadolivre.cadastroUsuario;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeEmailDuplicadoUsuarioValidator implements Validator {


    private UsuarioRepository usuarioRepository;

    public ProibeEmailDuplicadoUsuarioValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsuarioRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
       UsuarioRequest request = (UsuarioRequest) target;
       Optional<Usuario> possivelUsuario = usuarioRepository.findByLogin(request.getLogin());
        if (possivelUsuario.isPresent()) {

            errors.rejectValue("login", null, "Já existe um login com este e-mail "+ request.getLogin());
        }
    }
}

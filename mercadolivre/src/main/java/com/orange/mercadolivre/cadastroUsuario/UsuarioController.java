package com.orange.mercadolivre.cadastroUsuario;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;
    private ProibeEmailDuplicadoUsuarioValidator proibeEmailDuplicadoUsuarioValidator;


    public UsuarioController(UsuarioRepository usuarioRepository, ProibeEmailDuplicadoUsuarioValidator proibeEmailDuplicadoUsuarioValidator) {
        this.usuarioRepository = usuarioRepository;
        this.proibeEmailDuplicadoUsuarioValidator = proibeEmailDuplicadoUsuarioValidator;
    }

    @PostMapping
    @Transactional
    public String cadastrar(@RequestBody @Valid UsuarioRequest request){
        Usuario usuario = request.converter();
        usuarioRepository.save(usuario);
        return usuario.toString();
    }
}

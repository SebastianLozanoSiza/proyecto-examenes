package com.sistema.examenes.services;

import java.util.Set;

import com.sistema.examenes.repositories.entities.Usuario;
import com.sistema.examenes.repositories.entities.UsuarioRol;

public interface ServiceUsuario {

    Usuario findByUsername(String username);
    
    Usuario save(Usuario usuario, Set<UsuarioRol> ususarioRoles) throws Exception;

    void delete(Long id);
}

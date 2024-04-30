package com.sistema.examenes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sistema.examenes.repositories.entities.Usuario;

public interface RepositoryUsuario extends CrudRepository<Usuario,Long> {
    
    Usuario findByUsername(String username);
}

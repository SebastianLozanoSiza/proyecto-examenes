package com.sistema.examenes.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.examenes.repositories.RepositoryRol;
import com.sistema.examenes.repositories.RepositoryUsuario;
import com.sistema.examenes.repositories.entities.Usuario;
import com.sistema.examenes.repositories.entities.UsuarioRol;
import com.sistema.examenes.services.ServiceUsuario;

@Service
public class ServiceUsuarioImpl implements ServiceUsuario {

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    @Autowired
    private RepositoryRol repositoryRol;


    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return repositoryUsuario.findByUsername(username);
    } 

    @Override
    @Transactional
    public Usuario save(Usuario usuario, Set<UsuarioRol> ususarioRoles) throws Exception {
        Usuario usuarioLocal = repositoryUsuario.findByUsername(usuario.getUsername());
        if (usuarioLocal!= null) {
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        }else{
            for (UsuarioRol usuarioRol : ususarioRoles) {
                repositoryRol.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(ususarioRoles);
            usuarioLocal = repositoryUsuario.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repositoryUsuario.deleteById(id);
    } 
    
}

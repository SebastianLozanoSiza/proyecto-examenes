package com.sistema.examenes.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.sistema.examenes.repositories.entities.Rol;
import com.sistema.examenes.repositories.entities.Usuario;
import com.sistema.examenes.repositories.entities.UsuarioRol;
import com.sistema.examenes.services.ServiceUsuario;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/{username}")
    public Usuario findByUsername(@PathVariable("username") String username) {
        return serviceUsuario.findByUsername(username);
    }

    @PostMapping("/")
    public Usuario save(@RequestBody Usuario usuario) throws Exception {
        usuario.setPerfil("default.png");
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setId(2L);
        rol.setNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        return serviceUsuario.save(usuario, usuarioRoles);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        serviceUsuario.delete(id);
    }
}
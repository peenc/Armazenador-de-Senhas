package com.pedropc.senhas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedropc.senhas.models.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long>{


    Optional<Usuarios> findByUsuario(String usuario);

}

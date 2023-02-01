package com.pedropc.senhas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedropc.senhas.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}

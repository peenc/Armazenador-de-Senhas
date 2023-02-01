package com.pedropc.senhas.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pedropc.senhas.models.Pessoa;
import com.pedropc.senhas.models.Senha;

public interface SenhaRepository extends CrudRepository<Senha, Long>{
	Iterable<Senha> findByPessoa(Pessoa pessoa);
	Iterable<Senha> findByLocal(String local);
	Senha findById(String id);
}

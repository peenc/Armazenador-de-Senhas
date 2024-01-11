package com.pedropc.senhas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pedropc.senhas.models.Pessoa;
import com.pedropc.senhas.models.Senha;

@Repository
public interface SenhaRepository extends CrudRepository<Senha, Long>{
	Iterable<Senha> findByPessoa(Pessoa pessoa);
	Iterable<Senha> findByLocal(String local);
	Senha findById(String id);
}

package com.pedropc.senhas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedropc.senhas.models.Pessoa;
import com.pedropc.senhas.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public Iterable<Pessoa> findAll() {
		return repository.findAll();
	}

	public Pessoa findByName(String nome) {
		for (Pessoa pessoa : repository.findAll()) {

			if (pessoa.getNome().equals(nome)) {
				return pessoa;
			}	
		}
		return null;
	}

	public Pessoa findById(Long id) {
		Optional<Pessoa> pessoa = repository.findById(id);
		return pessoa.orElseThrow();
	}

	public Pessoa insert(Pessoa pessoa) {
		
		return repository.save(pessoa);
	}
	
	public Boolean validarPessoa(Pessoa pessoa) {
		Iterable<Pessoa> list = findAll();
		for(Pessoa p : list) {
			if(p.getNome().equals(pessoa.getNome())) {
				return true;
			}
		}
		return false;
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}
	
	public void delete(Pessoa obj) {
		repository.delete(obj);
	}

}

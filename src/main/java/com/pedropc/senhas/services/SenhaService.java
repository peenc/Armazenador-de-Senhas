package com.pedropc.senhas.services;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedropc.senhas.models.Pessoa;
import com.pedropc.senhas.models.Senha;
import com.pedropc.senhas.repositories.SenhaRepository;

@Service
public class SenhaService {

	@Autowired
	SenhaRepository repository;

	@Autowired
	PessoaService service;

	public Senha insert(Long id, Senha senha) {
		Pessoa p1 = service.findById(id);

		embaralhaSenha(senha);
		senha.setPessoa(p1);
		return repository.save(senha);
	}

	public Senha update(Long idSenha, Senha obj) {

		Optional<Senha> senha = repository.findById(idSenha);
		Senha senhaNova = senha.orElseThrow();
		updateData(senhaNova, obj);
		embaralhaSenha(senhaNova);
		return repository.save(senhaNova);

	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Iterable<Senha> findByPessoa(Pessoa pessoa) {

		return repository.findByPessoa(pessoa);
	}

	public void updateData(Senha entity, Senha obj) {
		entity.setAcesso(obj.getAcesso());
	}

	public Senha findById(Long id) {
		Optional<Senha> senha = repository.findById(id);
		return senha.orElseThrow();
	}

	public Iterable<Senha> findByLocal(String local) {
		return repository.findByLocal(local);

	}

	public Senha embaralhaSenha(Senha senha) {
		String senhaEmbaralhada = Base64.getEncoder().encodeToString(senha.getAcesso().getBytes());
		senha.setAcesso(senhaEmbaralhada);
		return senha;
	}

	public Senha desembaralhaSenha(Senha senha) {
		byte[] senhaEmbaralhada = Base64.getDecoder().decode(senha.getAcesso());

		String senhaDesembaralhada = new String(senhaEmbaralhada);
		senha.setAcesso(senhaDesembaralhada);
		return senha;
	}
}

package com.pedropc.senhas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedropc.senhas.models.Pessoa;
import com.pedropc.senhas.models.Senha;
import com.pedropc.senhas.services.PessoaService;
import com.pedropc.senhas.services.SenhaService;

@RestController
@RequestMapping(value = "/senhas")
public class SenhaResource {

	@Autowired
	private SenhaService service;

	@Autowired
	private PessoaService pessoaService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Senha> findById(@PathVariable Long id) {
		Senha senha = service.findById(id);
		service.desembaralhaSenha(senha);
		return ResponseEntity.ok().body(senha);
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<Senha> insertSenha(@PathVariable Long id, @RequestBody Senha senha) {
		Pessoa p1 = pessoaService.findById(id);
		service.embaralhaSenha(senha);
		senha.setPessoa(p1);
		service.insert(id,senha);
		return ResponseEntity.ok().body(senha);

	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Senha> updateSenha(@PathVariable Long id,@RequestBody Senha senha){
		senha = service.findById(id);
		service.embaralhaSenha(senha);
		service.update(id, senha);
		return ResponseEntity.ok().body(senha);

	}
}

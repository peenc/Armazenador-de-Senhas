package com.pedropc.senhas.resources;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.pedropc.senhas.models.Pessoa;
import com.pedropc.senhas.services.PessoaService;

@Controller	
@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService service;


	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll() {
		List<Pessoa> list = (List<Pessoa>) service.findAll();
		return ResponseEntity.ok().body(list);

	}


	@PostMapping
	public ResponseEntity<Pessoa> insertPessoa(@RequestBody Pessoa pessoa) {
		pessoa = service.insert(pessoa);
		return ResponseEntity.ok().body(pessoa);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
		Pessoa pessoa = service.findById(id);
		return ResponseEntity.ok().body(pessoa);
	}
	
	

}

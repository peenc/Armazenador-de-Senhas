package com.pedropc.senhas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pedropc.senhas.models.Pessoa;
import com.pedropc.senhas.models.Senha;
import com.pedropc.senhas.repositories.PessoaRepository;
import com.pedropc.senhas.repositories.SenhaRepository;
import com.pedropc.senhas.services.PessoaService;
import com.pedropc.senhas.services.SenhaService;

@Controller
public class PessoaController {

	@Autowired
	PessoaRepository repository;

	@Autowired
	SenhaRepository repository2;

	@Autowired
	SenhaService senhaService;
	
	@Autowired
	PessoaService pessoaService;

	@GetMapping(value = "/cadastrarpessoa")
	public String form() {
		return "form";
	}

	@PostMapping(value = "/cadastrarpessoa")
	public String form(@Valid Pessoa pessoa,BindingResult result,
			RedirectAttributes attributes) {
		if(pessoa.getNome()== "") {
			attributes.addFlashAttribute("mensagem", "Verifique o campo!");
			return "redirect:/cadastrarpessoa";
		}
		if(pessoaService.validarPessoa(pessoa)) {
			attributes.addFlashAttribute("mensagem", "Pessoa já cadastrada!");
			return "redirect:/cadastrarpessoa";
		}
		pessoaService.insert(pessoa);
		attributes.addFlashAttribute("mensagem", "Pessoa cadastrada com sucesso!");
		return "redirect:/cadastrarpessoa";
	}

	@RequestMapping("/pessoas")
	public ModelAndView listaPessoas() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Pessoa> pessoas = pessoaService.findAll();
		mv.addObject("pessoas", pessoas);
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detalhesPessoa(@PathVariable("id") Long id) {
		Pessoa pessoa = pessoaService.findById(id);
		ModelAndView mv = new ModelAndView("details");
		mv.addObject("pessoa", pessoa);

		Iterable<Senha> senhas = senhaService.findByPessoa(pessoa);
		mv.addObject("senhas", senhas);
		return mv;
	}
	
	@RequestMapping("/deletar")
	public String deletarPessoa(long id, RedirectAttributes attributes) {
		Pessoa pessoa = pessoaService.findById(id);
		List<Senha> list = pessoa.getSenhas();
		if(!list.isEmpty()) {
			attributes.addFlashAttribute("mensagem", "Não é possível deleta-la");
			return "redirect:/pessoas";
		}
		else
		repository.delete(pessoa);
		return "redirect:/pessoas";
	}
	
	@RequestMapping("/deletarSenha")
	public String deletarSenha(Long id) {
		Senha senha = senhaService.findById(id);
		repository2.delete(senha);
		
		Pessoa pessoa = senha.getPessoa();
		Long idLong = pessoa.getId();
		String idString = "" + idLong;
		
		return "redirect:/" + idString;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String detalhesPessoaPost(@PathVariable("id") Long id, @Valid Senha senha, BindingResult result,
			RedirectAttributes attributes) {

		if (senha.getLocal() == "" || senha.getConta() == "" || senha.getAcesso() == "") {
			attributes.addFlashAttribute("mensagem", "Preencha todos os campos!");
			return "redirect:/{id}";
		}
		Iterable<Senha> list = senhaService.findByLocal(senha.getLocal());
		for(Senha s : list) {
			if(senha.equals(s)) {
				attributes.addFlashAttribute("mensagem", "Senha já cadastrada!");
				return "redirect:/{id}";
			}
		}	
		senhaService.insert(id, senha);
		attributes.addFlashAttribute("mensagem", "Senha adicionada com sucesso!");
		return "redirect:/{id}";
	}
	
	

}

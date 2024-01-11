package com.pedropc.senhas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pedropc.senhas.models.Pessoa;
import com.pedropc.senhas.models.Senha;
import com.pedropc.senhas.services.SenhaService;

@Controller
public class SenhaController {

	@Autowired
	SenhaService senhaService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String incluirSenha(@PathVariable("id") Long id, @Valid Senha senha, BindingResult result,
			RedirectAttributes attributes) {

		if (senha.getLocal() == "" || senha.getConta() == "" || senha.getAcesso() == "") {
			attributes.addFlashAttribute("mensagem", "Preencha todos os campos!");
			return "redirect:/{id}";
		}
		Iterable<Senha> list = senhaService.findByLocal(senha.getLocal());
		for (Senha s : list) {
			if (senha.equals(s)) {
				attributes.addFlashAttribute("mensagem", "Senha já cadastrada!");
				return "redirect:/{id}";
			}
		}
		senhaService.insert(id, senha);
		attributes.addFlashAttribute("mensagem", "Senha adicionada com sucesso!");
		return "redirect:/{id}";
	}
	
	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public ModelAndView updateFormSenha(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("pass");

		Senha senha = senhaService.findById(id);
		senhaService.desembaralhaSenha(senha);
		mv.addObject("senha", senha);

		return mv;
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.POST)
	public String atualizarSenha(@PathVariable("id") Long id, @Valid Senha senha, BindingResult result,
			RedirectAttributes attributes) {

		Senha senhaatual = senhaService.findById(id);

		Pessoa pessoa = senhaatual.getPessoa();
		Long idLong = pessoa.getId();
		String idString = "" + idLong;

		if (senha.getAcesso() == "") {
			attributes.addFlashAttribute("mensagem", "Preencha o campo!");
			return "redirect:/{id}/editar";
		}

		senhaService.update(id, senha);
		attributes.addFlashAttribute("mensagem", "Senha Atualizada");
		return "redirect:/{id}/editar";

	}
	
	@RequestMapping("/{id}/deletarSenha")
	public String deletarSenha(@PathVariable("id") Long id) {
		Senha senha = senhaService.findById(id);
		senhaService.delete(senha);

		Pessoa pessoa = senha.getPessoa();
		Long idLong = pessoa.getId();
		String idString = "" + idLong;

		return "redirect:/" + idString;
	}

}

package com.pedropc.senhas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pedropc.senhas.models.Pessoa;
import com.pedropc.senhas.services.PessoaService;

@Controller
public class IndexController {
	
	@Autowired
	PessoaService pessoaService;

	
	@RequestMapping("/")
	public ModelAndView listaPessoas() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Pessoa> pessoas = pessoaService.findAll();
		mv.addObject("pessoas", pessoas);
		return mv;
	}
}

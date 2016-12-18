package com.pinheiro.esdras.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pinheiro.esdras.model.StatusTitulo;
import com.pinheiro.esdras.model.Titulo;
import com.pinheiro.esdras.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static String CADASTRO_TITULO = "CadastroTitulo";
	private static String PESQUISA_TITULO = "PesquisaTitulos";
	
	private static String ATRIBUTO_MENSAGEM = "mensagem";
	private static String ATRIBUTO_TITULOS = "titulos";
	
	private static String MSG_SALVO_CONSUCESSO = "Título salvo com sucesso!!";
	
	@Autowired
	private Titulos titulos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(CADASTRO_TITULO);
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors erros, RedirectAttributes attributes){
		if(erros.hasErrors()){
			return CADASTRO_TITULO;
		}
		titulos.save(titulo);
		attributes.addFlashAttribute(ATRIBUTO_MENSAGEM, MSG_SALVO_CONSUCESSO);
		return "redirect:/titulos/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisar(){
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView(PESQUISA_TITULO);
		mv.addObject(ATRIBUTO_TITULOS, todosTitulos);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo){
		ModelAndView mv = new ModelAndView(CADASTRO_TITULO);
		mv.addObject(titulo);
		return mv;
	}
	
	@ModelAttribute("statusTitulo")
	public List<StatusTitulo> statusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
}

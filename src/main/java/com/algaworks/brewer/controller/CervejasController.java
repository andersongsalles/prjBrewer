package com.algaworks.brewer.controller;

import java.util.Optional;

import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Origem;
import com.algaworks.brewer.model.Sabor;
import com.algaworks.brewer.repository.Cervejas;
import com.algaworks.brewer.repository.Estilos;

@Controller
public class CervejasController {
	
	private static final Logger logger = LoggerFactory.getLogger(CervejasController.class);

	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private Estilos estilos;
	
	@RequestMapping("/cervejas/novo")
	public ModelAndView novo(Cerveja cerveja) {
		
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
//		if (result.hasErrors()) {
//			return novo(cerveja);
//		}
		
		// Salvar no banco de dados...
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
		System.out.println(">>> sku: " + cerveja.getSku());
		System.out.println(">>> sabor:" + cerveja.getSabor());
		System.out.println(">>> Origem:" + cerveja.getOrigem());
		System.out.println(">>> Nome:" + cerveja.getNome());
		System.out.println(">>> Descricao:" + cerveja.getDescricao());		
		System.out.println(">>> Teor alcoolico:" + cerveja.getTeorAlcoolico());
		System.out.println(">>> Valor:" + cerveja.getValor());
		System.out.println(">>> Comissao:" + cerveja.getComissao());
		System.out.println(">>> Estoque:" + cerveja.getQuantidadeEstoque());
		return new ModelAndView("redirect:/cervejas/novo");
	}
	
	
}

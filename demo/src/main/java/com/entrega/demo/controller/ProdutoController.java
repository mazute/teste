package com.entrega.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entrega.demo.model.Cidade;
import com.entrega.demo.model.Produto;
import com.entrega.demo.repository.MedidaRepository;
import com.entrega.demo.repository.ProdutosRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutosRepository repositorioProduto;
	
	@Autowired
	private MedidaRepository repositorioMedida;
	
	@GetMapping("/add")
	public ModelAndView add(Produto produto) {
		ModelAndView mv = new ModelAndView("addProduto");
		mv.addObject("produto", produto);
		mv.addObject("medidas", repositorioMedida.findAll());
		return mv;
	}
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("produto");
		mv.addObject("produtos", repositorioProduto.findAll());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Produto produto) {
		repositorioProduto.saveAndFlush(produto);
		return listar();
	}

}

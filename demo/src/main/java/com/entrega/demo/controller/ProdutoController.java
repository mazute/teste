package com.entrega.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entrega.demo.model.Produto;
import com.entrega.demo.repository.ControllerInterface;
import com.entrega.demo.repository.MedidaRepository;
import com.entrega.demo.repository.ProdutosRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController implements ControllerInterface<Produto>{
	
	@Autowired
	private ProdutosRepository repositorioProduto;
	
	@Autowired
	private MedidaRepository repositorioMedida;
	
	@GetMapping("/add")
	@Override
	public ModelAndView add(Produto produto) {
		ModelAndView mv = new ModelAndView("addProduto");
		mv.addObject("produto", produto);
		mv.addObject("medidas", repositorioMedida.findAll());
		return mv;
	}
	
	@GetMapping("/listar")
	@Override
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("produto");
		mv.addObject("produtos", repositorioProduto.findN());
		return mv;
	}
	
	@PostMapping("/salvar")
	@Override
	public ModelAndView salvar(@Valid Produto produto, BindingResult result) {
		if(result.hasErrors()) {
			return add(produto);
		}
		repositorioProduto.saveAndFlush(produto);
		return listar();
	}
	@GetMapping("/editar/{id}")
	@Override
	public ModelAndView editar(long id) {
		Optional<Produto> op = repositorioProduto.findById(id);
		Produto produto = op.get();
		return add(produto);
	}
	@GetMapping("/remover/{id}")
	@Override
	public ModelAndView remover(long id) {
		Optional<Produto> op = repositorioProduto.findById(id);
		Produto produto = op.get();
		repositorioProduto.delete(produto);
		return listar();
	}
	
	@PostMapping("/buscarProduto")
	@Override
	public ModelAndView buscarPorNome(String nome) {
		ModelAndView mv = new ModelAndView("/produto");
		mv.addObject("produtos", repositorioProduto.buscarPorNome(nome));
		return mv;
	}

}

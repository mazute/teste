package com.entrega.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entrega.demo.model.ItensPedido;
import com.entrega.demo.model.Pedido;
import com.entrega.demo.repository.ClienteRepository;
import com.entrega.demo.repository.FuncionarioRepository;
import com.entrega.demo.repository.ProdutosRepository;

@Controller
@RequestMapping("/pedido")
public class ControllerPedido {
	
	@Autowired
	public FuncionarioRepository repositorioFuncionario;
	
	@Autowired
	private ClienteRepository repositorioCliente;
	
	@Autowired
	private ProdutosRepository repositorioProduto;
	
	@GetMapping("/add")
	public ModelAndView add(Pedido pedido, ItensPedido itens) {
		ModelAndView mv = new ModelAndView("pedido");
		mv.addObject("pedido", pedido);
		mv.addObject("itens", itens);
		mv.addObject("clientes", repositorioCliente.findAll());
		mv.addObject("produtos", repositorioProduto.findAll());
		mv.addObject("produtos", repositorioProduto.findAll());
		return mv;
		
	}
	

}

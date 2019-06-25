package com.entrega.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entrega.demo.model.Cliente;
import com.entrega.demo.repository.CidadeRepository;
import com.entrega.demo.repository.ClienteRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repositorioCliente;
	
	@Autowired
	private CidadeRepository repositorioCidade;
	
	@GetMapping("/add")
	public ModelAndView add(Cliente cliente) {
		ModelAndView mv = new ModelAndView("addCliente");
		mv.addObject("cidades", repositorioCidade.findAll());
		mv.addObject("cliente", cliente);
		return mv;
	}
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("cliente");
		mv.addObject("clientes", repositorioCliente.findAll());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(Cliente cliente) {
		repositorioCliente.saveAndFlush(cliente);
		return listar();
	}
	
	@GetMapping("alterar/{id}")
	public ModelAndView editar(@PathVariable ("id") long id) {
		Optional<Cliente> op = repositorioCliente.findById(id);
		Cliente cliente = op.get();
		return add(cliente);
	}
	
	@GetMapping("remover/{id}")
	public ModelAndView remover (@PathVariable ("id") long id) {
		Optional<Cliente> op = repositorioCliente.findById(id);
		Cliente cliente = op.get();
		repositorioCliente.delete(cliente);
		return listar();
	}
	

}

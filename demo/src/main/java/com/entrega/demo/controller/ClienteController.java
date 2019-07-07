package com.entrega.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entrega.demo.model.Cliente;
import com.entrega.demo.repository.CidadeRepository;
import com.entrega.demo.repository.ClienteRepository;
import com.entrega.demo.repository.ControllerInterface;

@Controller
@RequestMapping("/cliente")
public class ClienteController implements ControllerInterface<Cliente>{
	
	@Autowired
	private ClienteRepository repositorioCliente;
	
	@Autowired
	private CidadeRepository repositorioCidade;
	
	@GetMapping("/add")
	@Override
	public ModelAndView add(Cliente cliente) {
		ModelAndView mv = new ModelAndView("addCliente");
		mv.addObject("cidades", repositorioCidade.findN());
		mv.addObject("cliente", cliente);
		return mv;
	}
	
	@GetMapping("/listar")
	@Override
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("cliente");
		mv.addObject("clientes", repositorioCliente.findN());
		return mv;
	}
	
	@PostMapping("/buscarCliente")
	@Override
	public ModelAndView buscarPorNome(String nome) {
		ModelAndView mv = new ModelAndView("cliente");
		mv.addObject("clientes", repositorioCliente.buscarPorNome(nome));
		return mv;
	}
	
	@PostMapping("/salvar")
	@Override
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result) {
		if(result.hasErrors()) {
			return add(cliente);
		}
		repositorioCliente.saveAndFlush(cliente);
		return listar();
	}
	
	@GetMapping("alterar/{id}")
	@Override
	public ModelAndView editar(@PathVariable ("id") long id) {
		Optional<Cliente> op = repositorioCliente.findById(id);
		Cliente cliente = op.get();
		return add(cliente);
	}
	
	@GetMapping("remover/{id}")
	@Override
	public ModelAndView remover (@PathVariable ("id") long id) {
		Optional<Cliente> op = repositorioCliente.findById(id);
		Cliente cliente = op.get();
		repositorioCliente.delete(cliente);
		return listar();
	}
	

}

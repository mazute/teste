package com.entrega.demo.controller;

import java.util.List;
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

import com.entrega.demo.model.Estado;
import com.entrega.demo.repository.ControllerInterface;
import com.entrega.demo.repository.EstadoRepository;

@Controller
@RequestMapping("/estado")
public class ControllerEstado implements ControllerInterface<Estado>{
	
	@Autowired
	private EstadoRepository repositorioEstado;

	
	@GetMapping("/listar")
	@Override
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("estado");
		mv.addObject("estados", repositorioEstado.findAll());
		return mv;
	}
	
	@GetMapping("/add")
	@Override
	public ModelAndView add(Estado estado) {
		ModelAndView mv = new ModelAndView("/addEstado");
		mv.addObject("estado", estado);
		return mv;
	}
	
	@PostMapping("/buscarEstado")
	@Override
	public ModelAndView buscarPorNome(String nome) {
		ModelAndView mv = new ModelAndView("estado");
		List<Estado> est = repositorioEstado.buscarPorNome(nome);
		mv.addObject("estados", est);
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	@Override
	public ModelAndView editar(@PathVariable ("id") long id) {
		Optional<Estado> est = repositorioEstado.findById(id);
		Estado estado = est.get();
		return add(estado);
	}
	
	@GetMapping("/remover/{id}")
	@Override
	public ModelAndView remover(@PathVariable ("id") long id) {
		Optional<Estado> est = repositorioEstado.findById(id);
		Estado estado = est.get();
		repositorioEstado.delete(estado);
		return listar();
	}
	
	@PostMapping("/salvar")
	@Override
	public ModelAndView salvar(@Valid Estado estado, BindingResult result) {
		if(result.hasErrors()) {
			return add(estado);
		}
		repositorioEstado.saveAndFlush(estado);
		return listar();
	}

	
}

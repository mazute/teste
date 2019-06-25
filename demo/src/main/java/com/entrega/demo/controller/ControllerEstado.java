package com.entrega.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entrega.demo.model.Estado;
import com.entrega.demo.repository.EstadoRepository;

@Controller
public class ControllerEstado {
	
	@Autowired
	private EstadoRepository repositorioEstado;

	@GetMapping("/estado")
	public ModelAndView listarEstado() {
		ModelAndView mv = new ModelAndView("estado");
		mv.addObject("estados", repositorioEstado.findAll());
		return mv;
	}
	
	@GetMapping("/estados")
	public ModelAndView add(Estado estado) {
		ModelAndView mv = new ModelAndView("/estados");
		mv.addObject("estado", estado);
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView alterar(@PathVariable ("id") long id) {
		Optional<Estado> est = repositorioEstado.findById(id);
		Estado estado = est.get();
		return add(estado);
	}
	
	@GetMapping("/delete/{id}")
	public String deletar(@PathVariable ("id") long id) {
		Optional<Estado> est = repositorioEstado.findById(id);
		Estado estado = est.get();
		repositorioEstado.delete(estado);
		return "redirect:/estado";
	}
	
	@PostMapping("/save")
	public ModelAndView save (@Valid Estado estado, BindingResult result) {
		if(result.hasErrors()) {
			return add(estado);
		}
		repositorioEstado.saveAndFlush(estado);
		return listarEstado();
	}
}

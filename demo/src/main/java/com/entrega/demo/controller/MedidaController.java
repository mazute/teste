package com.entrega.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entrega.demo.model.Medida;
import com.entrega.demo.repository.MedidaRepository;

@Controller
@RequestMapping("/medida")
public class MedidaController {
	
	@Autowired
	public MedidaRepository repositorioMedida;
	
	@GetMapping("/add")
	public ModelAndView add(Medida medida) {
		ModelAndView mv = new ModelAndView("/addMedida");
		mv.addObject("medida", medida);
		return mv;
	}
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/medida");
		mv.addObject("medidas", repositorioMedida.findAll());
		return mv;
	}
	
	@PostMapping("/buscarMedida")
	public ModelAndView buscarMedida(String nome) {
		ModelAndView mv = new ModelAndView("/medida");
		mv.addObject("medidas", repositorioMedida.buscarPorNome(nome));
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable ("id") long id) {
		Optional<Medida> op = repositorioMedida.findById(id);
		Medida medida = op.get();
		return add(medida);
	}
	
	@GetMapping("remover/{id}")
	public ModelAndView remover(@PathVariable ("id") long id) {
		Optional<Medida> op = repositorioMedida.findById(id);
		Medida medida = op.get();
		repositorioMedida.delete(medida);
		return listar();
	}
	
	@PostMapping("salvar")
	public ModelAndView salvar(@Valid Medida medida) {
		repositorioMedida.saveAndFlush(medida);
		return listar();
	}

}

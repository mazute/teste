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

import com.entrega.demo.model.Cidade;
import com.entrega.demo.repository.CidadeRepository;
import com.entrega.demo.repository.EstadoRepository;

@Controller
public class ControllerCidade {
	
	@Autowired
	private EstadoRepository repositorioEstado;
	
	@Autowired
	private CidadeRepository repositorioCidade;
	
	
	@GetMapping("add/cidade")
	public ModelAndView cidadeAdd(Cidade cidade) {
		ModelAndView mv = new ModelAndView("/addcidade");
		mv.addObject("cidade", cidade);
		mv.addObject("estados", repositorioEstado.findAll());
		return mv;
	}
	
	@GetMapping("/cidade")
	public ModelAndView listarCid() {
		ModelAndView mv = new ModelAndView("cidade");
		mv.addObject("cidades", repositorioCidade.findAll());
		return mv;
	}
	
	@GetMapping("/remover/{id}")
	public String remover(@PathVariable("id") long id) {
		Optional<Cidade> cid = repositorioCidade.findById(id);
		Cidade e = cid.get();
		repositorioCidade.delete(e);
		return "redirect:/cidade";
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") long id) {
		Optional<Cidade> cid = repositorioCidade.findById(id);
		Cidade cidade = cid.get();
		return cidadeAdd(cidade);
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result) {
		if(result.hasErrors()) {
			return cidadeAdd(cidade);
		}
		repositorioCidade.saveAndFlush(cidade);
		return listarCid();
	}
	
	

}

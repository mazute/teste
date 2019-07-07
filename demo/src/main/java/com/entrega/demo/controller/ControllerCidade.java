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

import com.entrega.demo.model.Cidade;
import com.entrega.demo.repository.CidadeRepository;
import com.entrega.demo.repository.ControllerInterface;
import com.entrega.demo.repository.EstadoRepository;

@Controller
@RequestMapping("/cidade")
public class ControllerCidade implements ControllerInterface<Cidade>{
	
	@Autowired
	private EstadoRepository repositorioEstado;
	
	@Autowired
	private CidadeRepository repositorioCidade;
	
	
	@GetMapping("/add")
	@Override
	public ModelAndView add(Cidade cidade) {
		ModelAndView mv = new ModelAndView("/addcidade");
		mv.addObject("cidade", cidade);
		mv.addObject("estados", repositorioEstado.findAll());
		return mv;
	}
	
	@GetMapping("/listar")
	@Override
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("cidade");
		mv.addObject("cidades", repositorioCidade.findN());
		return mv;
	}
	
	@GetMapping("/remover/{id}")
	@Override
	public ModelAndView remover(@PathVariable("id") long id) {
		Optional<Cidade> cid = repositorioCidade.findById(id);
		Cidade e = cid.get();
		repositorioCidade.delete(e);
		return listar();
	}
	
	@GetMapping("/editar/{id}")
	@Override
	public ModelAndView editar(@PathVariable("id") long id) {
		Optional<Cidade> cid = repositorioCidade.findById(id);
		Cidade cidade = cid.get();
		return add(cidade);
	}
	
	@PostMapping("/salvar")
	@Override
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result) {
		if(result.hasErrors()) {
			return add(cidade);
		}
		repositorioCidade.saveAndFlush(cidade);
		return listar();
	}

	
	@PostMapping("/buscarCidade")
	@Override
	public ModelAndView buscarPorNome(String nome) {
		ModelAndView mv = new ModelAndView("/cidade");
		mv.addObject("cidades", repositorioCidade.buscarPorNome(nome));
		return mv;
	}
	
	

}

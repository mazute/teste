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

import com.entrega.demo.model.Funcionario;
import com.entrega.demo.repository.CidadeRepository;
import com.entrega.demo.repository.ControllerInterface;
import com.entrega.demo.repository.FuncionarioRepository;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController implements ControllerInterface<Funcionario>{
	
	@Autowired
	public CidadeRepository repositorioCidade;
	
	@Autowired
	public FuncionarioRepository repositorioFuncionario;
	
	@GetMapping("/add")
	@Override
	public ModelAndView add(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("/addFuncionario");
		mv.addObject("funcionario", funcionario);
		mv.addObject("cidades", repositorioCidade.findN());
		return mv;
	}

	@GetMapping("/listar")
	@Override
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("funcionario");
		mv.addObject("funcionarios", repositorioFuncionario.findN());
		return mv;
	}

	@PostMapping("/salvar")
	@Override
	public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result) {
		if(result.hasErrors())
			return add(funcionario);
		repositorioFuncionario.saveAndFlush(funcionario);
		return listar();
	}
	
	@GetMapping("/editar/{id}")
	@Override
	public ModelAndView editar(@PathVariable ("id") long id) {
		Optional<Funcionario> op = repositorioFuncionario.findById(id);
		Funcionario funcionario = op.get();
		return add(funcionario);
	}

	@GetMapping("/remover/{id}")
	@Override
	public ModelAndView remover(@PathVariable ("id") long id) {
		Optional<Funcionario> op = repositorioFuncionario.findById(id);
		Funcionario funcionario = op.get();
		repositorioFuncionario.delete(funcionario);
		return listar();
	}

	@PostMapping("/buscarFuncionario")
	@Override
	public ModelAndView buscarPorNome(String nome) {
		ModelAndView mv = new ModelAndView("/funcionario");
		mv.addObject("funcionario", repositorioFuncionario.buscarPorNome(nome));
		return mv;
	}

}

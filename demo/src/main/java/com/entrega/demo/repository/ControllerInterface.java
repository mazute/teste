package com.entrega.demo.repository;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


public interface ControllerInterface<T> {
	
	ModelAndView add(T entidade);
	ModelAndView listar();
	ModelAndView salvar(@Valid T entidade, BindingResult result);
	ModelAndView editar(@PathVariable ("id") long id);
	ModelAndView remover(@PathVariable ("id") long id);
	ModelAndView buscarPorNome(String nome);
}

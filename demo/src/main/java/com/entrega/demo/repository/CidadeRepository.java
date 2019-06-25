package com.entrega.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entrega.demo.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}

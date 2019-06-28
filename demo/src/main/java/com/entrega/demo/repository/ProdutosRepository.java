package com.entrega.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entrega.demo.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long>{

}

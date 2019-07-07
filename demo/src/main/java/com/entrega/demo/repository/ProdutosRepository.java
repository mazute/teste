package com.entrega.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entrega.demo.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long>{
	
	@Query("Select p from Produto p where p.nome like %?1% order by p.nome")
	List<Produto> buscarPorNome(String nome);
	
	@Query("select p from Produto p join fetch p.medida order by p.nome")
	List<Produto> findN();

}

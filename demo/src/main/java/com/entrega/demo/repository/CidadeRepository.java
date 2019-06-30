package com.entrega.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entrega.demo.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	@Query("SELECT c FROM Cidade c WHERE c.nome LIKE %?1% order by c.nome")
	List<Cidade> buscarPorNome(String nome);

}

package com.entrega.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entrega.demo.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
	@Query("Select e from Estado e where e.nome like %?1%")
	List<Estado> buscarPorNome(String nome);

}

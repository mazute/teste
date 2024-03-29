package com.entrega.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entrega.demo.model.Medida;

public interface MedidaRepository extends JpaRepository<Medida, Long>{
	
	@Query("select m from Medida m where m.nome like %?1%")
	List<Medida> buscarPorNome(String nome);

}

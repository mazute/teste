package com.entrega.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entrega.demo.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome LIKE %?1% ORDER BY f.nome")
	List<Funcionario> buscarPorNome(String nome);

}

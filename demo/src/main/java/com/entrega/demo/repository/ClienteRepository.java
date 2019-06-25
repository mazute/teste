package com.entrega.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entrega.demo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}

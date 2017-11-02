package com.servieCliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servieCliente.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}

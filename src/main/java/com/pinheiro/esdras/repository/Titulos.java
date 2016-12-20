package com.pinheiro.esdras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pinheiro.esdras.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Long>{
	
	public List<Titulo> findByDescricaoContaining(String descricao);

}

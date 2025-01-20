package com.thiago.barroso.empresaapi.web.dao;

import java.time.LocalDate;
import java.util.List;

import com.thiago.barroso.empresaapi.web.domain.Funcionario;

public interface FuncionarioDao {
	
	void save (Funcionario funcionario);
	
	void update (Funcionario funcionaro);
	
	void delete (Long id);
	
	Funcionario findById(Long id);
	
	List<Funcionario> findAll();
	
	List<Funcionario> findByNome(String nome);

	List<Funcionario> finByCargoId(Long id);

	List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida);

	List<Funcionario> findByDataEntrada(LocalDate entrada);

	List<Funcionario> findByDataSaida(LocalDate saida);
	
}

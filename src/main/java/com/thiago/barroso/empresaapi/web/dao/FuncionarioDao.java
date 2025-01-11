package com.thiago.barroso.empresaapi.web.dao;

import java.util.List;

import com.thiago.barroso.empresaapi.web.domain.Funcionario;

public interface FuncionarioDao {
	
	void save (Funcionario funcionario);
	
	void update (Funcionario funcionaro);
	
	void delete (Long id);
	
	Funcionario findById(Long id);
	
	List<Funcionario> findAll();
	
}

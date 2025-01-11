package com.thiago.barroso.empresaapi.web.dao;

import java.util.List;

import com.thiago.barroso.empresaapi.web.domain.Departamento;

public interface DepartamentoDao {
	
	void save(Departamento departamento);
	
	void update (Departamento departamento);
	
	void delete (Long id);
	
	Departamento findById(Long id);
	
	List<Departamento> findAll();
	
	
}

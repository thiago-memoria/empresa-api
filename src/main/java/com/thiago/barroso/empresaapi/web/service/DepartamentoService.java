package com.thiago.barroso.empresaapi.web.service;

import java.util.List;

import com.thiago.barroso.empresaapi.web.domain.Departamento;

public interface DepartamentoService {
	
	void salvar (Departamento departamento);
	
	void editar (Departamento departamento);
	
	void excluir (Long id);
	
	Departamento buscarPorId(Long id);
	
	List<Departamento> buscarTodos();

	boolean departamentoTemCargos(Long id);
	
}

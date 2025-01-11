package com.thiago.barroso.empresaapi.web.service;

import java.util.List;

import com.thiago.barroso.empresaapi.web.domain.Funcionario;

public interface FuncionarioService {
	
	void salvar(Funcionario funcionario);
	
	void editar(Funcionario funcionario);
	
	void excluir(Long id);
	
	Funcionario buscarPorId(Long id);
	
	List<Funcionario> buscarTodos();
	
}

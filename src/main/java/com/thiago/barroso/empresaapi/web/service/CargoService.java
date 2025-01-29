package com.thiago.barroso.empresaapi.web.service;

import java.util.List;

import com.thiago.barroso.empresaapi.web.domain.Cargo;
import com.thiago.barroso.empresaapi.web.util.PaginacaoUtil;

public interface CargoService {
	
	void salvar(Cargo cargo);
	
	void editar(Cargo cargo);
	
	void excluir(Long id);
	
	Cargo buscarPorId(Long id);
	
	List<Cargo> buscarTodos();

	boolean cargoTemFuncionarios(Long id);
	
	PaginacaoUtil<Cargo> buscarPorPagina(int pagina, String direcao);
}

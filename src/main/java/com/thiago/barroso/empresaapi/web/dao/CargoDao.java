package com.thiago.barroso.empresaapi.web.dao;

import java.util.List;

import com.thiago.barroso.empresaapi.web.domain.Cargo;
import com.thiago.barroso.empresaapi.web.util.PaginacaoUtil;

public interface CargoDao {

	void save (Cargo cargo);
	
	void update (Cargo cargo);
	
	void delete (Long id);
	
	Cargo findById(Long id);
	
	List<Cargo> findAll();
	
	PaginacaoUtil<Cargo> buscaPaginada(int pagina, String descricao, String coluna);
	
}

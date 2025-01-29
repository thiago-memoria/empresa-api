package com.thiago.barroso.empresaapi.web.dao;

import java.util.List;

import org.hibernate.query.sqm.UnknownEntityException;
import org.springframework.stereotype.Repository;

import com.thiago.barroso.empresaapi.web.domain.Cargo;
import com.thiago.barroso.empresaapi.web.util.PaginacaoUtil;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {
	
	public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao){
		if (!direcao.equals("asc") && !direcao.equals("desc")) {
		    direcao = "asc";
		}
		String queryStr = "select c from Cargo c order by c.nome " + direcao;
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;
		List<Cargo> cargos = null;
		try {
		    cargos = getEntityManager()
		        .createQuery(queryStr, Cargo.class)
		        .setFirstResult(inicio)
		        .setMaxResults(tamanho)
		        .getResultList();
		} catch (EntityNotFoundException e) {
		    throw new RuntimeException("Entidade Cargo não encontrada", e);
		} catch (UnknownEntityException e) {
		    throw new RuntimeException("Não foi possível resolver a entidade Cargo", e);
		} catch (Exception e) {
		    e.printStackTrace();
		    throw new RuntimeException("Erro ao buscar cargos", e);
		}
		
		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho + 1)) / tamanho;
		
		return new PaginacaoUtil<Cargo>(tamanho, pagina, totalDePaginas, cargos, direcao);
	}
	
	public long count() {
		return getEntityManager()
				.createQuery("select count(*) from Cargo", Long.class)
				.getSingleResult();	
	}
	
}

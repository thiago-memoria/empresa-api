package com.thiago.barroso.empresaapi.web.util;

import java.util.List;

public class PaginacaoUtil<T> {
	
	private int tamanho;
	private int pagina;
	private Long totalDePaginas;
	private List<T> registros;
	private String direcao;
	private String coluna;
	
	public PaginacaoUtil(int tamanho, int pagina, Long totalDePaginas, List<T> registros, String direcao, String coluna) {
		super();
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalDePaginas = totalDePaginas;
		this.registros = registros;
		this.direcao = direcao;
		this.coluna = coluna;
	}

	public int getTamanho() {
		return tamanho;
	}

	public int getPagina() {
		return pagina;
	}

	public String getDirecao() {
		return direcao;
	}

	public String getColuna() {
		return coluna;
	}

	public Long getTotalDePaginas() {
		return totalDePaginas;
	}

	public List<T> getRegistros() {
		return registros;
	}
}

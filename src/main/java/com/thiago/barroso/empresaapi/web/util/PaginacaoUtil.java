package com.thiago.barroso.empresaapi.web.util;

import java.util.List;

public class PaginacaoUtil<T> {
	
	private int tamanho;
	private int pagina;
	private Long totalDePaginas;
	private List<T> registros;
	private String direcao;
	
	public PaginacaoUtil(int tamanho, int pagina, Long totalDePaginas, List<T> registros, String direcao) {
		super();
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalDePaginas = totalDePaginas;
		this.registros = registros;
		this.direcao = direcao;
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

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}

	public Long getTotalDePaginas() {
		return totalDePaginas;
	}

	public List<T> getRegistros() {
		return registros;
	}
	
}

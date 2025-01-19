package com.thiago.barroso.empresaapi.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.thiago.barroso.empresaapi.web.domain.Departamento;
import com.thiago.barroso.empresaapi.web.service.DepartamentoService;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento>{
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@Override
	public Departamento convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return departamentoService.buscarPorId(id);
	}

}

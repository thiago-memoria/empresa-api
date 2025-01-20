package com.thiago.barroso.empresaapi.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.thiago.barroso.empresaapi.web.domain.Cargo;
import com.thiago.barroso.empresaapi.web.service.CargoService;

@Component
public class StringToCargoConverter implements Converter<String, Cargo>{

	@Autowired
	CargoService cargoService;
	
	@Override
	public Cargo convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		
		Long id = Long.valueOf(text);
		return cargoService.buscarPorId(id);
	}
}

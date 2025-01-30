package com.thiago.barroso.empresaapi.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thiago.barroso.empresaapi.web.domain.Cargo;
import com.thiago.barroso.empresaapi.web.domain.Departamento;
import com.thiago.barroso.empresaapi.web.service.CargoService;
import com.thiago.barroso.empresaapi.web.service.DepartamentoService;
import com.thiago.barroso.empresaapi.web.util.PaginacaoUtil;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	CargoService cargoService;
	
	@Autowired
	DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return"/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page,
	        @RequestParam("dir") Optional<String> dir, @RequestParam("col") Optional<String> col) {

	    int paginaAtual = page.orElse(1);
	    String ordem = dir.orElse("asc");
	    String coluna= col.orElse("nome");
	    System.out.println("Página: " + paginaAtual + ", Ordem: " + ordem); // Adicionando log

	    try {
	        PaginacaoUtil<Cargo> pageCargo = cargoService.buscarPorPagina(paginaAtual, ordem, coluna);
	        model.addAttribute("pageCargo", pageCargo);
	    } catch (Exception e) {
	        e.printStackTrace();  // Logar o erro
	    }
	    return "cargo/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "/cargo/cadastro";
		}
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap map) {
		map.addAttribute("cargo", cargoService.buscarPorId(id));
		return "/cargo/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "/cargo/cadastro";
		}
		
		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "Registro Atualizado com sucesso");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if(cargoService.cargoTemFuncionarios(id)) {
			attr.addFlashAttribute("fail", "Cargo não excluido. Tem funcionario(s) vinculado(s).");
		}else {
			cargoService.excluir(id);
			attr.addFlashAttribute("success", "Cargo excluído com sucesso.");
		}
		return "redirect:/cargos/listar";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos(){
		return departamentoService.buscarTodos();
	}
}

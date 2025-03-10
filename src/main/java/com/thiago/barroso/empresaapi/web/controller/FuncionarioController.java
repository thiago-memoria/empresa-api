package com.thiago.barroso.empresaapi.web.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thiago.barroso.empresaapi.web.domain.Cargo;
import com.thiago.barroso.empresaapi.web.domain.Funcionario;
import com.thiago.barroso.empresaapi.web.domain.UF;
import com.thiago.barroso.empresaapi.web.service.CargoService;
import com.thiago.barroso.empresaapi.web.service.FuncionarioService;
import com.thiago.barroso.empresaapi.web.validator.FuncionarioValidator;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private CargoService cargoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return"/funcionario/cadastro";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new FuncionarioValidator());
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarTodos());
		return"/funcionario/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "/funcionario/cadastro";
		}
		
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success", "Funcionario Inserido com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.buscarPorId(id));
		return "funcionario/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "/funcionario/cadastro";
		}
		
		funcionarioService.editar(funcionario);
		attr.addFlashAttribute("success", "Funcionário editado com sucesso");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		funcionarioService.excluir(id);
		attr.addFlashAttribute("success", "Funcionário removido com sucesso");
		return "redirect:/funcionarios/listar";
	}
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarPorNome(nome));
		return "/funcionario/lista";
	}
	
	@GetMapping("/buscar/cargo")
	public String getPorCargo(@RequestParam("id") Long id, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarPorCargo(id));
		return "/funcionario/lista";
	}
	
	@GetMapping("/buscar/data")
	public String getPorDatas(@RequestParam(name = "entrada", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate entrada,
			@RequestParam(name = "saida", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida, ModelMap model) {
		
		model.addAttribute("funcionarios", funcionarioService.buscarPorDatas(entrada,saida));
		return "/funcionario/lista";
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> getCargos(){
		return cargoService.buscarTodos();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}
}

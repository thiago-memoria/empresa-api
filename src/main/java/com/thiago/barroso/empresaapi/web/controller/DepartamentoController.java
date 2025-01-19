package com.thiago.barroso.empresaapi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thiago.barroso.empresaapi.web.domain.Departamento;
import com.thiago.barroso.empresaapi.web.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return"/departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", service.buscarTodos());
		return"/departamento/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute Departamento departamento, RedirectAttributes redirectAttributes) {
	    try {
	        service.salvar(departamento);
	        redirectAttributes.addFlashAttribute("success", "Departamento inserido com sucesso.");
	        return "redirect:/departamentos/cadastrar";  // Sucesso
	    } catch (DataIntegrityViolationException e) {
	        // Adiciona a mensagem de erro no modelo
	    	System.out.println("Erro ao salvar departamento: " + e.getMessage()); 
	    	// Adiciona a mensagem de erro para ser passada ao redirecionamento
            redirectAttributes.addFlashAttribute("erro", "Já existe um departamento com esse nome.");
	    	return "redirect:/departamentos/cadastrar";  // Retorna para a tela de cadastro com erro
	    } catch (Exception e) {
	        // Caso ocorra outro erro
	    	// Adiciona a mensagem de erro para ser passada ao redirecionamento
            redirectAttributes.addFlashAttribute("erro", "Ocorreu um erro inesperado.");
	        return "redirect:/departamentos/cadastrar";  // Retorna para a tela de cadastro com erro genérico
	    }
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", service.buscarPorId(id));
		return "/departamento/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Departamento departamento, RedirectAttributes attr) {
		service.editar(departamento);
		attr.addFlashAttribute("success", "Departamento Editado com sucesso.");
		return "redirect:/departamento/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if(service.departamentoTemCargos(id)) {
			model.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s)");
		}else {
			service.excluir(id);
			model.addAttribute("success", "Departamento excluído com sucesso.");
		}
		
		return listar(model);
	}
	
}

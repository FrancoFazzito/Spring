package com.bolsadeideas.springboot.datajpa.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.util.paginator.PageRender;
import com.bolsadeideas.springboot.datajpa.app.models.entity.Cliente;
import com.bolsadeideas.springboot.datajpa.app.models.service.IclienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	@Qualifier("clienteService")
	private IclienteService clienteService;

	@GetMapping("/listar")
	public String listar(@RequestParam(name = "page",defaultValue = "0") int page,Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes); 
		model.addAttribute("titulo", "listado de cliente");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page",pageRender);
		return "listar";
	}

//	public String listar(@RequestParam(name = "page",defaultValue = "0") int page,Model model) {
//		model.addAttribute("titulo", "listado de cliente");
//		model.addAttribute("clientes", clienteService.findAll());
//		return "listar";
//	}
	
	@GetMapping("/form")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "formulario de cliente");
		return "form";
	}

	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Long id,Model model,RedirectAttributes flash) {
		Cliente cliente = null;
		if(id>0) {
			cliente = clienteService.findOne(id);
		}else {
			flash.addFlashAttribute("error","no puede ser cero");
			return "redirect:/listar";
		}
		model.addAttribute("titulo","editar cliente");
		model.addAttribute("cliente",cliente);
		return "form";
	}
	
	@PostMapping("/form") //action es a la ruta ( /form ) y el object es el param
	public String guardar(@Valid @ModelAttribute("cliente")/*solo si se llama distinto a la clase*/ Cliente cliente, BindingResult result,Model model,RedirectAttributes flash, SessionStatus status) {
		if(result.hasErrors()) {
			//pasa de forma automatica si se llama igual
			model.addAttribute("titulo","formulario de Cliente");
			return "form";
		}
		clienteService.Save(cliente);
		status.setComplete(); //termina la sesion y borra el cliente dejandolo en null sin necesidad del id
		flash.addFlashAttribute("success","cliente creado con exito");
		return "redirect:/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id,Model model,RedirectAttributes flash) {
		Cliente cliente = null;
		if(id>0) {
			clienteService.delete(id);
		}
		flash.addFlashAttribute("success","cliente eliminado con exito");
		return "redirect:/listar";
	}

}

package com.bolsadeideas.springboot.datajpa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bolsadeideas.springboot.datajpa.app.entity.Usuario;
import com.bolsadeideas.springboot.datajpa.app.repository.IRolRepository;
import com.bolsadeideas.springboot.datajpa.app.repository.IUsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	IUsuarioRepository usuarioRepository;
	
	@Autowired
	IRolRepository rolRepository;
	
	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("usuario",new Usuario());
		modelo.addAttribute("usuarios",usuarioRepository.findAll());
		modelo.addAttribute("roles",rolRepository.findAll());
		return "index";
	}
	
	@PostMapping("/crearUsuario")
	public String crearUsuario(Model modelo, Usuario usuario) {
		usuarioRepository.save(usuario);
		
		modelo.addAttribute("usuario",new Usuario());
		modelo.addAttribute("usuarios",usuarioRepository.findAll());
		modelo.addAttribute("roles",rolRepository.findAll());
		return "index";
	}
	
	@GetMapping("/editarUsuario/{id}")
	public String editarUsuarioForm(Model modelo, @PathVariable(name="id") Long id) {
		Usuario usuarioParaEditar = usuarioRepository.findById(id).get();
		modelo.addAttribute("usuario",usuarioParaEditar);
		modelo.addAttribute("usuarios",usuarioRepository.findAll());
		modelo.addAttribute("roles",rolRepository.findAll());
		return "index";
	}
	
	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUsuario(Model modelo, @PathVariable(name="id") Long id) {
		Usuario usuarioParaEliminar = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuarioParaEliminar);
		modelo.addAttribute("usuario",new Usuario());
		modelo.addAttribute("usuarios",usuarioRepository.findAll());
		modelo.addAttribute("roles",rolRepository.findAll());
		return "index";
	}
}

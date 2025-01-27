package com.bolsadeideas.springboot.web.app.controllers;

import com.bolsadeideas.springboot.web.app.models.Usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
//ruta general a toda la clase
@RequestMapping("/app") //http://localhost:8080/app/
public class IndexController {
	/*cada controller debe llevar Controller en el nombre y lo marcamos como @controller (ctrl + espacio)*/
	
	//handler para cada pedido ( peticion http ) de usuario ( cada pagina web html) es decir maneja peticiones del usuario
	@RequestMapping(value = "/index", method = RequestMethod.GET) //nombre de la ruta con request mapping y su valor 
	public String index() {
		return "index";
	}
	
	//@GetMapping({"/","/index"}) asociado a varias rutas
	//@PostMapping
	//...
	
	
	@GetMapping({"/","home"}) //"" raiz igual
	public String home(Model model){
		model.addAttribute("titulo","Hola Spring framework"); //trabaja como un map de <String,Object>
		return "home"; //ruta
	}
	
	@GetMapping("/perfil")
	public String perfil(Model model) {
		Usuario user = new Usuario("franco", "fazzito");
		user.setEmail("francofazzito@gmail.com");
		model.addAttribute("titulo","info de usuario " + user.getNombre());
		model.addAttribute("usuario",user);
		model.addAttribute("nombre",user.getNombre());
		model.addAttribute("apellido",user.getApellido());
		return "perfil";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("usuarios","listado de usuarios");
		return "listar";
	}
	
//	@GetMapping("/listar")
//	public String listar(Model model) {
//		List<Usuario> users = new ArrayList<Usuario>();
//		users.add(new Usuario("katia", "perchet"));
//		users.add(new Usuario("franco", "perez"));
//		model.addAttribute("listaUsers",users);
//		return "listar";
//	}
	
	@ModelAttribute("listaUsers") //llenar desplegables
	public List<Usuario> getUsuarios(){
		List<Usuario> users = new ArrayList<Usuario>();
		users.add(new Usuario("katia", "perchet"));
		users.add(new Usuario("franco", "perez"));
		return users;
	}
	
}

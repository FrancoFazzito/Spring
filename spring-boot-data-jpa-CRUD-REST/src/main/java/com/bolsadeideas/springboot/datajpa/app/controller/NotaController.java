package com.bolsadeideas.springboot.datajpa.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.datajpa.app.entity.Nota;
import com.bolsadeideas.springboot.datajpa.app.model.NotaModelo;
import com.bolsadeideas.springboot.datajpa.app.service.NotasService;


@RestController
@RequestMapping("api/")
public class NotaController {

	@Autowired
	private NotasService servicio;

//	GET	Read
//	PUT	Update/Replace
//	PATCH	Update/Modify
//	DELETE	Delete
	
	@PutMapping("/nota") //alta
	public boolean agregarNota(@RequestBody @Valid Nota nota) {
		return servicio.alta(nota);
	}
	
	@PostMapping("/nota") //modificacion
	public boolean modificarNota(@RequestBody @Valid Nota nota) {
		return servicio.modificar(nota);
	}
	
	@DeleteMapping("/nota/{id}") //baja
	public boolean borrarNota(@PathVariable(required = true) Long id) { 
		return servicio.borrar(id);
	}
	
	@GetMapping("/nota")
	public List<NotaModelo> obtenerNotas(){
		return servicio.obtener();
	}
	
	@GetMapping("/nota-paginacion")
	public List<NotaModelo> obtenerNotas(Pageable pageable){ //(@RequestParam("page") int pageIndex, @RequestParam("size") int pageSize) resuelto en webconfig
		return servicio.obtenerPorPaginacion(pageable);
	}
	
	@GetMapping("/nota/{nombre}")
	public List<NotaModelo> obtenerNotasNombre(@PathVariable(required = true) String nombre){
		return servicio.obtenerPorNombre(nombre);
	}
	
	
}

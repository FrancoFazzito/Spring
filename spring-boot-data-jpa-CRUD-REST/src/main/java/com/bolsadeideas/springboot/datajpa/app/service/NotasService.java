package com.bolsadeideas.springboot.datajpa.app.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.datajpa.app.converter.ConverterNota;
import com.bolsadeideas.springboot.datajpa.app.entity.Nota;
import com.bolsadeideas.springboot.datajpa.app.model.NotaModelo;
import com.bolsadeideas.springboot.datajpa.app.repository.NotaRepository;


@Service
public class NotasService {

	@Autowired
	@Qualifier("repositorioNota")
	private NotaRepository repositorio;

	@Autowired
	@Qualifier("converter")
	private ConverterNota convertidor;

	private static final Log logger = LogFactory.getLog(NotasService.class);
	
	public boolean alta(Nota nota) {
		logger.info("CREANDO NOTA");
		try {
			repositorio.save(nota);
			logger.info("CREADA CON EXITO");
			return true;
		} catch (Exception e) {
			logger.info("HA OCURRIDO UN ERROR EN CREAR NOTA");
			return false;
		}
	}

	public boolean modificar(Nota nota) {
		logger.info("MODIFICANDO NOTA");
		try {
			if (nota.getId() == null && nota.getId() == 0) {
				alta(nota);
				logger.info("CREADA CON EXITO");
			} else {
				repositorio.save(nota);
				logger.info("MODIFICADA CON EXITO");
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean borrar(Long id) {
		logger.info("BORRANDO NOTA");
		try {
			repositorio.deleteById(id);
			logger.info("ELIMINADA CON EXITO");
			return true;
		} catch (Exception e) {
			logger.info("HA OCURRIDO UN ERROR EN ELIMINAR NOTA");
			return false;
		}
	}

	public List<NotaModelo> obtener() {
		logger.info("OBTENIENDO NOTA");
		return convertidor.convertir(repositorio.findAll());
	}

	public List<NotaModelo> obtenerPorNombre(String nombre) {
		logger.info("CREANDO NOTA POR NOMBRE: " + nombre);
		return convertidor.convertir(repositorio.findByNombre(nombre));
	}
	
	public List<NotaModelo> obtenerPorPaginacion(Pageable pageable){
		return convertidor.convertir(repositorio.findAll(pageable).getContent());
	}
}

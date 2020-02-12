package com.bolsadeideas.springboot.datajpa.app.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.datajpa.app.entity.Nota;
import com.bolsadeideas.springboot.datajpa.app.model.NotaModelo;

@Component("converter")
public class ConverterNota {

	public List<NotaModelo> convertir(List<Nota> notas){
		
		List<NotaModelo> notasModelo = new ArrayList<NotaModelo>();
		for (var nota : notas) {
			notasModelo.add(new NotaModelo(nota));
		}
		return notasModelo;
	}
	
}

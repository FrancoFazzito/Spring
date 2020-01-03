package com.bolsadeideas.springboot.id.app.models.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("servicioComplejo")
@Primary //establece la por defecto
public class ServicioComplejo implements IServiciable{

	@Override
	public String operacion() {
		return "operacion compleja exitosa";
	}
	
	public ServicioComplejo() {
		
	}
	
}

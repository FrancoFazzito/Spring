package com.bolsadeideas.springboot.id.app.models.service;

//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component service es semantico de component y debe tener un ctor vacio

//indicar el nombre para la inyeccion pero al haber 1 solo toma esa
@Service("servicioSimple") //lo mismo que crear con @configuracion y @bean
public class ServicioSimple implements IServiciable{

	@Override //notacion que es un comportamiento de padre
	public String operacion() {
		return "operacion simple exitosa";
	}
	
	public ServicioSimple() {
		
	}
	
}

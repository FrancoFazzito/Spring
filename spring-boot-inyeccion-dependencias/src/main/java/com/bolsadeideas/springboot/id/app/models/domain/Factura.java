package com.bolsadeideas.springboot.id.app.models.domain;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
//@RequestScope //trabaja durante la peticion
//@SessionScope //trabaja durante la sesion y se tiene que implementar serializable se usa para carros de compra y no se aplica el destructor
public class Factura implements Serializable{ // tenemos que inyectar cliente y una lista de items
 
	private static final long serialVersionUID = 1484477962609758737L;

	@Value("${descripcion.nombre}")
	public String descripcion;
	
	@Autowired
	public Cliente cliente;
	
	@Autowired
	@Qualifier("itemsFacturaOficina")
	public List<ItemFactura> items;

	@PostConstruct
	public void inicializar() {
		cliente.setNombre(cliente.getNombre().concat(" ").concat("seteado"));
	}
	
	@PreDestroy
	public void destruir() {
		System.out.println("factura destruida: ".concat(this.descripcion)); //singleton que dure lo que dure la app
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getFacturas(){
		return items;
	}
}

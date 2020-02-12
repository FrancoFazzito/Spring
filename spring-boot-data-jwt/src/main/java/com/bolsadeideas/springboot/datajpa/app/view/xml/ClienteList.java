package com.bolsadeideas.springboot.datajpa.app.view.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Cliente;

@XmlRootElement(name = "clientesList")
public class ClienteList {

	@XmlElement(name = "cliente")
	private List<Cliente> clienteElements;
	
	public ClienteList(){
		
	}
	
	public ClienteList(List<Cliente> clientes) {
		this.clienteElements = clientes;
	}

	public List<Cliente> getClientes() {
		return clienteElements;
	}

	
	
}

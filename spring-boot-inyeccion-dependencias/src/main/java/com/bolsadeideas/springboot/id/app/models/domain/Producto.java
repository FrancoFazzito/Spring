package com.bolsadeideas.springboot.id.app.models.domain;

public class Producto {

	public String descripcion;
	public double precio;
	
	public Producto(String descripcion, double precio) {
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}

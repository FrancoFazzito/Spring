package com.bolsadeideas.springboot.id.app.models.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class ItemFactura {

	private Producto producto;
	private Integer cantidad;

	@Autowired
	public ItemFactura(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public double calcularImporte() {
		return cantidad * producto.getPrecio();
	}
}

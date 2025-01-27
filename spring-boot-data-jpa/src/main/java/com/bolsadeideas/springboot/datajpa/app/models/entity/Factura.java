package com.bolsadeideas.springboot.datajpa.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="facturas")
public class Factura implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descripcion;
	private String observacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;
	
	@ManyToOne(fetch = FetchType.LAZY) //many donde estamos y one donde apunta //trae solo facturas y EAGER trae facturas y clientes
	private Cliente cliente;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL/*borra los elementos hijos si es eliminada la factura*/,orphanRemoval = true)
	@JoinColumn(name ="factura_id") //FK ya que es una relacion unidireccional
	private List<ItemFactura> items;
	//darse cuenta si es un atributo de la clase para saber la direccion
	
	public Factura(){
		this.items = new ArrayList<ItemFactura>();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void addItemFactura(ItemFactura itemFactura){
		items.add(itemFactura);
	}
	
	public Double getTotal() {
		Double total = 0.0;
		for (ItemFactura itemFactura : items) {
			total += itemFactura.calcularImporte();
		}
		return total;
	}
	
	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}
}

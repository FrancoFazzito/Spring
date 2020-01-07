package com.bolsadeideas.springboot.datajpa.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L; // se usa mucho el envio de datos y para eso se tiene que
													 // implementar serializable CLASES ENTITY

	@Id // indica la PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // marca que sea autoincremental
	private Long id;

	// @Column(name = "nombre_cliente",nullable = true /*indica si acepta nulos*/) indica el nombre en la base de datos
	@NotEmpty //validaciones
	@Size(min = 3, max = 15)
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	@Email
	private String email;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE) // indica el formato de fecha
	private Date createAt;
	// va a mapear los atributos con el nombre de la variable

	@PrePersist
	public void prePersist() {
		createAt = new Date(); //pone la fecha de hoy
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//http://localhost:8080/h2-console/login.jsp?jsessionid=406cba9dfa8a8ceafa12d928859ed971
	//jdbc:h2:mem:testdb
}

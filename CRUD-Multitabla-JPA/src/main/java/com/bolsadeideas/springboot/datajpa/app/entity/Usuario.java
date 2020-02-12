package com.bolsadeideas.springboot.datajpa.app.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nombreUsuario;
	
	@ManyToMany
	@JoinTable(name = "usuario_roles",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Rol> roles; // muchos roles 
	
	//USER -> MANY TO MANY -> ROL
	//
	//		USUARIO_ROLES
	//
	//USER -> USUARIO_ID
	//		  ROLE_ID   <- ROL
	
	@JoinColumn(name="configuracion_id",unique=true)
	@OneToOne(cascade=CascadeType.ALL)
	private Configuracion configuracion; // una configuracion que sea unica
	
	
	public Usuario() {
		this.configuracion = new Configuracion();
	}


	public Usuario(Long id, String nombreUsuario, Set<Rol> roles, Configuracion configuracion) {
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.roles = roles;
		this.configuracion = configuracion;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", roles=" + roles + ", configuracion="
				+ configuracion + "]";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public Set<Rol> getRoles() {
		return roles;
	}
	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	public Configuracion getConfiguracion() {
		return configuracion;
	}
	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}
	
}

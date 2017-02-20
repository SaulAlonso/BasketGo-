package es.sidelab.urjc;

import javax.persistence.*;

//import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

// @Component
@Entity
@SessionScope
public class Usuario {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	private boolean administrador;
	private String nombre;
	private String contraseña;
	private String nombreEquipo;
	
	public Usuario() {
		
	}
	

	public Usuario(String nombre, String contraseña) {
		this.nombre = nombre;
		this.contraseña = contraseña;
	}


	public Usuario(Boolean administrador, String nombre, String contraseña, String nombreEquipo) {
		this.administrador = administrador;
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.nombreEquipo = nombreEquipo;
	}
	
	public Boolean getAdministrador() {
		return administrador;
	}
	
	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public String getNombreEquipo() {
		return nombreEquipo;
	}
	
	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
	
}

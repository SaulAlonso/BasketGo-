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
	private String nombreId;
	private String contraseña;
	@OneToOne
	private Equipo equipo;
	
	public Usuario() {
		
	}
	

	public Usuario(String nombreId, String contraseña) {
		this.nombreId = nombreId;
		this.contraseña = contraseña;
	}


	public Usuario(Boolean administrador, String nombreId, String contraseña, Equipo equipo) {
		this.administrador = administrador;
		this.nombreId = nombreId;
		this.contraseña = contraseña;
		this.equipo = equipo;
	}
	
	public Boolean getAdministrador() {
		return administrador;
	}
	
	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}
	
	public String getNombreId() {
		return nombreId;
	}
	
	public void setNombreId(String nombreId) {
		this.nombreId = nombreId;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public Equipo getEquipo() {
		return equipo;
	}
	
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
}

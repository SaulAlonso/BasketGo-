package es.sidelab.urjc;

import java.util.List;

import javax.persistence.*;

//import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

// @Component
@Entity
@SessionScope
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String passwordHash;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	private String nombre;
	private String nombreEquipo;

	public Usuario() {

	}

	public Usuario(String nombre, String passwordHash) {
		this.nombre = nombre;
		this.passwordHash = passwordHash;
	}

	public Usuario(Boolean administrador, String nombre, String passwordHash, String nombreEquipo) {
		this.nombre = nombre;
		this.passwordHash = passwordHash;
		this.nombreEquipo = nombreEquipo;
	}


	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getpasswordHash() {
		return passwordHash;
	}

	public void setpasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

}

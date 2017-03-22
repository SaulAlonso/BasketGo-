package es.sidelab.urjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

// @Component
@Entity
@SessionScope
public class Usuario {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String elementoAsociado;
	
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private String passwordHash;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	public Usuario() {

	}
	
	public Usuario(String nombre, String password, String... roles) {
		this.nombre = nombre;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.elementoAsociado = "";
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}

	public Usuario(Boolean administrador, String nombre, String password,
			String elementoAsociado, String rol) {
		this.nombre = nombre;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.elementoAsociado = elementoAsociado;
		this.roles = new ArrayList<>();
		this.roles.add(rol);
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
	
	public String getElementoAsociado() {
		return elementoAsociado;
	}
	
	public void setElementoAsociado(String elementoAsociado) {
		this.elementoAsociado = elementoAsociado;
	}

}

package es.sidelab.urjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// @Component
@Entity
//@SessionScope
public class Usuario {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private String passwordHash;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	public Usuario() {

	}
	
	public Usuario(String name, String password, String... roles) {
		this.name = name;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}
	
	public Usuario(String name, String password) {
		this.name = name;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpasswordHash() {
		return passwordHash;
	}

	public void setpasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

}

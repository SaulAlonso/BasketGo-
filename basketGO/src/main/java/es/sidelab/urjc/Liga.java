package es.sidelab.urjc;

/*import java.util.ArrayList;

 import org.springframework.stereotype.Controller;*/

import javax.persistence.*;

@Entity
public class Liga {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	
	@OneToOne
	private Clasificacion clasificacion;

	public Liga() {
		
	}
	
	public Liga(String nombre, Clasificacion clasificacion) {
		this.nombre = nombre;
		this.clasificacion = clasificacion;
	}

	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
}

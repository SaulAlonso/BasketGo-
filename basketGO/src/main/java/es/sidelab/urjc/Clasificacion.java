package es.sidelab.urjc;

import java.util.ArrayList;
/*import java.util.Collections;
import java.util.Comparator;*/
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.*;


@Entity
public class Clasificacion {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	
	@OneToMany
	private List<Equipo> listaClasificacion;
	
	public Clasificacion() {
		
	}
	
	public Clasificacion(List<Equipo> listaClasificacion) {
		this.listaClasificacion = listaClasificacion;
	}
	
	public List<Equipo> getListaClasificacion() {
		
		Collections.sort(listaClasificacion, new Comparator<Equipo>() {
			@Override
			public int compare(Equipo arg0, Equipo arg1) {
				return new Integer(arg0.getPuntuacion()).compareTo(new Integer(arg1.getPuntuacion())) ;
			}
		});
		return listaClasificacion;
	}

	public void setListaClasificacion(ArrayList<Equipo> listaClasificacion) {
		this.listaClasificacion = listaClasificacion;
	}


	
	

}

package es.sidelab.urjc;

import java.util.List;

import javax.persistence.*;

//import org.springframework.stereotype.Controller;
@Entity
public class Equipo {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int fichaEquipo;
	private String nombreEquipo;
	private int puntuacion;
	private int numeroDerrotas;
	private int numeroVictorias;
	@OneToMany
	private List<Jugador> listaJugadores;
	

	public Equipo() {
		
	}
	
	public Equipo(String nombreEquipo, int puntuacion,
			int numeroDerrotas, int numeroVictorias, List<Jugador> listaJugadores) {
		
		this.nombreEquipo = nombreEquipo;
		this.puntuacion = puntuacion;
		this.numeroDerrotas = numeroDerrotas;
		this.numeroVictorias = numeroVictorias;
		this.listaJugadores = listaJugadores;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getNumeroDerrotas() {
		return numeroDerrotas;
	}

	public void setNumeroDerrotas(int numeroDerrotas) {
		this.numeroDerrotas = numeroDerrotas;
	}

	public int getNumeroVictorias() {
		return numeroVictorias;
	}

	public void setNumeroVictorias(int numeroVictorias) {
		this.numeroVictorias = numeroVictorias;
	}
	
	public String getNombreEquipo() {
		return nombreEquipo;
	}
	
	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
	
	public List<Jugador> getListaJugadores() {
		return listaJugadores;
	}
	
	public void setListaJugadores(List<Jugador> listaJugadores) {
		this.listaJugadores = listaJugadores;
	}

}

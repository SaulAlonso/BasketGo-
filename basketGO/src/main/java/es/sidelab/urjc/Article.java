package es.sidelab.urjc;

import java.util.List;
import java.util.LinkedList;

public class Article {
	
	private String nombreEquipo;
	private int puntuacion;
	private int numeroDerrotas;
	private int numeroVictorias;
	
	public Article(String nombreEquipo, int puntuacion,
			int numeroDerrotas, int numeroVictorias) {
	}

	public String getPuntuacion() {
		return ""+puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getNumeroDerrotas() {
		return ""+numeroDerrotas;
	}

	public void setNumeroDerrotas(int numeroDerrotas) {
		this.numeroDerrotas = numeroDerrotas;
	}

	public String getNumeroVictorias() {
		return ""+numeroVictorias;
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

}

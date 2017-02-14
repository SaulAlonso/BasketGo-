package es.sidelab.urjc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LigaController {
	

	@Autowired
	private LigaRepository liga;
	@Autowired
	private ClasificacionRepository clasificacion;
	@Autowired
	private EquipoRepository equipo;
	@Autowired
	private JugadorRepository jugador;
	
	@PostConstruct
	public void init(){
		Jugador jugador1 = new Jugador("Prueba1", 2.10, 18);
		Jugador jugador2 = new Jugador("Prueba2", 2.09, 17);
		Jugador jugador3 = new Jugador("Prueba3", 2.08, 18);
		Jugador jugador4 = new Jugador("Prueba4", 2.07, 19);
		Jugador jugador5 = new Jugador("Prueba5", 2.06, 15);
		Jugador jugador6 = new Jugador("Prueba6", 2.05, 16);
		Jugador jugador7 = new Jugador("Prueba7", 2.04, 20);
		Jugador jugador8 = new Jugador("Prueba8", 2.03, 18);
		Jugador jugador9 = new Jugador("Prueba9", 2.02, 19);
		Jugador jugador10 = new Jugador("Prueba10", 1.98, 16);
		
		jugador.save(jugador1);
		jugador.save(jugador2);
		jugador.save(jugador3);
		jugador.save(jugador4);
		jugador.save(jugador5);
		jugador.save(jugador6);
		jugador.save(jugador7);
		jugador.save(jugador8);
		jugador.save(jugador9);
		jugador.save(jugador10);
		
		List<Jugador> lista1 = new ArrayList<Jugador> ();		
		List<Jugador> lista2 = new ArrayList<Jugador> ();

		Equipo equipo1 = new Equipo("Equipo1", 10, 2, 12, lista1);
		Equipo equipo2 = new Equipo("Equipo2", 2, 12, 2, lista2);
		
		equipo1.getListaJugadores().add(jugador1);
		equipo1.getListaJugadores().add(jugador2);
		equipo1.getListaJugadores().add(jugador3);
		equipo1.getListaJugadores().add(jugador4);
		equipo1.getListaJugadores().add(jugador5);
		equipo2.getListaJugadores().add(jugador6);
		equipo2.getListaJugadores().add(jugador7);
		equipo2.getListaJugadores().add(jugador8);
		equipo2.getListaJugadores().add(jugador9);
		equipo2.getListaJugadores().add(jugador10);
		
		equipo.save(equipo1);
		equipo.save(equipo2);
		List<Equipo> lista3 = new ArrayList<Equipo> ();		

		Clasificacion clasificacion1 = new Clasificacion(lista3);
		
		clasificacion1.getListaClasificacion().add(equipo1);
		clasificacion1.getListaClasificacion().add(equipo2);
		
		clasificacion.save(clasificacion1);
		
		Liga liga1 = new Liga("Liga1", clasificacion1);
				
		liga.save(liga1);
		
	}
	
	@RequestMapping("/liga/clasificacion/")
	public List<Equipo> clasificacionLiga(String nombre){
		
		List<Equipo> equiposClasificacion = liga.findByNombre("Liga1").getClasificacion().getListaClasificacion();
		
		return equiposClasificacion;
		
	}

}

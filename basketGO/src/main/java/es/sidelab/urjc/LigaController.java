package es.sidelab.urjc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
		
		/* En la primera ejecución borrar el esquema "test" y volverlo a crear vacío
		 * Después, descomentar esta sección para crear todos los datos de 0 y tener
		 * algunos de ejemplo.
		 */
		
		/*
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

		Clasificacion clasificacion1 = new Clasificacion("ClasificacionLiga1",lista3);
		
		clasificacion1.getListaClasificacion().add(equipo1);
		clasificacion1.getListaClasificacion().add(equipo2);
		
		clasificacion.save(clasificacion1);
		
		Liga liga1 = new Liga("Liga1", clasificacion1);
				
		liga.save(liga1);
		*/
		
	}
	
/*	@RequestMapping("/liga/clasificacion/")
	public List<Equipo> clasificacionLiga(String nombre){
		
		List<Equipo> equiposClasificacion = liga.findByNombre("Liga1").getClasificacion().getListaClasificacion();
		
		return equiposClasificacion;
		
	}*/
	@GetMapping("/liga")
	public String creandoLiga(Model model){
		return "preliga";
	}
	
	
	@PostMapping("/liga/clasificacion") 
	public String ligaClasificacion(Model model, @RequestParam String nombreLiga){
		List<Liga> listaLigas = liga.findByNombre(nombreLiga);
		Clasificacion clasificacionAux;
		if(listaLigas.size()>0){
			clasificacionAux = listaLigas.get(0).getClasificacion();
			model.addAttribute("clasificacionliga", clasificacionAux.getListaClasificacion());
			return "liga";
		}
		//model.addAttribute("clasificacionliga", liga.findByNombre("Liga1").getClasificacion().getListaClasificacion());
		String mensaje = "No existe la liga";
		model.addAttribute("mensaje", mensaje);
		return "preliga";
	}
	
	@GetMapping("/liga/creacion") 
	public String creacionLiga(Model model){
		boolean creaLiga = true;
		model.addAttribute("creacionliga", creaLiga);
		return "creacionliga";
	}
	
	@PostMapping("/liga/creando") 
	public String creandoLiga(Model model, @RequestParam String nombreLiga, 
			@RequestParam String nombreEquipo1, @RequestParam String nombreEquipo2){
		boolean creaLiga = true;
		String mensaje = "";
		if(nombreLiga==""){
			mensaje = "El nombre de la Liga debe tener al menos un caracter";
			model.addAttribute("creacionliga", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionliga";
		}
		if(nombreEquipo1=="" || nombreEquipo2==""){
			mensaje = "El nombre de los Equipos debe tener al menos un caracter";
			model.addAttribute("creacionliga", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionliga";
		}
		
		List<Equipo> equipo1 = equipo.findByNombreEquipo(nombreEquipo1);
		List<Equipo> equipo2 = equipo.findByNombreEquipo(nombreEquipo2);
		
		if(equipo1.size()!=1){
			mensaje = "El Equipo 1 no existe";
			model.addAttribute("creacionliga", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionliga";
		}
		if(equipo2.size()!=1){
			mensaje = "El Equipo 2 no existe";
			model.addAttribute("creacionliga", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionliga";
		}
		
		List<Equipo> lista3 = new ArrayList<Equipo> ();
		
		Clasificacion clasificacion1 = new Clasificacion("Clasificacion"+nombreLiga,lista3);
		
		if(liga.findByNombre(nombreLiga).size()>0){
			creaLiga=true;
			mensaje="Ya existe una Liga con ese nombre";
			model.addAttribute("creacionliga", creaLiga);
			model.addAttribute("mensaje", mensaje);
			
			return "creacionliga";
		}
		
		List<Clasificacion> clasificacionAux = clasificacion.findAll(); 
		
		if(clasificacionAux.size()>0){
			for(Clasificacion cl:clasificacionAux){
				if(equipo.findByNombreEquipo(nombreEquipo1).size()>0){
					creaLiga=true;
					mensaje="El Equipo1 ya pertenecen a otra liga";
					model.addAttribute("creacionliga", creaLiga);
					model.addAttribute("mensaje", mensaje);					
					return "creacionliga";
				}
				if(equipo.findByNombreEquipo(nombreEquipo2).size()>0){
					creaLiga=true;
					mensaje="El Equipo2 ya pertenecen a otra liga";
					model.addAttribute("creacionliga", creaLiga);
					model.addAttribute("mensaje", mensaje);					
					return "creacionliga";
				}
			}
			
		}
		
		clasificacion1.getListaClasificacion().add(equipo2.get(0));
		clasificacion1.getListaClasificacion().add(equipo1.get(0));
		
		clasificacion.save(clasificacion1);
		
		Liga liga1 = new Liga(nombreLiga, clasificacion1);
				
		liga.save(liga1);
		
		creaLiga=false;
		mensaje="Liga creada correctamente";
		model.addAttribute("creacionliga", creaLiga);
		model.addAttribute("mensaje", mensaje);
		
		return "creacionliga";
	}

}

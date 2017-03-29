package es.sidelab.urjc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EquipoController {
	

	@Autowired
	private EquipoRepository equipo;
	@Autowired
	private JugadorRepository jugador;
	
	@PostConstruct
	public void init(){
	}
	
	@GetMapping("/equipos")
	public String creandoEquipo(Model model, HttpSession session){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		return "preequipo";
	}
	
	@GetMapping("/equipos/porNombre") 
	public String paginaEquipos(Model model, HttpSession session,
			@RequestParam String nombreEquipo){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		List<Equipo> listaEquipos = equipo.findByNombreEquipo(nombreEquipo);
		if(listaEquipos.size()>0){
			model.addAttribute("listaequipos", listaEquipos);
			return "equipo";
		}
		String mensaje = "No existe el equipo";
		model.addAttribute("mensaje", mensaje);
		return "preequipo";
	}
	
	@GetMapping("/equipos/creacion") 
	public String creacionEquipo(Model model, HttpSession session){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		boolean creaEquipo = true;
		model.addAttribute("creacionequipo", creaEquipo);
		return "creacionequipo";
	}
	
	@GetMapping("/equipos/registro") 
	public String creandoEquipo(Model model, HttpSession session, 
			@RequestParam String nombreEquipo, @RequestParam String jugadores){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		boolean creaEquipo = true;
		String mensaje = "";
		String[] nombreJugadores= {"","","","",""};
		if(nombreEquipo==""){
			mensaje = "El nombre del equipo debe tener al menos un caracter";
			model.addAttribute("creacionequipo", creaEquipo);
			model.addAttribute("mensaje", mensaje);
			return "creacionequipo";
		}
		
		nombreJugadores = jugadores.split(",");
		
		if(nombreJugadores.length<5){
			mensaje = "Deben introducirse los nombres de los 5 jugadores separados por comas";
			model.addAttribute("creacionequipo", creaEquipo);
			model.addAttribute("mensaje", mensaje);
			return "creacionequipo";
		}
		
		for(int i=0; i<4; i++){
			for(int j=i+1; j<5; j++){
				if(nombreJugadores[i].equals(nombreJugadores[j])){
					mensaje = "Los jugadores deben ser diferentes";
					model.addAttribute("creacionequipo", creaEquipo);
					model.addAttribute("mensaje", mensaje);
					return "creacionequipo";
				}
			}
		}
		
		List<Equipo> equipo1 = equipo.findByNombreEquipo(nombreEquipo);
		if(equipo1.size()==1){
			mensaje = "El Equipo ya existe";
			model.addAttribute("creacionequipo", creaEquipo);
			model.addAttribute("mensaje", mensaje);
			return "creacionequipo";
		}

		List<Jugador> jugador1 = jugador.findByNombre(nombreJugadores[0]);
		if(jugador1.size()<1){
			mensaje = "El Jugador 1 no existe";
			model.addAttribute("creacionequipo", creaEquipo);
			model.addAttribute("mensaje", mensaje);
			return "creacionequipo";
		}
		List<Jugador> jugador2 = jugador.findByNombre(nombreJugadores[1]);
		if(jugador1.size()<1){
			mensaje = "El Jugador 2 no existe";
			model.addAttribute("creacionequipo", creaEquipo);
			model.addAttribute("mensaje", mensaje);
			return "creacionequipo";
		}
		List<Jugador> jugador3 = jugador.findByNombre(nombreJugadores[2]);
		if(jugador1.size()<1){
			mensaje = "El Jugador 3 no existe";
			model.addAttribute("creacionequipo", creaEquipo);
			model.addAttribute("mensaje", mensaje);
			return "creacionequipo";
		}
		List<Jugador> jugador4 = jugador.findByNombre(nombreJugadores[3]);
		if(jugador1.size()<1){
			mensaje = "El Jugador 4 no existe";
			model.addAttribute("creacionequipo", creaEquipo);
			model.addAttribute("mensaje", mensaje);
			return "creacionequipo";
		}
		List<Jugador> jugador5 = jugador.findByNombre(nombreJugadores[4]);
		if(jugador1.size()<1){
			mensaje = "El Jugador 5 no existe";
			model.addAttribute("creacionequipo", creaEquipo);
			model.addAttribute("mensaje", mensaje);
			return "creacionequipo";
		}
		
		List<Equipo> equiposAux = equipo.findAll(); 
		
		if(equiposAux.size()>0){
			for(Equipo eq:equiposAux){
				for(Jugador ju:eq.getListaJugadores()){
					if(ju.getNombre().equals(nombreJugadores[0])||
						ju.getNombre().equals(nombreJugadores[1])||
						ju.getNombre().equals(nombreJugadores[2])||
						ju.getNombre().equals(nombreJugadores[3])||
						ju.getNombre().equals(nombreJugadores[4])){
							mensaje="El Jugador ya pertenecen a otro equipo";
							model.addAttribute("creacionequipo", creaEquipo);
							model.addAttribute("mensaje", mensaje);					
							return "creacionequipo";
						}
				}
			}
		}
		
		List<Jugador> listaJugador1 = new ArrayList<Jugador>();
		
		Equipo equipo2 = new Equipo(nombreEquipo, 0, 0, 0, listaJugador1);
		
		equipo2.getListaJugadores().add(jugador1.get(0));
		equipo2.getListaJugadores().add(jugador2.get(0));
		equipo2.getListaJugadores().add(jugador3.get(0));
		equipo2.getListaJugadores().add(jugador4.get(0));
		equipo2.getListaJugadores().add(jugador5.get(0));
				
		equipo.save(equipo2);
		
		creaEquipo=false;
		mensaje="Equipo creado correctamente";
		model.addAttribute("creacionequipo", creaEquipo);
		model.addAttribute("mensaje", mensaje);
		
		return "creacionequipo";
	}
	
}

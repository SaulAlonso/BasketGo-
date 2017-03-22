package es.sidelab.urjc;

import java.util.ArrayList;
import java.util.List;

//import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class JugadorController {
	

	@Autowired
	private JugadorRepository jugador;
	
	@PostConstruct
	public void init(){
	}
	
	@GetMapping("/jugadores")
	public String creandoJugadores(Model model, HttpServletRequest request){
		model.addAttribute("superadmin", request.isUserInRole("SUPERADMIN"));
		model.addAttribute("manager", request.isUserInRole("MANAGER"));
		return "prejugador";
	}
	
	@GetMapping("/jugadores/porNombre")
	public String paginaJugadoresPorNombre(Model model, HttpServletRequest request,
			@RequestParam String nombreJugador){
		model.addAttribute("superadmin", request.isUserInRole("SUPERADMIN"));
		model.addAttribute("manager", request.isUserInRole("MANAGER"));
		List<Jugador> listaJugadores = jugador.findByNombre(nombreJugador);
		if(listaJugadores.size()>0){
			model.addAttribute("etiqueta", "Nombre Jugador - Altura");
			model.addAttribute("listaJugadores", listaJugadores);
			return "jugadores";
		}
		String mensaje = "No existe el jugador";
		model.addAttribute("mensaje", mensaje);
		return "prejugador";		
	}
	
	@GetMapping("/jugadores/creacion") 
	public String creacionJugador(Model model, HttpServletRequest request){
		model.addAttribute("superadmin", request.isUserInRole("SUPERADMIN"));
		model.addAttribute("manager", request.isUserInRole("MANAGER"));
		boolean creaLiga = true;
		model.addAttribute("jugadorcreacion", creaLiga);
		return "creacionjugador";
	}
	
	@PostMapping("/jugadores/registro") 
	public String creandoJugadores(Model model, HttpServletRequest request,
			@RequestParam String nombreJugador, @RequestParam String alturaJugador,
			@RequestParam String edadJugador){
		model.addAttribute("superadmin", request.isUserInRole("SUPERADMIN"));
		model.addAttribute("manager", request.isUserInRole("MANAGER"));
		boolean creaLiga = true;
		String mensaje = "";
		if(nombreJugador==""){
			mensaje = "El nombre del Jugador debe tener al menos un caracter";
			model.addAttribute("jugadorcreacion", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionjugador";
		}
		Double altura;
		int edad;
		
		try{
			altura=Double.parseDouble(alturaJugador);
		}catch(Exception e){
			mensaje = "La altura debe ser un número válido";
			model.addAttribute("jugadorcreacion", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionjugador";
		}
		try{
			edad=Integer.parseInt(edadJugador);
		}catch(Exception e){
			mensaje = "La edad debe ser un número válido";
			model.addAttribute("jugadorcreacion", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionjugador";
		}
		
		List<Jugador> jugador1 = jugador.findByNombre(nombreJugador);
		
		if(jugador1.size()==1){
			mensaje = "El Jugador ya existe";
			model.addAttribute("jugadorcreacion", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionjugador";
		}
		
		Jugador jugador2 = new Jugador(nombreJugador, altura, edad);
				
		jugador.save(jugador2);
		
		creaLiga=false;
		mensaje="Jugador registrado correctamente";
		model.addAttribute("jugadorcreacion", creaLiga);
		model.addAttribute("mensaje", mensaje);
		
		return "creacionjugador";
	}

}

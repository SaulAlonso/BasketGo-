package es.sidelab.urjc;

import java.util.ArrayList;
import java.util.List;

//import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class JugadorController {
	

	@Autowired
	private JugadorRepository jugador;
	
	@PostConstruct
	public void init(){
	}
	
	@GetMapping("/jugadores")
	public String creandoJugadores(Model model, HttpSession session){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		return "prejugador";
	}
	
	@GetMapping("/jugadores/porNombre")
	public String paginaJugadoresPorNombre(Model model, HttpSession session,
			@RequestParam String nombreJugador){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		System.out.println(nombreJugador);
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
	public String creacionJugador(Model model, HttpSession session){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		boolean creaLiga = true;
		model.addAttribute("jugadorcreacion", creaLiga);
		return "creacionjugador";
	}
	
	@GetMapping("/jugadores/registro") 
	public String creandoJugadores(Model model, HttpSession session,
			@RequestParam String nombreJugador, @RequestParam String alturaJugador,
			@RequestParam String edadJugador){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
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
	
	@GetMapping("/jugadores/preedicion") 
	public String edicionJugador(Model model, HttpSession session){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		boolean editaLiga = true;
		model.addAttribute("jugadoredicion", editaLiga);
		return "edicionjugador";
	}
	
	@GetMapping("/jugadores/edicion") 
	public String editandoJugadores(Model model, HttpSession session,
			@RequestParam String nombreJugador, @RequestParam String alturaJugador,
			@RequestParam String edadJugador){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		
		List<Jugador> jugador1 = jugador.findByNombre(nombreJugador);
		boolean editaLiga = true;
		String mensaje = "";
		if(nombreJugador==""){
			mensaje = "El nombre del Jugador debe tener al menos un caracter";
			model.addAttribute("jugadoredicion", editaLiga);
			model.addAttribute("mensaje", mensaje);
			return "edicionjugador";
		}else{
			
			if(jugador1.size()==0){
				mensaje = "El Jugador no existe";
				model.addAttribute("jugadoredicion", editaLiga);
				model.addAttribute("mensaje", mensaje);
				return "edicionjugador";
			}
		}
		
		Double altura;
		int edad;
		
		try{
			altura=Double.parseDouble(alturaJugador);
		}catch(Exception e){
			mensaje = "La altura debe ser un número válido";
			model.addAttribute("jugadoredicion", editaLiga);
			model.addAttribute("mensaje", mensaje);
			return "edicionjugador";
		}
		try{
			edad=Integer.parseInt(edadJugador);
		}catch(Exception e){
			mensaje = "La edad debe ser un número válido";
			model.addAttribute("jugadoredicion", editaLiga);
			model.addAttribute("mensaje", mensaje);
			return "edicionjugador";
		}
		
		
		
		jugador1.get(0).setAltura(altura);
		jugador1.get(0).setEdad(edad);
				
		jugador.save(jugador1.get(0));
		
		editaLiga=false;
		mensaje="Jugador editado correctamente";
		model.addAttribute("jugadoredicion", editaLiga);
		model.addAttribute("mensaje", mensaje);
		
		return "edicionjugador";
	}

}

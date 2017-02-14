package es.sidelab.urjc;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class JugadorController {
	

	@Autowired
	private JugadorRepository jugador;
	
	@PostConstruct
	public void init(){
	}
	
	@RequestMapping("/jugadores")
	public Page<Jugador> todosJugadores(Pageable page){
		List<Jugador> listaJugadores = (List<Jugador>) jugador.findAll(page);
		return listaJugadores;
		
	}
	
	@RequestMapping("/jugadores/porNombre")
	public Page<Jugador> nombreJugadores(String nombre, Pageable page){
		
		return jugador.findByNombre(nombre, page);
		
	}

}

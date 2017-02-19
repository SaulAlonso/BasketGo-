package es.sidelab.urjc;

//import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class JugadorController {
	

	@Autowired
	private JugadorRepository jugador;
	
	@PostConstruct
	public void init(){
	}
	
	/*@RequestMapping("/jugadores")
	public Page<Jugador> todosJugadores(Pageable page){
		return jugador.findAll(page);
		
	}*/
	
	@RequestMapping("/jugadores") 
	public String paginaJugadores(Model model/*, Pageable page*/){
		model.addAttribute("listaJugadores", jugador.findAll());
		return "jugadores";
	}
	
	@RequestMapping("/jugadores/porNombre")
	public String paginaJugadoresPorNombre(Model model, String nombre/*, Pageable page*/){
		model.addAttribute("listaJugadores", jugador.findByNombre(nombre));
		return "jugadores";
	}
	
	
	/*public Page<Jugador> nombreJugadores(String nombre, Pageable page){
		
		return jugador.findByNombre(nombre, page);
		
	}*/

}

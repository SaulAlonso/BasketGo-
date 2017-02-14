package es.sidelab.urjc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class EquipoController {
	

	@Autowired
	private EquipoRepository equipo;
	
	@PostConstruct
	public void init(){
	}
	
	@RequestMapping("/equipos/")
	public Page<Equipo> todosEquipos(Pageable page, Sort sort){
		
		return equipo.findAll(page, new Sort("nombreEquipo"));
		
	}
	
	@RequestMapping("/equipos/porNombre/")
	public Page<Equipo> nombreEquipos(String nombre, Pageable page){
		
		return equipo.findByNombreEquipo(nombre, page);
		
	}

}

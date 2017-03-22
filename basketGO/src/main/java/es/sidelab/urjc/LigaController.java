package es.sidelab.urjc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
/*	@Autowired
	private JugadorRepository jugador;*/
	@Autowired
	private UsuarioRepository usuario;
	
	@PostConstruct
	public void init(){
		
	}
	
/*	@RequestMapping("/liga/clasificacion/")
	public List<Equipo> clasificacionLiga(String nombre){
		
		List<Equipo> equiposClasificacion = liga.findByNombre("Liga1").getClasificacion().getListaClasificacion();
		
		return equiposClasificacion;
		
	}*/
	@GetMapping("/liga")
	public String creandoLiga(Model model, HttpServletRequest request, HttpSession session){
		//model.addAttribute("superadmin", (boolean)session.getAttribute("superadmin"));
	//	model.addAttribute("admin", (boolean)session.getAttribute("admin"));
		return "preliga";
	}
	
	
	@GetMapping("/liga/clasificacion") 
	public String ligaClasificacion(Model model, /*HttpServletRequest request,*/
			@RequestParam String nombreLiga){
		/*model.addAttribute("superadmin", request.isUserInRole("SUPERADMIN"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));*/
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
	public String creacionLiga(Model model, HttpServletRequest request){
		model.addAttribute("superadmin", request.isUserInRole("SUPERADMIN"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		boolean creaLiga = true;
		model.addAttribute("creacionliga", creaLiga);
		return "creacionliga";
	}
	
	@PostMapping("/liga/creando") 
	public String creandoLiga(Model model, HttpServletRequest request,
			@RequestParam String nombreLiga, @RequestParam String nombreEquipo1,
			@RequestParam String nombreEquipo2){
		model.addAttribute("superadmin", request.isUserInRole("SUPERADMIN"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
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
		
		if(nombreEquipo1.equals(nombreEquipo2)){
			mensaje = "Los equipos deben ser diferentes";
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
	
	@GetMapping("/liga/gestion") 
	public String gestionLiga(Model model, HttpServletRequest request){
		model.addAttribute("superadmin", request.isUserInRole("SUPERADMIN"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		boolean gestionarliga = true;
		model.addAttribute("gestionarliga", gestionarliga);
		return "gestionarliga";
	}
	
	@PostMapping("/liga/gestionando") 
	public String gestionandoLiga(Model model, HttpServletRequest request,
			@RequestParam String nombreLiga, @RequestParam String nombreEquipo,
			@RequestParam String victoriasEquipo, @RequestParam String derrotasEquipo){
		model.addAttribute("superadmin", request.isUserInRole("SUPERADMIN"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		boolean gestionarliga = true;
		String mensaje = "";
		if(nombreLiga==""){
			mensaje = "El nombre de la Liga debe tener al menos un caracter";
			model.addAttribute("gestionarliga", gestionarliga);
			model.addAttribute("mensaje", mensaje);
			return "gestionarliga";
		}
		if(nombreEquipo==""){
			mensaje = "El nombre del Equipo debe tener al menos un caracter";
			model.addAttribute("gestionarliga", gestionarliga);
			model.addAttribute("mensaje", mensaje);
			return "gestionarliga";
		}
		
		List<Equipo> equipo1 = equipo.findByNombreEquipo(nombreEquipo);
		
		if(equipo1.size()<1){
			mensaje = "El Equipo no existe";
			model.addAttribute("gestionarliga", gestionarliga);
			model.addAttribute("mensaje", mensaje);
			return "gestionarliga";
		}
		
		List<Liga> liga1 = liga.findByNombre(nombreLiga);
		
		if(liga1.size()<1){
			mensaje="La liga no existe";
			model.addAttribute("gestionarliga", gestionarliga);
			model.addAttribute("mensaje", mensaje);
			return "gestionarliga";
		}
		
		int vic, der;

		try{
			vic=Integer.parseInt(victoriasEquipo);
		}catch(Exception e){
			mensaje = "El número de victorias debe ser un número válido";
			model.addAttribute("gestionarliga", gestionarliga);
			model.addAttribute("mensaje", mensaje);
			return "gestionarliga";
		}
		try{
			der=Integer.parseInt(derrotasEquipo);
		}catch(Exception e){
			mensaje = "El número de derrotas debe ser un número válido";
			model.addAttribute("gestionarliga", gestionarliga);
			model.addAttribute("mensaje", mensaje);
			return "gestionarliga";
		}
		
		equipo1.get(0).setNumeroVictorias(vic);
		equipo1.get(0).setNumeroDerrotas(der);
		equipo1.get(0).setPuntuacion(vic-der);
		equipo.save(equipo1.get(0));
		mensaje="Clasificacion actualizada correctamente";
		model.addAttribute("gestionarliga", gestionarliga);
		model.addAttribute("mensaje", mensaje);					
		return "gestionarliga";	

	}
	
	@GetMapping("/liga/anadir") 
	public String anadirEquipoLiga(Model model, HttpServletRequest request){
		model.addAttribute("superadmin", request.isUserInRole("SUPERADMIN"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		boolean anadirequipoliga = true;
		model.addAttribute("anadirequipoliga", anadirequipoliga);
		return "anadirequipoliga";
	}
	
	@PostMapping("/liga/anadiendo") 
	public String anadiendoEquipoLiga(Model model, HttpServletRequest request,
			@RequestParam String nombreLiga, @RequestParam String nombreEquipo1,
			@RequestParam String nombreEquipo2){
		model.addAttribute("superadmin", request.isUserInRole("SUPERADMIN"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		boolean anadirequipoliga = true;
		String mensaje = "";
		if(nombreLiga==""){
			mensaje = "El nombre de la Liga debe tener al menos un caracter";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		if(nombreEquipo1==""){
			mensaje = "El nombre del Equipo1 debe tener al menos un caracter";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		if(nombreEquipo2==""){
			mensaje = "El nombre del Equipo2 debe tener al menos un caracter";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		
		if(nombreEquipo1.equals(nombreEquipo2)){
			mensaje = "Los equipos deben ser distintos";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		
		List<Equipo> equipo1 = equipo.findByNombreEquipo(nombreEquipo1);
		List<Equipo> equipo2 = equipo.findByNombreEquipo(nombreEquipo2);
		
		if(equipo1.size()<1){
			mensaje = "El Equipo1 no existe";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		if(equipo2.size()<1){
			mensaje = "El Equipo2 no existe";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		
		List<Liga> liga1 = liga.findByNombre(nombreLiga);
		
		if(liga1.size()<1){
			mensaje="La liga no existe";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		
		List<Clasificacion> clasificacionAux = clasificacion.findAll();
		
		if(clasificacionAux.size()>=10){
			mensaje="Se ha alcanzado el máximo de Equipos para esta Liga";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);					
			return "anadirequipoliga";
		}
		
		if(clasificacionAux.size()>0){
			for(Clasificacion cl:clasificacionAux){
				if(equipo.findByNombreEquipo(nombreEquipo1).size()>0){
					mensaje="El Equipo ya pertenece a otra liga";
					model.addAttribute("anadirequipoliga", anadirequipoliga);
					model.addAttribute("mensaje", mensaje);					
					return "anadirequipoliga";
				}
				if(equipo.findByNombreEquipo(nombreEquipo2).size()>0){
					mensaje="El Equipo ya pertenece a otra liga";
					model.addAttribute("anadirequipoliga", anadirequipoliga);
					model.addAttribute("mensaje", mensaje);					
					return "anadirequipoliga";
				}
			}
			
		}
		
		liga1.get(0).getClasificacion().getListaClasificacion().add(equipo1.get(0));
		liga1.get(0).getClasificacion().getListaClasificacion().add(equipo2.get(0));;
		liga.save(liga1.get(0));
		mensaje="Equipos añadidos correctamente";
		model.addAttribute("anadirequipoliga", anadirequipoliga);
		model.addAttribute("mensaje", mensaje);					
		return "anadirequipoliga";	

	}
	
	
	

}

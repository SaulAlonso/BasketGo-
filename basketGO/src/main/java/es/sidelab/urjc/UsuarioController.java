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
public class UsuarioController {
	

	@Autowired
	private UsuarioRepository usuario;
	
	@PostConstruct
	public void init(){
	}
	
	@GetMapping("/preusuarios")
	public String creandoLiga(Model model, HttpSession session){

		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		return "registro";
	}

	@GetMapping("/usuario") 
	public String registoUsuario(Model model, HttpSession session, 
			@RequestParam String name, @RequestParam String passwordHash, @RequestParam String rol){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		boolean creaUsuario = true;
		String mensaje = "";
		if(name==""){
			mensaje = "El nombre del usuario debe tener al menos un caracter";
			model.addAttribute("creausuario", creaUsuario);
			model.addAttribute("mensaje", mensaje);
			return "registro";
		}
				
		if(passwordHash==""){
			mensaje = "La contraseña debe contener al menos un caracter";
			model.addAttribute("creausuario", creaUsuario);
			model.addAttribute("mensaje", mensaje);
			return "registro";
		}
		
		if(rol==""){
			mensaje = "Debe seleccionar al menos un rol";
			model.addAttribute("creausuario", creaUsuario);
			model.addAttribute("mensaje", mensaje);
			return "registro";
		}
		
		Usuario usuarios = new Usuario();

		usuarios= usuario.findByName(name);
		
		if(usuarios!=null){
			mensaje = "Ya existe un usuario con ese nombre";
			model.addAttribute("creausuario", creaUsuario);
			model.addAttribute("mensaje", mensaje);
			return "registro";
		}
		
		usuario.save(new Usuario(name,passwordHash,rol));
		
		creaUsuario=false;
		mensaje="Usuario registrado correctamente";
		model.addAttribute("creausuario", creaUsuario);
		model.addAttribute("mensaje", mensaje);
		return "registro";
	}
	
}

package es.sidelab.urjc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class RegistroController {

	@Autowired
	private UsuarioRepository usuario;
	
	@PostConstruct
	public void init(){
	}
	
	@GetMapping("/registrado") 
	public String creandoEquipo(Model model, /*HttpServletRequest request,*/
			@RequestParam String nombreUsuario, 
			@RequestParam String passwordUsuario, @RequestParam String rolUsuario){
		String mensaje = "";
		if(nombreUsuario==""){
			mensaje = "El nombre del usuario debe tener al menos un caracter";
			model.addAttribute("mensaje", mensaje);
			return "registro";
		}
		
		if(passwordUsuario==""){
			mensaje = "La contraseña debe tener al menos un caracter";
			model.addAttribute("mensaje", mensaje);
			return "registro";
		}
		
		if(passwordUsuario.length()<5){
			mensaje = "La contraseña debe tener al menos 5 carácteres";
			model.addAttribute("mensaje", mensaje);
			return "registro";
		}
		
		Usuario usuario1 = usuario.findByNombre(nombreUsuario);
		if(usuario1!=null){
			mensaje = "El usuario ya está registrado";
			model.addAttribute("mensaje", mensaje);
			return "registro";
		}

		Usuario usuario2 = new Usuario(nombreUsuario, passwordUsuario, rolUsuario);
		usuario.save(usuario2);

		mensaje="Usuario registrado correctamente";
		model.addAttribute("mensaje", mensaje);
		
		return "index";
	}
		
}

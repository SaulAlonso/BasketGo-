package es.sidelab.urjc;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BasketController {
	
	
	@GetMapping("/")
	public String paginaPrincipal(Model model, HttpSession session){
		if(session.isNew()){
			session.setAttribute("loged", false);
			session.setAttribute("nologed", true);
			session.setAttribute("administrador", false);
			session.setAttribute("usuario", false);
		}
		model.addAttribute("nologueado", (boolean) session.getAttribute("nologed"));
		model.addAttribute("logueado", (boolean) session.getAttribute("loged"));
		model.addAttribute("administrador", (boolean) session.getAttribute("administrador"));
		model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		return "index";
		 
	}
	
	@GetMapping("/main")
	public String paginaMain(HttpSession session){

		return "index";
		 
	}
	
	@GetMapping("/login")
	public String loguin() {
	
		return "login";
		
	}
	
	@GetMapping("/postlogin")
	public String postlogin(Model model, HttpServletRequest request, HttpSession session) {
		
		model.addAttribute("bienvenida",true);
		session.setAttribute("administrador", (boolean) request.isUserInRole("ADMIN"));
		session.setAttribute("usuario", (boolean) request.isUserInRole("USER"));
	    model.addAttribute("admin", (boolean) request.isUserInRole("ADMIN"));
		model.addAttribute("user", (boolean) request.isUserInRole("USER"));
		session.setAttribute("loged", true);
		model.addAttribute("logueado", (boolean) session.getAttribute("loged"));
		session.setAttribute("nologed", false);
		model.addAttribute("nologueado", (boolean) session.getAttribute("nologed"));
		return "main";
	
	}
	
	@GetMapping("/loginerror")
	public String loginerror(Model model) {
		String mensajeError = "Nombre de usuario o contraseña incorrectos";
		model.addAttribute("mensajeerror",mensajeError);
		return "loginerror";
		
	}
	
	@GetMapping("/invitado")
	public String invitado(Model model, HttpSession session){
		
		model.addAttribute("invitado",(String)session.getAttribute("invitado"));
		return "index";
	}
	
	@GetMapping("/registro")
	public String registro(Model model, HttpSession session){
		return "registro";
	}
	

}

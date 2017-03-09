package es.sidelab.urjc;

import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BasketController {
	
	
	@GetMapping("/")
	public String paginaPrincipal(Model model, HttpSession session){
		
		if(session.isNew()){
			session.setAttribute("logueado", false);
			return "loguin";
		}if(!(boolean)session.getAttribute("logueado")){
			return "loguin";
		}
		model.addAttribute("bienvenida",true);
		model.addAttribute("usuario", (String)session.getAttribute("usuario"));
		return "index";
		
	}
	@RequestMapping("/loguin")
	public String login(Model model, HttpServletRequest request) {

	 CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
	 model.addAttribute("token", token.getToken());

	 return "loguin";
	}
	
	@GetMapping("/invitado")
	public String invitado(Model model, HttpSession session){
		
		model.addAttribute("invitado",(String)session.getAttribute("invitado"));
		return "index";
	}
	
	@PostMapping("/loged") 
	public String paginaJugadores(Model model, HttpSession session,
			@RequestParam String nombreUsuario){
		if(nombreUsuario.isEmpty()){
			model.addAttribute("mensaje","El nombre no puede estar vacío");
			return "loguin";
		}
		session.setAttribute("usuario", nombreUsuario);
		session.setAttribute("logueado", true);
		model.addAttribute("bienvenida",true);
		model.addAttribute("usuario", (String)session.getAttribute("usuario"));
		return "index";
	}

}

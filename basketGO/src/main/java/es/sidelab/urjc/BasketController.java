package es.sidelab.urjc;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BasketController {
	
	
	@GetMapping("/")
	public String paginaPrincipal(Model model, HttpSession session){
		
		model.addAttribute("bienvenida",session.isNew());
		return "index";
		
	}
	@RequestMapping("/login") 
	public String paginaJugadores(Model model){
		model.addAttribute("logueado", true);
		return "loguin";
	}

}

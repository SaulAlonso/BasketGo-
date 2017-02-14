package es.sidelab.urjc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BasketController {
	

	@Autowired
	private Usuario usuario;
	
	@GetMapping("/")
	public String paginaPrincipal(Model model, HttpSession session){
		
		model.addAttribute("usuario",usuario);
		model.addAttribute("bienvenida",session.isNew());
		return "loguin";
		
	}
	

	
	@PostMapping("/loguin/log")
	public String inicioSesion(Model model,Usuario usuario){
		

		return "index";
	}

}

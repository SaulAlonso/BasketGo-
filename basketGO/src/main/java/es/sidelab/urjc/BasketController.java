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
	public String paginaPrincipal(Model model, HttpServletRequest request, HttpSession session){
		
		if(session.isNew()){
			session.setAttribute("logueado", false);
			return "loguin";
		}if(!(boolean)session.getAttribute("logueado")){
			return "loguin";
		}
		model.addAttribute("usuario", (String)session.getAttribute("usuario"));
		return "index";
		
	}
	@GetMapping("/loguin")
	public String loguin() {
	
		return "loguin";
		
	}
	
	@GetMapping("/postloguin")
	public String postloguin(Model model, HttpServletRequest request, HttpSession session) {
		
		session.setAttribute("bienvenida",true);
		model.addAttribute("superadmin", request.isUserInRole("SUPERADMIN"));
		model.addAttribute("superadmin", request.isUserInRole("ROLE_SUPERADMIN"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("manager", request.isUserInRole("MANAGER"));
		session.setAttribute("logueado", true);
		return "index";
		
	}
	
	@GetMapping("/loguinerror")
	public String loguinerror(Model model) {
	
		return "loguinerror";
		
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

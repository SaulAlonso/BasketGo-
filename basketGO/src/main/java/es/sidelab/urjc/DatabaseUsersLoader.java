package es.sidelab.urjc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@PostConstruct
	private void initDatabase() {
		/*userRepository.save(new Usuario("user","pass","ROLE_ADMIN"));
		userRepository.save(new Usuario("admin","adminpass","ROLE_USER"));
		userRepository.save(new Usuario("superadmin","superpass","ROLE_ADMIN", "ROLE_USER"));*/
	}
}
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
		/*userRepository.save(new Usuario("user","pass","ROLE_SUPERADMIN"));
		userRepository.save(new Usuario("admin","adminpass","ROLE_ADMIN"));*/
	}
}
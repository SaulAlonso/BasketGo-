package es.sidelab.urjc;


import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

public interface UsuarioRepository extends  CrudRepository<Usuario, Long> {

	Usuario findByNombre(String nombre);

}

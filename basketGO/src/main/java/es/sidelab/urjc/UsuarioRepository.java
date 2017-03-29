package es.sidelab.urjc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends  CrudRepository<Usuario, Long> {

	//List<Usuario> findByNombre(String nombre);
	Usuario findByName(String name);

}

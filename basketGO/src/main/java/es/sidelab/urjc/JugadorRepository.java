package es.sidelab.urjc;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

	Page<Jugador> findAll(Pageable page);
	Page<Jugador> findByNombre(String nombre, Pageable page);
	List<Jugador> findByNombre(String nombre);

}

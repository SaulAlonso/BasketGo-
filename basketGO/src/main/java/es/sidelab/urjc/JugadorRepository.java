package es.sidelab.urjc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

	Page<Jugador> findAll(Pageable page, Sort sort);
	Page<Jugador> findByNombre(String nombre, Pageable page);

}

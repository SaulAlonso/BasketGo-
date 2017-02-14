package es.sidelab.urjc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

	Page<Equipo> findAll(Pageable page);
	Page<Equipo> findByNombreEquipo(String nombreEquipo, Pageable page);

}

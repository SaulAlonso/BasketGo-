package es.sidelab.urjc;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigaRepository extends JpaRepository<Liga, Long> {
	
	Page<Liga> findAll(Pageable page);
	List<Liga> findByNombre(String nombre);
	//Equipo findByNombre(String nombre);
	
}

package es.sidelab.urjc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigaRepository extends JpaRepository<Liga, Long> {
	
	Page<Liga> findAll(Pageable page);
	Liga findByNombre(String nombre);
	
}

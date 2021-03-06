package es.sidelab.urjc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {

	List<Clasificacion> findByNombreClasificacion(String nombreClasificacion);
}

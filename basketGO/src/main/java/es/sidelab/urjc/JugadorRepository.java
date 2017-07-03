package es.sidelab.urjc;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames="cacheJugadores")
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

	Page<Jugador> findAll(Pageable page);
	Page<Jugador> findByNombre(String nombre, Pageable page);
	
	@Cacheable
	List<Jugador> findByNombre(String nombre);
	
	@CacheEvict
	Jugador save(Jugador jugador);

}

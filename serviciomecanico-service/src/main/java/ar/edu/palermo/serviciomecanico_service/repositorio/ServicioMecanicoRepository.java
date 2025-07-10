package ar.edu.palermo.serviciomecanico_service.repositorio;

import ar.edu.palermo.serviciomecanico_service.dominio.ServicioMecanico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioMecanicoRepository extends JpaRepository<ServicioMecanico, Integer> {
}

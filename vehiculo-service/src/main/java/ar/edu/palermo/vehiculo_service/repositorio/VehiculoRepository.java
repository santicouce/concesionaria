package ar.edu.palermo.vehiculo_service.repositorio;

import ar.edu.palermo.vehiculo_service.dominio.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
}

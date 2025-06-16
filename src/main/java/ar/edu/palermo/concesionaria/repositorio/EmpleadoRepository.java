package ar.edu.palermo.concesionaria.repositorio;

import ar.edu.palermo.concesionaria.dominio.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
}

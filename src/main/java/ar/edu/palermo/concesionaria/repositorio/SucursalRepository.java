package ar.edu.palermo.concesionaria.repositorio;

import ar.edu.palermo.concesionaria.dominio.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    Sucursal findByEsCentralTrue();

}

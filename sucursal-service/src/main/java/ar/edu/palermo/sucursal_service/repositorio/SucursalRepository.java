package ar.edu.palermo.sucursal_service.repositorio;

import ar.edu.palermo.sucursal_service.dominio.Sucursal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    Optional<Sucursal> findByEsCentralTrue();

}

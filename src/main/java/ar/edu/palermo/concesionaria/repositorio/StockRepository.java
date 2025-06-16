package ar.edu.palermo.concesionaria.repositorio;

import ar.edu.palermo.concesionaria.dominio.Stock;
import ar.edu.palermo.concesionaria.dominio.Sucursal;
import ar.edu.palermo.concesionaria.dominio.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    Optional<Stock> findBySucursalAndVehiculo(Sucursal sucursal, Vehiculo vehiculo);
}

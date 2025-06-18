package ar.edu.palermo.concesionaria.repositorio;

import ar.edu.palermo.concesionaria.dominio.Cliente;
import ar.edu.palermo.concesionaria.dominio.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    List<Venta> findByCliente(Cliente cliente);
    Optional<Venta> findByVehiculoId(Integer vehiculoId);
}

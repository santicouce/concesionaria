package ar.edu.palermo.venta_service.repositorio;

import ar.edu.palermo.venta_service.dominio.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    List<Venta> findByClienteId(Integer clienteId);
    Optional<Venta> findByVehiculoId(Integer vehiculoId);
}

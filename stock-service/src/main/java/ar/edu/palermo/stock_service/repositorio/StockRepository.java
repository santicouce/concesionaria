package ar.edu.palermo.stock_service.repositorio;

import ar.edu.palermo.stock_service.dominio.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    Optional<Stock> findBySucursalIdAndVehiculoId(Integer sucursalId, Integer vehiculoId);
}

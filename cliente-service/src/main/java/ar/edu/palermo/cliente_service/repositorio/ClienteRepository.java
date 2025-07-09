package ar.edu.palermo.cliente_service.repositorio;

import ar.edu.palermo.cliente_service.dominio.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findById(Integer id);
}

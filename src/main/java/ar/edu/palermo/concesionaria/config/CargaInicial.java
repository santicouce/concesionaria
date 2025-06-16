package ar.edu.palermo.concesionaria.config;

import ar.edu.palermo.concesionaria.dominio.*;
import ar.edu.palermo.concesionaria.repositorio.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CargaInicial implements CommandLineRunner {
    // Cargo datos iniciales con fines de prueba para tener datos en la base de datos al iniciar la aplicación.
    private final ClienteRepository clienteRepository;
    private final VehiculoRepository vehiculoRepository;
    private final SucursalRepository sucursalRepository;
    private final StockRepository stockRepository;
    private final EmpleadoRepository empleadoRepository;

    public CargaInicial(
        ClienteRepository clienteRepository,
        VehiculoRepository vehiculoRepository,
        SucursalRepository sucursalRepository,
        StockRepository stockRepository,
        EmpleadoRepository empleadoRepository
    ) {
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.sucursalRepository = sucursalRepository;
        this.stockRepository = stockRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public void run(String... args) {
        // Clientes
        Cliente c1 = new Cliente("Juan", "Pérez", "1234567890", "1515151515");
        Cliente c2 = new Cliente("Ana", "Gómez", "0987654321", "1616161616");
        clienteRepository.save(c1);
        clienteRepository.save(c2);

        // Vehículos
        Vehiculo v1 = new Vehiculo("1587489547", "Toyota", "Corolla", 2022);
        Vehiculo v2 = new Vehiculo("2589631470", "Ford", "Focus", 2021);
        Vehiculo v3 = new Vehiculo("3698521470", "Chevrolet", "Onix", 2023);
        vehiculoRepository.save(v1);
        vehiculoRepository.save(v2);
        vehiculoRepository.save(v3);

        // Sucursal
        Sucursal sucCentral = new Sucursal("Sucursal Central", "Av. Libertador 1234", "Buenos Aires", "CABA", "Argentina", LocalDate.of(2020, 1, 1), 5, 0);
        Sucursal sucNote = new Sucursal("Sucursal Norte", "Av. Santa Fe 5678", "Buenos Aires", "CABA", "Argentina", LocalDate.of(2021, 6, 15), 3, 8);
        sucCentral.setEsCentral(true);
        sucursalRepository.save(sucCentral);
        sucursalRepository.save(sucNote);

        // Empleados
        Empleado e1 = new Empleado("Carlos", "López","001", "Vendedor", sucCentral);
        Empleado e2 = new Empleado("Pedro", "García", "002", "Gerente", sucCentral);
        Empleado e3 = new Empleado("Lucía", "Martínez", "003", "Vendedor", sucNote);
        Empleado e4 = new Empleado("María", "Fernández", "004", "Gerente", sucNote);
        empleadoRepository.save(e1);
        empleadoRepository.save(e2);
        empleadoRepository.save(e3);
        empleadoRepository.save(e4);

        // Stock
        Stock s1 = new Stock(sucCentral, v1, 5);  // stock de vehículos para toda la red 
        Stock s2 = new Stock(sucCentral, v2, 3);  // stock de vehículos para toda la red
        Stock s3 = new Stock(sucNote, v3, 2);  // stock en particular para la concesionaria
        stockRepository.save(s1);
        stockRepository.save(s2);
        stockRepository.save(s3);
        System.out.println("Datos iniciales cargados correctamente.");
    }
}

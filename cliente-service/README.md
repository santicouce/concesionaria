# Microservicio: Cliente Service

Este microservicio forma parte del sistema de gestión de una concesionaria, y se encarga exclusivamente de la administración de los **clientes**.

## Funcionalidad

- Permite registrar, listar, eliminar y consultar clientes.
- Está preparado para integrarse con otros microservicios como el de ventas, sucursales, etc.
- Utiliza base de datos H2 en memoria.
- Se registra en Eureka Server para descubrimiento de servicios.

## El servidor corre en

```bash
http://localhost:8081/
```

## Cómo correr este microservicio

1. Cloná el repositorio.
2. Ejecutá el siguiente comando desde terminal:

```bash
mvnw.cmd spring-boot:run
```

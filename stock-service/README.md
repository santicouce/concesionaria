# Microservicio: Stock Service

Este microservicio forma parte del sistema de gestión de una concesionaria, y se encarga exclusivamente de la gestion del **stock**.

## Funcionalidad

- Permite registrar, listar, eliminar y consultar stock.
- Está preparado para integrarse con otros microservicios como el de empleados, clientes, etc.
- Utiliza base de datos H2 en memoria.
- Se registra en Eureka Server para descubrimiento de servicios.

## El servidor corre en

```bash
http://localhost:8087/
```

## Cómo correr este microservicio

1. Cloná el repositorio.
2. Ejecutá el siguiente comando desde terminal:

```bash
mvnw.cmd spring-boot:run
```

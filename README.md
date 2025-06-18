# Concesionaria - Trabajo Práctico

Este proyecto fue desarrollado como parte de la materia **Microservicios y APIs Escalables** de la Universidad de Palermo.  
La idea principal es simular el funcionamiento de una red de concesionarias, donde se pueden gestionar clientes, vehículos, ventas, servicios mecánicos y stock.
Al iniciar el servidor corre una pequeña logica que carga datos iniciales en la base de datos. (ver archivo CargaInicial.java)

## Supuestos

- Solo puede haber una única sucursal central.
- Cualquier cliente puede hacer el service de un auto comprado en la consecionaria, sin importa la sucursal.
- Los empleados solo venden vehiculos para la sucursal asignada.
- Central puede enviar autos a sucursales, pero entre sucursales no pueden.

## Comando para iniciar el proyecto

Como en mi entorno local tengo instalado Maven Wrapper pero no Maven globalmente, debo iniciar el proyecto con:

```bash
mvnw.cmd spring-boot:run
```

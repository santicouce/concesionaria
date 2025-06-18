# Concesionaria - Trabajo Práctico

Este proyecto fue desarrollado como parte de la materia **Microservicios y APIs Escalables** de la Universidad de Palermo.  
La idea principal es simular el funcionamiento de una red de concesionarias, donde se pueden gestionar clientes, vehículos, ventas, servicios mecánicos y stock.

## Supuestos

- Solo puede haber una única sucursal central.
- El tiempo de entrega al cliente depende de la sucursal.
- Si no hay stock local, se calcula tiempo extra desde la central.

## Comando para iniciar el proyecto

Como en mi entorno local tengo instalado Maven Wrapper pero no Maven globalmente, debo iniciar el proyecto con:

```bash
mvnw.cmd spring-boot:run
```

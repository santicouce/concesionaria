package ar.edu.palermo.concesionaria.negocio;

import ar.edu.palermo.concesionaria.dominio.Sucursal;
import ar.edu.palermo.concesionaria.dominio.Vehiculo;

public interface IServicioEntrega {
    Integer calcularTiempoEntrega(Sucursal sucursal, Vehiculo vehiculo);
}

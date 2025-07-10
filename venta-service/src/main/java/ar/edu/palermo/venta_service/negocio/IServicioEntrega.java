package ar.edu.palermo.venta_service.negocio;

import ar.edu.palermo.venta_service.dto.StockInfoDTO;
import ar.edu.palermo.venta_service.dto.SucursalInfoDTO;

public interface IServicioEntrega {
    Integer calcularTiempoEntrega(SucursalInfoDTO sucursal, StockInfoDTO stock);
}

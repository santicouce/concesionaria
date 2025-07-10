package ar.edu.palermo.venta_service.negocio.impl;

import ar.edu.palermo.venta_service.dto.StockInfoDTO;
import ar.edu.palermo.venta_service.dto.SucursalInfoDTO;
import ar.edu.palermo.venta_service.negocio.IServicioEntrega;
import org.springframework.stereotype.Service;


@Service
public class ServicioEntrega implements IServicioEntrega {
    // Este servicio calcula el tiempo de entrega de un vehículo basado en el stock
    
    @Override
    public Integer calcularTiempoEntrega(SucursalInfoDTO sucursal, StockInfoDTO stock) {

        if (stock.getCantidad() > 0) {
            return sucursal.getDiasEntregaCliente();
        } else {
            return sucursal.getDiasEntregaDesdeCentral() + sucursal.getDiasEntregaCliente();
        }
    }
}
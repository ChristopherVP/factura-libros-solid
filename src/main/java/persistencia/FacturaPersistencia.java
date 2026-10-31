package persistencia;

import dominio.Factura;

public interface FacturaPersistencia {
    void guardar(Factura factura, double total, String metodoPago);
}
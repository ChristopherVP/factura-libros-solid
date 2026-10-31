package negocio;

import dominio.Factura;

public class CalculadoraFactura {
    public double calcularTotal(Factura factura) {
        double subtotal =factura.getLibro().getPrecio()* factura.getCantidad();
        double descuento =subtotal * factura.getTasaDescuento();
        subtotal -= descuento;
        return subtotal *
                (1 + factura.getTasaImpuesto());
    }
}

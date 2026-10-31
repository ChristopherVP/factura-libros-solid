package negocio;

import dominio.Factura;

public class ImpresoraFactura {

    public void imprimir(Factura factura, double total) {
        System.out.println("\n===== FACTURA =====");
        System.out.println("Cantidad: " + factura.getCantidad() + " x " + factura.getLibro().getNombre());
        System.out.println("Precio Unitario: $" + factura.getLibro().getPrecio());
        System.out.println("Descuento: " + (factura.getTasaDescuento()*100) + "%");
        System.out.println("Impuesto: " + (factura.getTasaImpuesto()*100) + "%");
        System.out.println("TOTAL: $" + total);
    }
}
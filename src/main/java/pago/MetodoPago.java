package pago;

public class MetodoPago {

    public void pagar(double monto) {
        System.out.println("=== PAGO ===");
        System.out.println("Procesando factura...");
        System.out.println("Pago realizado: $" + monto);
        System.out.println("Factura procesada.");
    }
}
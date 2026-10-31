package pago;

public class PagoEfectivo extends MetodoPago {

    @Override
    public void pagar(double monto) {
        System.out.println("\n=== PAGO EN EFECTIVO ===");
        System.out.println("Procesando factura...");
        System.out.println("Pago en efectivo realizado: $" + monto);
        System.out.println("Factura procesada.");
    }
}
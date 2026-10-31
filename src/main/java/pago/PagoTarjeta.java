package pago;

public class PagoTarjeta extends MetodoPago {

    @Override
    public void pagar(double monto) {
        System.out.println("\n=== PAGO CON TARJETA ===");
        System.out.println("Procesando factura...");
        System.out.println("Pago con tarjeta realizado: $" + monto);
        System.out.println("Factura procesada.");
    }
}
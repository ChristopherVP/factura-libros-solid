package ejecutable;

import DB.ConexionPostgres;
import dominio.Factura;
import dominio.Libro;
import negocio.CalculadoraFactura;
import negocio.ImpresoraFactura;
import pago.MetodoPago;
import pago.PagoEfectivo;
import pago.PagoTarjeta;
import persistencia.ArchivoPersistencia;
import persistencia.FacturaPersistencia;
import persistencia.PostgresPersistencia;


public class AbiertaCerrada {

    public static void main(String[] args) {

        CalculadoraFactura calculadora = new CalculadoraFactura();
        ImpresoraFactura impresora = new ImpresoraFactura();

        FacturaPersistencia archivo = new ArchivoPersistencia();
        FacturaPersistencia postgres = new PostgresPersistencia(new ConexionPostgres());

        // Factura 1: Clean Code - Pago en efectivo
        Libro libro1 =
                new Libro(
                        "Clean Code",
                        "Robert C. Martin",
                        2008,
                        50.00,
                        "9780132350884");

        Factura factura1 =
                new Factura(
                        libro1,
                        2,
                        0.10,
                        0.12);

        double total1 = calculadora.calcularTotal(factura1);
        impresora.imprimir(factura1, total1);

        // Factura 2: Effective Java - Pago con tarjeta
        Libro libro2 =
                new Libro(
                        "Effective Java",
                        "Joshua Bloch",
                        2018,
                        45.00,
                        "9780134685991");

        Factura factura2 =
                new Factura(
                        libro2,
                        3,
                        0.05,
                        0.12);

        double total2 = calculadora.calcularTotal(factura2);
        impresora.imprimir(factura2, total2);

        // Liskov: ambos métodos de pago sustituyen a MetodoPago
        MetodoPago pago1 = new PagoEfectivo();
        MetodoPago pago2 = new PagoTarjeta();

        procesarPago(pago1, factura1, total1, archivo, postgres);
        procesarPago(pago2, factura2, total2, archivo, postgres);
    }

    public static void procesarPago(MetodoPago metodo, Factura factura, double total,
                                    FacturaPersistencia archivo, FacturaPersistencia postgres) {
        metodo.pagar(total);
        String nombre = metodo.getClass().getSimpleName();
        archivo.guardar(factura, total, nombre);
        postgres.guardar(factura, total, nombre);
    }
}

package persistencia;

import dominio.Factura;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoPersistencia implements FacturaPersistencia {
    @Override
    public void guardar(Factura factura, double total, String metodoPago) {
        try (FileWriter writer = new FileWriter("factura.txt", true)) {
            writer.write("Libro: " + factura.getLibro().getNombre() + "\n");
            writer.write("Cantidad: " + factura.getCantidad() + "\n");
            writer.write("Total: " + total + "\n");
            writer.write("Metodo de pago: " + metodoPago + "\n\n");
            System.out.println("Factura almacenada en archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
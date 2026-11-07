package persistencia;

import DB.ProveedorConexion;
import dominio.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgresPersistencia implements FacturaPersistencia {
    private final ProveedorConexion proveedorConexion;

    public PostgresPersistencia(ProveedorConexion proveedorConexion) {
        this.proveedorConexion = proveedorConexion;
    }

    @Override
    public void guardar(Factura factura, double total, String metodoPago) {

        String sql = """
                INSERT INTO factura(libro, autor, isbn, precio, cantidad, descuento, impuesto, total, metodo_pago)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        try (Connection conn = proveedorConexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, factura.getLibro().getNombre());
            ps.setString(2, factura.getLibro().getNombreAutor());
            ps.setString(3, factura.getLibro().getIsbn());
            ps.setDouble(4, factura.getLibro().getPrecio());
            ps.setInt(5, factura.getCantidad());
            ps.setDouble(6, factura.getTasaDescuento());
            ps.setDouble(7, factura.getTasaImpuesto());
            ps.setDouble(8, total);
            ps.setString(9, metodoPago);
            ps.executeUpdate();
            System.out.println("Factura almacenada en PostgreSQL.");
        } catch (SQLException | IllegalStateException e) {
            System.err.println("No se pudo guardar en PostgreSQL: " + e.getMessage());
        }
    }
}
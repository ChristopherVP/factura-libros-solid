package DB;

import java.sql.Connection;
import java.sql.SQLException;

public interface ProveedorConexion {
    Connection obtenerConexion() throws SQLException;
}
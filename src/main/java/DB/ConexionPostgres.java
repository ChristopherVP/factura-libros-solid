package DB;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConexionPostgres implements ProveedorConexion {

    private static final Path ARCHIVO_CONFIG = Path.of("db.properties");
    @Override
    public Connection obtenerConexion() throws SQLException {
        Properties config = cargarConfiguracion();
        return DriverManager.getConnection(
                leer(config, "db.url"),
                leer(config, "db.user"),
                leer(config, "db.password"));
    }

    private Properties cargarConfiguracion() {
        Properties config = new Properties();
        try (InputStream entrada = Files.newInputStream(ARCHIVO_CONFIG)) {
            config.load(entrada);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer " + ARCHIVO_CONFIG + ": " + e.getMessage(), e);
        }
        return config;
    }

    private String leer(Properties config, String clave) {
        String valor = config.getProperty(clave);
        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException("Falta '" + clave + "' en " + ARCHIVO_CONFIG);
        }
        return valor.trim();
    }
}
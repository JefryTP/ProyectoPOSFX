package proyectoposfx.bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    
    protected Connection conn;
    
    private final String DB_URL = "jdbc:mysql://localhost:3306/bdahorramax";
    private final String USUAR = "root";
    private final String CONTR = "admin123";
    
    public void conectar() throws Exception {
        try {
            conn = DriverManager.getConnection(DB_URL, USUAR, CONTR);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
    
    public void cerrar() throws Exception {
        if (conn != null) {
            if (!conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada");
            }
        }
    }
}

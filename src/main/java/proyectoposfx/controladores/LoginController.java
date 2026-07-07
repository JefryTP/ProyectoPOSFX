package proyectoposfx.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectoposfx.App;
import proyectoposfx.dao.UsuarioDAO;
import proyectoposfx.modelos.Sesion;
import proyectoposfx.modelos.Usuario;

public class LoginController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtContrasena;

    @FXML
    public void initialize() {
        proyectoposfx.bd.ConexionBD bd = new proyectoposfx.bd.ConexionBD();
        try {
            bd.conectar();
            bd.cerrar();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogin() {
        String contrasena = txtContrasena.getText().trim();
 
        if (contrasena.isEmpty()) {
            mostrarAlerta("Error", "Ingrese su clave.");
            return;
        }
 
        UsuarioDAO dao = new UsuarioDAO();
        try {
            Usuario usuario = dao.login(contrasena);
            if (usuario != null) {
                Sesion.setUsuarioActual(usuario);
                System.out.println("✓ Login exitoso: " + usuario.getNombre()
                        + " (" + usuario.getCargoRol() + ")");
                irAVistaCaja();
            } else {
                mostrarAlerta("Error", "Clave incorrecta.");
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al conectar: " + e.getMessage());
        }
    }

    private void irAVistaCaja() {
    try { 
        App.setRoot("Menu"); 
    } catch (Exception e) { 
        System.out.println("Error al cargar Menu: " + e.getMessage()); 
    } 
}

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
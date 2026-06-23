package proyectoposfx.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectoposfx.dao.UsuarioDAO;
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
        String codigo = txtCodigo.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        if (codigo.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Error", "Ingrese código y contraseña.");
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        try {
            Usuario usuario = dao.login(codigo, contrasena);

            if (usuario != null) {
                System.out.println("✓ Login exitoso: " + usuario.getNombre());
                irAVistaCaja();
            } else {
                mostrarAlerta("Error", "Código o contraseña incorrectos.");
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al conectar: " + e.getMessage());
        }
    }

    private void irAVistaCaja() {
    try {
        Parent root = FXMLLoader.load(
            getClass().getResource("/grupo/uno/proyectoposfx/VistaCaja.fxml")
        );
        Stage stage = (Stage) txtCodigo.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    } catch (Exception e) {
        System.out.println("Error al cargar VistaCaja: " + e.getMessage());
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
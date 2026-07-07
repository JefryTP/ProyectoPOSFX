
package proyectoposfx.controladores;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

public class MenuController {

    @FXML
    private StackPane ctnVista;

    @FXML
    public void initialize() {
        cargarVista("/grupo/uno/proyectoposfx/VistaCaja.fxml");
    }

    @FXML
    private void irCaja(ActionEvent event) {
        cargarVista("/grupo/uno/proyectoposfx/VistaCaja.fxml");
    }
    
    @FXML
    private void irArqueo(ActionEvent event) {
        mostrarPendiente("Arqueo");
    }
 
    @FXML
    private void irUsuarios(ActionEvent event) {
        mostrarPendiente("Usuarios");
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        mostrarPendiente("Cerrar Sesion");
    }

    private void cargarVista(String rutaFxml) {
        try {
            Parent vista = FXMLLoader.load(getClass().getResource(rutaFxml));
            ctnVista.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarPendiente(String seccion) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("En construcción");
        alert.setHeaderText(null);
        alert.setContentText("La sección de " + seccion + " aún no está implementada.");
        alert.showAndWait();
    }
}

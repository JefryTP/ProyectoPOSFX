package proyectoposfx.controladores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
 
import proyectoposfx.dao.ProductoDAO;
import proyectoposfx.modelos.ItemCaja;
import proyectoposfx.modelos.Producto;
import proyectoposfx.modelos.Sesion;
import proyectoposfx.modelos.Usuario;
public class CajaController {
     
    @FXML private MenuButton mbTipoDocumento;
    @FXML private TextField txtNumeroDocumento;
    @FXML private TextField txtCodigoProducto;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtCajero;
 
    @FXML private TableView<ItemCaja> tablaCaja;
    @FXML private TableColumn<ItemCaja, String> colProducto;
    @FXML private TableColumn<ItemCaja, Double> colPrecio;
    @FXML private TableColumn<ItemCaja, Integer> colCantidad;
    @FXML private TableColumn<ItemCaja, Double> colSubtotal;
 
    @FXML private TextField txtTotalPago;
 
    private final ObservableList<ItemCaja> itemsCarrito = FXCollections.observableArrayList();
 
    @FXML
    public void initialize() {
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        tablaCaja.setItems(itemsCarrito);
 
        // Mostrar el nombre del cajero que inició sesión
        Usuario actual = Sesion.getUsuarioActual();
        if (actual != null) {
            txtCajero.setText(actual.getNombre());
        }
    }
 
    @FXML
    private void seleccionarTipoDocumento(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        mbTipoDocumento.setText(item.getText());
    }
 
    @FXML
    private void agregarProducto() {
        String codigoTexto = txtCodigoProducto.getText().trim();
        String cantidadTexto = txtCantidad.getText().trim();
 
        if (codigoTexto.isEmpty()) {
            mostrarAlerta("Atención", "Ingresa el código del producto.");
            return;
        }
        if (cantidadTexto.isEmpty()) {
            mostrarAlerta("Atención", "Ingresa la cantidad.");
            return;
        }
 
        int codigo;
        int cantidad;
        try {
            codigo = Integer.parseInt(codigoTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Atención", "El código debe ser un número.");
            return;
        }
        try {
            cantidad = Integer.parseInt(cantidadTexto);
            if (cantidad <= 0) {
                mostrarAlerta("Atención", "La cantidad debe ser mayor a 0.");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Atención", "La cantidad debe ser un número entero.");
            return;
        }
 
        try {
            ProductoDAO dao = new ProductoDAO();
            Producto producto = dao.buscarPorId(codigo);
 
            if (producto == null) {
                mostrarAlerta("No encontrado", "No existe un producto activo con código " + codigo + ".");
                return;
            }
 
            double subtotal = producto.getPrecio() * cantidad;
            ItemCaja item = new ItemCaja(
                    producto.getId(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    cantidad,
                    subtotal
            );
            itemsCarrito.add(item);
            actualizarTotal();
 
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al buscar el producto: " + e.getMessage());
            return;
        }
 
        // limpiar campos y devolver el foco al código, listo para el siguiente producto
        txtCodigoProducto.clear();
        txtCantidad.setText("1");
        txtCodigoProducto.requestFocus();
    }
 
    @FXML
    private void irACobrar() {
        mostrarInfo("Próximamente", "La función de pago estará disponible próximamente.");
    }
 
    private void actualizarTotal() {
        double total = itemsCarrito.stream()
                .mapToDouble(ItemCaja::getSubtotal)
                .sum();
        txtTotalPago.setText(String.format("%.2f", total));
    }
 
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
 
    private void mostrarInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

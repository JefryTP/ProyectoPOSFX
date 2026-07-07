package proyectoposfx.modelos;
 
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
 
public class ItemCaja {
    private final int idProducto;
    private final SimpleStringProperty producto;
    private final SimpleDoubleProperty precioUnitario;
    private final SimpleIntegerProperty cantidad;
    private final SimpleDoubleProperty subtotal;
 
    public ItemCaja(int idProducto, String producto, double precioUnitario, int cantidad, double subtotal) {
        this.idProducto = idProducto;
        this.producto = new SimpleStringProperty(producto);
        this.precioUnitario = new SimpleDoubleProperty(precioUnitario);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.subtotal = new SimpleDoubleProperty(subtotal);
    }
 
    public int getIdProducto() { return idProducto; }
 
    public String getProducto() { return producto.get(); }
    public SimpleStringProperty productoProperty() { return producto; }
 
    public double getPrecioUnitario() { return precioUnitario.get(); }
    public SimpleDoubleProperty precioUnitarioProperty() { return precioUnitario; }
 
    public int getCantidad() { return cantidad.get(); }
    public SimpleIntegerProperty cantidadProperty() { return cantidad; }
 
    public double getSubtotal() { return subtotal.get(); }
    public SimpleDoubleProperty subtotalProperty() { return subtotal; }
}